package com.crebsthecoder.skwasp.api.event.bound;

import com.crebsthecoder.skwasp.api.bound.Bound;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class BoundEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Bound bound;

    public BoundEvent(Bound bound) {
        this.bound = bound;
    }

    /**
     * The bound that is affiliated with the event
     *
     * @return Bound that is affiliated
     */
    public Bound getBound() {
        return bound;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

}
