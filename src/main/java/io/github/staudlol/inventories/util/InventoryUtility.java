package io.github.staudlol.inventories.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InventoryUtility {

    /**
     * Serialize an {@link ItemStack} array to Base64 string.
     *
     * @param items the items.
     * @return Base64 string.
     * @throws IllegalStateException if unable to save item stack.
     */

    public static String toBase64(ItemStack[] items) throws IllegalStateException {
        try {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(items.length);

            for (ItemStack item : items) {
                dataOutput.writeObject(item);
            }

            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to save item stacks.", exception);
        }
    }

    /**
     * Get an array of {@link ItemStack} from Base64 string.
     *
     * @param data the data.
     * @return the items.
     * @throws IOException if unable to decode.
     */

    public static ItemStack[] fromBase64(String data) throws IOException {
        try {
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            final BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            final ItemStack[] items = new ItemStack[dataInput.readInt()];

            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }

            dataInput.close();
            return items;
        } catch (ClassNotFoundException exception) {
            throw new IOException("Unable to decode class type.", exception);
        }
    }
}
