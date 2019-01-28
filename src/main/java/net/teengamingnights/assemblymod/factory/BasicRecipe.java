package net.teengamingnights.assemblymod.factory;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BasicRecipe implements Recipe {
    // Private fields
    private int id;
    private List<ItemStack> cost;
    private List<ItemStack> product;
    private int duration;

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

    @Override
    public int getDuration(){
        return duration;
    }

    public BasicRecipe(int id, List<ItemStack> cost, List<ItemStack> product, int duration) {
        this.id = id;
        this.cost = cost;
        this.product = product;
        this.duration = duration;
    }
}
