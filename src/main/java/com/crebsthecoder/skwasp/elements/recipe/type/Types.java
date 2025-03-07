package com.crebsthecoder.skwasp.elements.recipe.type;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.StringUtils;
import com.crebsthecoder.skwasp.api.recipe.RecipeType;
import com.crebsthecoder.skwasp.api.wrapper.EnumWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.jetbrains.annotations.NotNull;
import org.skriptlang.skript.lang.converter.Converter;
import org.skriptlang.skript.lang.converter.Converters;

import java.util.ArrayList;
import java.util.List;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(RecipeChoice.class, "recipechoice")
            .name("Recipe Choice")
            .user("recipe ?choices?")
            .description("Represents an Exact/Material Choice.",
                "MaterialChoice represents a set of materials/minecraft tags which can be used in some recipes.",
                "ExactChoice represents a special ItemStack used in some recipes.",
                "Requires Minecraft 1.13+")
            .usage("see material choice expression")
            .examples("set {_a} to material choice of diamond sword, diamond shovel and diamond hoe",
                "set {_a} to material choice of every sword",
                "set {_a} to material choice of minecraft tag \"doors\"")
            .after("itemtype", "itemstack")
            .since("1.10.0")
            .parser(new Parser<>() {

                @SuppressWarnings("NullableProblems")
                @Override
                public boolean canParse(ParseContext context) {
                    return false;
                }

                @Override
                public @NotNull String toString(@NotNull RecipeChoice matChoice, int flags) {
                    return recipeChoiceToString(matChoice);
                }

                @Override
                public @NotNull String toVariableNameString(RecipeChoice matChoice) {
                    return "recipechoice:" + toString(matChoice, 0);
                }
            }));

        EnumWrapper<RecipeType> RECIPE_TYPE_ENUM = new EnumWrapper<>(RecipeType.class);
        Classes.registerClass(RECIPE_TYPE_ENUM.getClassInfo("recipetype")
            .user("recipe ?types?")
            .name("Recipe Type")
            .description("Represents the types of recipes.")
            .since("2.6.0"));

        // CONVERTERS
        Converters.registerConverter(ItemStack.class, RecipeChoice.class, new Converter<>() {
            @Override
            public @NotNull RecipeChoice convert(ItemStack from) {
                if (from.isSimilar(new ItemStack(from.getType()))) {
                    return new MaterialChoice(from.getType());
                }
                return new ExactChoice(from);
            }
        });
    }

    private static String recipeChoiceToString(RecipeChoice recipeChoice) {
        List<String> itemTypes = new ArrayList<>();
        if (recipeChoice instanceof MaterialChoice materialChoice) {
            materialChoice.getChoices().forEach(material -> itemTypes.add(new ItemType(material).toString()));
            return String.format("MaterialChoice{choices=[%s]}", StringUtils.join(itemTypes, ", "));
        } else if (recipeChoice instanceof ExactChoice exactChoice) {
            exactChoice.getChoices().forEach(material -> itemTypes.add(new ItemType(material).toString()));
            return String.format("ExactChoice{choices=[%s]}", StringUtils.join(itemTypes, ", "));
        }
        throw new IllegalStateException("This shouldnt happen!!!");
    }

}
