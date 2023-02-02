package io.github.staudlol.inventories.profile;

import io.github.staudlol.inventories.InventorySpigotPlugin;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ProfileHandler {

    private final Map<UUID, PlayerProfile> profileMap = new HashMap<>();

    public ProfileHandler() {
        final FileConfiguration config = InventorySpigotPlugin.getInstance().getConfig();
        final ConfigurationSection section = config.getConfigurationSection("profiles");

        if (section != null) {
            for (String key : section.getKeys(false)) {
                final PlayerProfile playerProfile = (PlayerProfile) section.get(key);

                this.profileMap.put(playerProfile.getUniqueId(), playerProfile);
            }
        }
    }

    /**
     * Find or create a {@link PlayerProfile} by a {@link UUID} and {@link String}
     *
     * @param uniqueId the unique identifier.
     * @param name the name.
     * @return the player profile.
     */

    public PlayerProfile findOrCreateProfile(UUID uniqueId, String name) {
        PlayerProfile playerProfile = this.profileMap.get(uniqueId);

        if (playerProfile == null) {
            playerProfile = new PlayerProfile(
                    uniqueId,
                    name
            );

            this.profileMap.put(uniqueId, playerProfile);
        }

        return playerProfile;
    }

    /**
     * Save a {@link PlayerProfile} to the config.
     *
     * @param playerProfile the playerProfile.
     */

    public void saveProfile(PlayerProfile playerProfile) {
        final FileConfiguration config = InventorySpigotPlugin.getInstance().getConfig();

        config.set("profiles." + playerProfile.getUniqueId(), playerProfile);

        // remove the player from the cache
        this.profileMap.remove(playerProfile.getUniqueId());

        InventorySpigotPlugin.getInstance().saveConfig();
    }

    /**
     * Save the inventory of a {@link PlayerProfile}
     *
     * @param playerProfile the playerProfile.
     */

    public void saveInventory(PlayerProfile playerProfile) {
        final FileConfiguration configuration = InventorySpigotPlugin.getInstance().getConfig();
        final ConfigurationSection section = configuration.getConfigurationSection("profiles");

        if (section == null) {
            throw new IllegalArgumentException("Invalid map provided to deserialize PlayerProfile.");
        }

        section.set(playerProfile.getUniqueId() + ".contents", playerProfile.getContents());
        InventorySpigotPlugin.getInstance().saveConfig();
    }

    public void saveProfiles() {
        final FileConfiguration config = InventorySpigotPlugin.getInstance().getConfig();

        for (Map.Entry<UUID, PlayerProfile> entry : this.profileMap.entrySet()) {
            config.set("profiles." + entry.getKey(), entry.getValue());
        }

        // clear the cache
        this.profileMap.clear();

        InventorySpigotPlugin.getInstance().saveConfig();
    }
}
