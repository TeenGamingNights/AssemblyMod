package net.teengamingnights.assemblymod.factory;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestFactory implements IFactory {
    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public Location getCenter() {
        return null;
    }

    @Override
    public Location getFurnace() {
        return null;
    }

    @Override
    public Location getChest() {
        return null;
    }

    @Override
    public FactoryType getType() {
        return null;
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public double getHealthLossMultiplier() {
        return 0;
    }

    @Override
    public List<ItemStack> getRequiredMaterials() {
        return new ArrayList<ItemStack>() {{
                add(new ItemStack(Material.OAK_PLANKS, 4));
                add(new ItemStack(Material.COBBLESTONE, 2));
        }};
    }
}
