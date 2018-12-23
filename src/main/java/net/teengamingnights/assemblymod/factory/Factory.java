package net.teengamingnights.assemblymod.factory;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Factory {
    List<ItemStack> getRequiredMaterials();
}
