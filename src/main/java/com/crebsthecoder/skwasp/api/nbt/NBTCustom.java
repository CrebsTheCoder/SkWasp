package com.crebsthecoder.skwasp.api.nbt;

import com.crebsthecoder.skwasp.SkBee;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import org.bukkit.NamespacedKey;

public interface NBTCustom {

    NamespacedKey OLD_KEY = new NamespacedKey(SkBee.getPlugin(), "custom-nbt");
    String KEY = "skbee-custom";

    void deleteCustomNBT();

    NBTCompound getCopy();

}
