package net.teengamingnights.assemblymod.factory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FactoryType {
    // Create a placeholder factory requiring 8 cobblestone
    PLACEHOLDER_FACTORY("Placeholder",
            new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Material.COBBLESTONE, 8))),
            100,
            10);

    // This really isn't ideal, but I hope y'all can understand what I'm trying to do here.

    private final String typeName;
    private final List<ItemStack> creationCost;
    private final double startingHealth;
    private final double hlMultiplier;

    FactoryType(String name, List<ItemStack> cost, double health, double lossFactor) {

        this.typeName = name;
        this.creationCost = cost;
        this.startingHealth = health;
        this.hlMultiplier = lossFactor;

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
