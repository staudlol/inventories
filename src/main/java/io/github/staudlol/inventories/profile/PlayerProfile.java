package io.github.staudlol.inventories.profile;

import io.github.staudlol.inventories.util.InventoryUtility;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class PlayerProfile implements ConfigurationSerializable {

    public final UUID uniqueId;
    public final String name;

    public ItemStack[] contents = new ItemStack[36];
    public ItemStack[] armorContents = new ItemStack[4];

    /**
     * Constructor to create a new {@link PlayerProfile}
     *
     * @param uniqueId the unique identifier.
     * @param name the name.
     */

    public PlayerProfile(UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    @Override
    public Map<String, Object> serialize() {
        return new HashMap<String, Object>() {{
            put("uniqueId", uniqueId.toString());
            put("name", name);

            if (contents == null) {
                put("contents", null);
            } else {
                put("contents", InventoryUtility.toBase64(contents));
            }

            if (armorContents == null) {
                put("armorContents", null);
            } else {
                put("armorContents", InventoryUtility.toBase64(armorContents));
            }
        }};
    }

    public static PlayerProfile deserialize(Map<String, Object> map) throws IOException {
        if (!map.containsKey("uniqueId") || !map.containsKey("name")) {
            throw new IllegalArgumentException("Invalid map provided to deserialize PlayerProfile.");
        }

        final PlayerProfile playerProfile = new PlayerProfile(
                UUID.fromString((String) map.get("uniqueId")),
                (String) map.get("name")
        );

        if (map.get("contents") == null) {
            playerProfile.setContents(null);
        } else {
            playerProfile.setContents(InventoryUtility.fromBase64((String) map.get("contents")));
        }

        if (map.get("armorContents") == null) {
            playerProfile.setArmorContents(null);
        } else {
            playerProfile.setArmorContents(InventoryUtility.fromBase64((String) map.get("armorContents")));
        }

        return playerProfile;
    }

    /**
     * Apply the inventory to a {@link Player}
     *
     * @param player the player.
     */

    public void applyInventory(Player player) {
        player.getInventory().setContents(contents);
        player.getInventory().setArmorContents(armorContents);

        player.sendMessage(ChatColor.YELLOW + "Your inventory has been restored.");

        player.updateInventory();
    }

    /**
     * Get the {@link Player} from the {@link UUID}
     *
     * @return the player.
     */

    public Player getPlayer() {
        return Bukkit.getPlayer(uniqueId);
    }
}
