package net.teengamingnights.assemblymod.factory;

import net.teengamingnights.assemblymod.utils.CollectionUtils;
import net.teengamingnights.assemblymod.utils.items.ItemsUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void registerFactory(Factory factory) {
        factories.add(factory);
    }

    public void unregisterFactory(Factory factory) {
        factories.remove(factory);
    }

    public boolean isBlockFac(Block centerBlock) {
        return getRegFactories()
                .parallelStream()
                .anyMatch(factory -> factory.getCenter().equals(centerBlock.getLocation()));
    }

    // I made it so that the chest and furnace can be added to the factory object for future manipulation

    public void createFactory(Block center, Chest chest, Furnace furnace) {
        // Check if this factory already exists
        if (factoryExistsAt(center.getLocation())){
            System.out.println("Factory already exists at " + center.getLocation().toString());
        }

        // Check if the chest is empty
        // You have to iterate over it because the array will always have 27 elements, but they will be null if empty.
        List<ItemStack> fItems = Arrays.asList(chest.getBlockInventory().getContents());
        boolean empty = true;
        for (ItemStack is : fItems){
            if (is != null){
                empty = false;
                break;
            }
        }
        if (empty) return;

        // Get factories with requirements matching those found in the chest.
        // If there are no factories that match, or more than 1, return because that shouldn't happen.
        List<FactoryType> suitables = factoryTypes
                .clone()
                .parallelStream()
                .filter(type -> requirementsMet(type, fItems))
                .collect(Collectors.toList());
        if (suitables.size() != 1) return;
        FactoryType typeToBeMade = suitables.get(0);

        // Now that we know which factory to make, remove the required items from the chest, and print some juicy debug
        ItemsUtil.removeItemsFromInv(chest.getBlockInventory(), typeToBeMade.getCreationCost());
        System.out.println("[DEBUG] Created a " + typeToBeMade.getName() + " factory at " + center.getLocation().toString());
    }

    /*
    Check if the List provided contains the materials required to create a factory. Due to how ItemStacks work,
    you need the exact number of materials in the chest in the exact same configuration (but not necessarily the same order)
    e.g. if the requirements are (10 cobble, 4 wood) this will return false if the chest contains (2 stacks of 5 cobble, 4 wood),
    but will return true if the chest contains (10 cobble, 4 wood, 8 wool). so. TODO: find a workaround for this crap.
     */
    private boolean requirementsMet(FactoryType type, List<ItemStack> materials) {
        List<ItemStack> requirements = type.getCreationCost();
        return materials.containsAll(requirements);
    }

}
