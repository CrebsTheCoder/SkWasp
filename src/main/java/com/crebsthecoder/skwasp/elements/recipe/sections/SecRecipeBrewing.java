package com.crebsthecoder.skwasp.elements.recipe.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Section;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import com.crebsthecoder.skwasp.SkBee;
import com.crebsthecoder.skwasp.api.recipe.RecipeUtil;
import com.crebsthecoder.skwasp.api.util.Util;
import io.papermc.paper.potion.PotionMix;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.potion.PotionBrewer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.skriptlang.skript.lang.entry.EntryContainer;
import org.skriptlang.skript.lang.entry.EntryValidator;
import org.skriptlang.skript.lang.entry.util.ExpressionEntryData;

import java.util.List;

@Name("Recipe - Register Brewing Recipe")
@Description({"This section allows you to register a brewing recipe, define the ingredient and input. Requires a PaperMC server.",
    "\n`id` = The ID of this recipe.",
    "\n`result` = The resulting output ItemStack of this recipe (What the 3 bottle slots turn into).",
    "\n`ingredient` = Represents the ItemStack put in the top of the brewer (Accepts an ItemStack or RecipeChoice).",
    "\n`input` = Represents the ItemStack put in the 3 bottle slots (Accepts an ItemStack or RecipeChoice)."})
@Examples({"on load:",
    "\tregister brewing recipe:",
    "\t\tid: \"custom:brew_glow_diamond\"",
    "\t\tresult: diamond of unbreaking with all item flags",
    "\t\tingredient: glowstone dust",
    "\t\tinput: potato",
    "\t\t",
    "\tregister brewing recipe:",
    "\t\tid: \"custom:yummy_soup\"",
    "\t\tresult: mushroom stew named \"&bYummy Soup\"",
    "\t\tingredient: glowstone dust",
    "\t\tinput: water bottle"})
@Since("3.0.0")
public class SecRecipeBrewing extends Section {

    private static final EntryValidator.EntryValidatorBuilder ENTRY_VALIDATOR = EntryValidator.builder();
    private static final boolean DEBUG = SkBee.getPlugin().getPluginConfig().SETTINGS_DEBUG;
    private static PotionBrewer POTION_BREWER = null;

    static {
        if (Skript.classExists("io.papermc.paper.potion.PotionMix")) {
            Skript.registerSection(SecRecipeBrewing.class,
                "register [a] [new] (brewing recipe|potion mix)");
            ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("id", null, false, String.class));
            ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("result", null, false, ItemStack.class));
            ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("ingredient", null, false, RecipeChoice.class));
            ENTRY_VALIDATOR.addEntryData(new ExpressionEntryData<>("input", null, false, RecipeChoice.class));
            POTION_BREWER = Bukkit.getPotionBrewer();
        }
    }

    private Expression<String> id;
    private Expression<ItemStack> result;
    private Expression<RecipeChoice> ingredient;
    private Expression<RecipeChoice> input;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult, SectionNode sectionNode, List<TriggerItem> triggerItems) {
        EntryContainer container = ENTRY_VALIDATOR.build().validate(sectionNode);
        if (container == null) return false;

        this.id = (Expression<String>) container.getOptional("id", false);
        if (this.id == null) return false;
        this.result = (Expression<ItemStack>) container.getOptional("result", false);
        if (this.result == null) return false;
        this.ingredient = (Expression<RecipeChoice>) container.getOptional("ingredient", false);
        if (this.ingredient == null) return false;
        this.input = (Expression<RecipeChoice>) container.getOptional("input", false);
        return this.ingredient != null;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable TriggerItem walk(Event event) {
        execute(event);
        return super.walk(event, false);
    }

    private void execute(Event event) {
        String recipeId = this.id.getSingle(event);
        if (recipeId == null) {
            RecipeUtil.error("Invalid/Missing recipe Id: &e" + this.toString(event, DEBUG));
            return;
        }
        NamespacedKey namespacedKey = Util.getNamespacedKey(recipeId, false);
        ItemStack result = this.result.getSingle(event);
        RecipeChoice input = this.input.getSingle(event);
        RecipeChoice ingredient = this.ingredient.getSingle(event);

        if (namespacedKey == null) {
            RecipeUtil.error("Invalid/Missing recipe Id: &e" + this.toString(event, DEBUG));
            return;
        } else if (result == null || !result.getType().isItem() || result.getType().isAir()) {
            RecipeUtil.error("Invalid/Missing recipe result: &e" + this.toString(event, DEBUG));
            return;
        } else if (input == null) {
            RecipeUtil.error("Invalid/Missing recipe input: &e" + this.toString(event, DEBUG));
            return;
        } else if (ingredient == null) {
            RecipeUtil.error("Invalid/Missing recipe ingredient: &e" + this.toString(event, DEBUG));
            return;
        }

        // Remove duplicates on script reload
        POTION_BREWER.removePotionMix(namespacedKey);
        PotionMix potionMix = new PotionMix(namespacedKey, result, input, ingredient);
        POTION_BREWER.addPotionMix(potionMix);
        if (DEBUG) RecipeUtil.logBrewingRecipe(potionMix);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean d) {
        return "register brewing recipe";
    }

}
