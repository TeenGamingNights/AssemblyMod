package net.teengamingnights.assemblymod.factory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Recipes {
    static Recipe STONE = new BasicRecipe(0, singletonList(new ItemStack(Material.COBBLESTONE, 64)), singletonList(new ItemStack(Material.STONE, 64)), 1600);
    static Recipe STONE_BRICKS = new BasicRecipe(1, singletonList(new ItemStack(Material.STONE, 64)), singletonList(new ItemStack(Material.STONE_BRICKS, 64)), 1600);
    static Recipe RAILS = new BasicRecipe(2, asList(new ItemStack(Material.IRON_INGOT, 32), new ItemStack(Material.STICK, 8)), asList(new ItemStack(Material.RAIL, 64), new ItemStack(Material.RAIL, 64)), 800);

}
