package net.teengamingnights.assemblymod.factory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static java.util.Arrays.asList;

public class Recipes {
    static Recipe STONE = new BasicRecipe(0, asList(new ItemStack(Material.COBBLESTONE, 64)), asList(new ItemStack(Material.STONE, 64)));

}
