package net.teengamingnights.assemblymod.utils.items;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemsUtil {

    public static void removeItemsFromInv(Inventory inventory, Collection<ItemStack> itemStacks) {
        itemStacks.forEach(inventory::remove);
    }

    public static List<ItemStack> sanitizeISList(Collection<ItemStack> itemStacks) {
        return itemStacks.parallelStream().filter(Predicate.isEqual(null)).collect(Collectors.toList());
    }

}
