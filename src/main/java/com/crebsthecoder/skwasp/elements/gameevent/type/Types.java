package com.crebsthecoder.skwasp.elements.gameevent.type;

import ch.njol.skript.registrations.Classes;
import com.crebsthecoder.skwasp.api.wrapper.RegistryClassInfo;
import org.bukkit.GameEvent;
import org.bukkit.Registry;

public class Types {

    static {

        Classes.registerClass(RegistryClassInfo.create(Registry.GAME_EVENT, GameEvent.class, "gameevent")
            .name("Game Event")
            .user("game ?events?")
            .description("Represents a Minecraft 'GameEvent', mainly used by Skulk Sensors. Requires MC 1.17+.",
                "See [**GameEvents**](https://minecraft.wiki/w/Sculk_Sensor#Vibration_frequencies) on McWiki for more details.",
                "NOTE: These are auto-generated and may differ between server versions.")
            .after("itemtype")
            .examples("")
            .since("1.14.0"));
    }

}
