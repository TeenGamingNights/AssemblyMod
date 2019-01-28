package net.teengamingnights.assemblymod.factory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Recipes {
    static Recipe STONE = new BasicRecipe(0, singletonList(new ItemStack(Material.COBBLESTONE, 64)), singletonList(new ItemStack(Material.STONE, 64)), 1600);

}
