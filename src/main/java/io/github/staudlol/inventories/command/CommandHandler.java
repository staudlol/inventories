package io.github.staudlol.inventories.command;

import io.github.staudlol.inventories.InventorySpigotPlugin;
import io.github.staudlol.inventories.command.load.LoadInventoryCommand;
import io.github.staudlol.inventories.command.save.SaveInventoryCommand;

public class CommandHandler {

    public CommandHandler() {
        InventorySpigotPlugin.getInstance().getCommand("saveinventory").setExecutor(new SaveInventoryCommand());
        InventorySpigotPlugin.getInstance().getCommand("loadinventory").setExecutor(new LoadInventoryCommand());
    }
}
