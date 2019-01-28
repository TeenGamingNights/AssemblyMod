package net.teengamingnights.assemblymod.factory;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Recipe {
    int getId();
    List<ItemStack> getCost();
    List<ItemStack> getProduct();
    int getDuration();
}
