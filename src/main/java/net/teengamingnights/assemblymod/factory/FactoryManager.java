package net.teengamingnights.assemblymod.factory;

import net.teengamingnights.assemblymod.utils.items.ItemsUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class FactoryManager {

    private List<Factory> factories = new ArrayList<>();
    private EnumSet<FactoryType> factoryTypes = EnumSet.allOf(FactoryType.class);

    public List<Factory> getRegFactories() {
        return new ArrayList<>(factories);
    }

    public boolean factoryExistsAt(Location loc){
        for (Factory fac : factories){
            if(fac.getCenter().equals(loc)){
                return true;
            }
        }
        return false;
    }

    public Factory getFactoryAt(Location loc){
        return getRegFactories()
                .parallelStream()
                .filter(factory -> factory.contains(loc))
                .findAny().orElse(null);
    }

    public void registerFactory(Factory factory) {
        factories.add(factory);
        System.out.println("[DEBUG] Created a " + factory.getType().getName() + " factory at " + factory.getCenter().toString());
    }

    public void unregisterFactory(Factory factory) {
        factories.remove(factory);
        System.out.println("[DEBUG] Unregistered a factory at " + factory.getCenter().toString());
    }

    public boolean isBlockFac(Block block) {
        return getRegFactories()
                .parallelStream()
                .anyMatch(factory -> factory.contains(block.getLocation()));
    }

    public void createFactory(Block center, Chest chest, Furnace furnace) {
        // Check if this factory already exists
        if (factoryExistsAt(center.getLocation())){
            System.out.println("Factory already exists at " + center.getLocation().toString());
        }

        Inventory inv = chest.getBlockInventory();
        List<ItemStack> fItems = Arrays.asList(chest.getBlockInventory().getContents());

        // Get factories with requirements matching those found in the chest.
        // If there are no factories that match, or more than 1, return because that shouldn't happen.
        List<FactoryType> suitables = factoryTypes
                .clone()
                .parallelStream()
                .filter(type -> ItemsUtil.inventoryContains(inv, type.getCreationCost()))
                .collect(Collectors.toList());
        if (suitables.size() != 1) return;
        FactoryType typeToBeMade = suitables.get(0);

        // Now that we know which factory to make, remove the required items from the chest, and register it.
        ItemsUtil.removeItemsFromInv(chest.getBlockInventory(), typeToBeMade.getCreationCost());
        registerFactory(new BasicFactory(typeToBeMade, center.getLocation(), chest.getLocation(), furnace.getLocation()));
    }

}
