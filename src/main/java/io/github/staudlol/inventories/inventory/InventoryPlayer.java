package io.github.staudlol.inventories.inventory;

import io.github.staudlol.inventories.util.InventoryUtility;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class InventoryPlayer implements ConfigurationSerializable {

    public final UUID uniqueId;

    public ItemStack[] contents = new ItemStack[36];
    public ItemStack[] armorContents = new ItemStack[4];

    /**
     * Constructor to create a new {@link InventoryPlayer
     *
     * @param uniqueId the unique identifier.
     */

    public InventoryPlayer(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public Map<String, Object> serialize() {
       return new HashMap<String, Object>() {{
           put("uniqueId", uniqueId);

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

    public static InventoryPlayer deserialize(Map<String, Object> map) throws IOException {
        final InventoryPlayer inventoryPlayer = new InventoryPlayer((UUID) map.get("uniqueId"));

        if (map.get("contents") == null) {
            inventoryPlayer.setContents(null);
        } else {
            inventoryPlayer.setContents(InventoryUtility.fromBase64((String) map.get("contents")));
        }

        if (map.get("armorContents") == null) {
            inventoryPlayer.setArmorContents(null);
        } else {
            inventoryPlayer.setArmorContents(InventoryUtility.fromBase64((String) map.get("armorContents")));
        }

        return inventoryPlayer;
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
}