package io.github.staudlol.inventories;

import io.github.staudlol.inventories.command.CommandHandler;
import io.github.staudlol.inventories.profile.PlayerProfile;
import io.github.staudlol.inventories.profile.ProfileHandler;
import io.github.staudlol.inventories.profile.listener.ProfileListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class InventorySpigotPlugin extends JavaPlugin {

    private static InventorySpigotPlugin instance;

    private CommandHandler commandHandler;
    private ProfileHandler profileHandler;

    @Override
    public void onEnable() {
        instance = this;

        ConfigurationSerialization.registerClass(PlayerProfile.class);

        this.commandHandler = new CommandHandler();
        this.profileHandler = new ProfileHandler();

        Bukkit.getPluginManager().registerEvents(new ProfileListener(this.profileHandler), this);
    }

    @Override
    public void onDisable() {
        this.profileHandler.saveProfiles();

        instance = null;
    }

    public static InventorySpigotPlugin getInstance() {
        return instance;
    }
}
