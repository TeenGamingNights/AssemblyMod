package net.teengamingnights.assemblymod.factory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;

public enum FactoryType {
    // Create a placeholder factory requiring 8 cobblestone
    PLACEHOLDER_FACTORY("Placeholder",
            new ArrayList<ItemStack>(asList(new ItemStack(Material.COBBLESTONE, 8))),
            100,
            10,
            asList(Recipes.STONE));

    // This really isn't ideal, but I hope y'all can understand what I'm trying to do here.

    private final String typeName;
    private final List<ItemStack> creationCost;
    private final double startingHealth;
    private final double hlMultiplier;
    private final List<IRecipe> recipes;

    FactoryType(String name, List<ItemStack> cost, double health, double lossFactor, List<IRecipe> recipes) {

        this.typeName = name;
        this.creationCost = cost;
        this.startingHealth = health;
        this.hlMultiplier = lossFactor;
        this.recipes = recipes;

    }

    public String getName() {

        return typeName;

    }

    public List<ItemStack> getCreationCost() {

        return creationCost;

    }

    public double getStartingHealth() {

        return startingHealth;

    }

    public double getHlMultiplier() {

        return hlMultiplier;

    }

}
