package net.teengamingnights.assemblymod.factory;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IRecipe {
    int getId();
    List<ItemStack> getCost();
    List<ItemStack> getProduct();
}
