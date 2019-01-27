package net.teengamingnights.assemblymod.factory;

import net.teengamingnights.assemblymod.factory.IRecipe;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Recipe implements IRecipe {
    // Private fields
    private int id;
    private List<ItemStack> cost;
    private List<ItemStack> product;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<ItemStack> getCost() {
        return cost;
    }

    @Override
    public List<ItemStack> getProduct() {
        return product;
    }

    public Recipe(int id, List<ItemStack> cost, List<ItemStack> product) {
        this.id = id;
        this.cost = cost;
        this.product = product;
    }
}
