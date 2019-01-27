package net.teengamingnights.assemblymod.factory;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class BasicFactory implements Factory {
    /*So basically, when you want to make a new factory just extend this class. Most factories won't need to override anything,
      but when we do it like this it gives the option to allow factories to override methods and do fancier things.
     */

    // Private fields.
    private Location furnace;
    private Location center;
    private Location chest;
    private UUID id;
    private double health;
    private FactoryType type;
    private List<ItemStack> requirements;
    private List<Recipe> recipes;
    private double healthLossMultiplier;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Location getCenter() {
        return center;
    }

    @Override
    public Location getFurnace() {
        return furnace;
    }

    @Override
    public Location getChest() {
        return chest;
    }

    @Override
    public FactoryType getType() {
        return type;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public double getHealthLossMultiplier() {
        return healthLossMultiplier;
    }

    @Override
    public List<ItemStack> getRequiredMaterials() {
        return requirements;
    }

    @Override
    public boolean contains(Location loc) {
        return loc.equals(center) || loc.equals(furnace) || loc.equals(chest);
    }

    public BasicFactory(FactoryType ft, Location center, Location chest, Location furnace){
        this.center = center;
        this.furnace = furnace;
        this.chest = chest;
        this.type = ft;
        this.id = UUID.randomUUID();

        this.health = type.getStartingHealth();
        this.healthLossMultiplier = type.getHlMultiplier();
        this.requirements = type.getCreationCost();
    }
}
