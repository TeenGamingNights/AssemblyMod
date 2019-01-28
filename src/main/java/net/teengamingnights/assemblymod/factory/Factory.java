package net.teengamingnights.assemblymod.factory;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public interface Factory {
    UUID getId();

    Location getCenter();

    Location getFurnace();

    Location getChest();

    FactoryType getType();

    double getHealth();

    double getHealthLossMultiplier();

    List<ItemStack> getRequiredMaterials();

    boolean contains(Location loc);

    boolean getEnabled();

    Recipe getCurrentRecipe();

    void setRecipe(Recipe recipe);

    void toggle();
}
