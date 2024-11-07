package com.crebsthecoder.skwasp.elements.bound.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.crebsthecoder.skwasp.SkBee;
import com.crebsthecoder.skwasp.config.BoundConfig;
import org.bukkit.event.Event;
import com.crebsthecoder.skwasp.api.bound.Bound;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Bound - From ID")
@Description("Get a bound object from a bound ID")
@Examples("set {_b} to bound from id \"%player%.home\"")
@Since("1.0.0")
public class ExprBoundFromID extends SimpleExpression<Bound> {

    private static final BoundConfig boundConfig = SkBee.getPlugin().getBoundConfig();

    static {
        Skript.registerExpression(ExprBoundFromID.class, Bound.class, ExpressionType.SIMPLE,
            "bound[s] (of|from|with) id[s] %strings%");
    }

    private Expression<String> ids;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        this.ids = (Expression<String>) exprs[0];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected Bound[] get(Event event) {
        List<Bound> bounds = new ArrayList<>();
        for (String id : this.ids.getArray(event)) {
            Bound bound = boundConfig.getBoundFromID(id);
            if (bounds.contains(bound)) continue;
            bounds.add(bound);
        }
        return bounds.toArray(new Bound[0]);
    }

    @Override
    public boolean isSingle() {
        return ids.isSingle();
    }

    @Override
    public @NotNull Class<? extends Bound> getReturnType() {
        return Bound.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "bound from id " + this.ids.toString(event, debug);
    }

}
