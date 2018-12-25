package net.teengamingnights.assemblymod.factory;

import net.teengamingnights.assemblymod.factory.Factory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TestFactory implements Factory {
    @Override
    public List<ItemStack> getRequiredMaterials() {
        return new ArrayList<ItemStack>() {{
                add(new ItemStack(Material.OAK_PLANKS, 4));
                add(new ItemStack(Material.COBBLESTONE, 2));
        }};
    }
}
