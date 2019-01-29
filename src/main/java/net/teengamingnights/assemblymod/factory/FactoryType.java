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
            asList(Recipes.STONE, Recipes.STONE_BRICKS, Recipes.RAILS));

    // This really isn't ideal, but I hope y'all can understand what I'm trying to do here.

    private final String typeName;
    private final List<ItemStack> creationCost;
    private final double startingHealth;
    private final double hlMultiplier;
    private final List<Recipe> recipes;

    FactoryType(String name, List<ItemStack> cost, double health, double lossFactor, List<Recipe> recipes) {

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

    public ItemStack[] getRefund(double health){
        ItemStack[] refund = new ItemStack[creationCost.size()];
        for (int i = 0; i < refund.length; i++){
            ItemStack item = creationCost.get(i);
            refund[i] = new ItemStack(item.getType(), (int)Math.floor(item.getAmount()/2));
        }
        return refund;
    }

    public Recipe[] getRecipes(){
        return (Recipe[]) this.recipes.toArray();
    }

}
