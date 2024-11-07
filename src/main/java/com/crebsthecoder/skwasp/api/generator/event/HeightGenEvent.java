package com.crebsthecoder.skwasp.api.generator.event;

import org.bukkit.Location;

public class HeightGenEvent extends BaseGenEvent {

    private final Location location;
    private int height = 0;

    public HeightGenEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
