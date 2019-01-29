package net.teengamingnights.assemblymod.utils.items;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemsUtil {

    public static void removeItemsFromInv(Inventory inventory, Collection<ItemStack> itemStacks) {
        HashMap<Material, Integer> toRemove = new HashMap<Material, Integer>();
        for(ItemStack item : itemStacks){
            if(!toRemove.containsKey(item.getType()))
                toRemove.put(item.getType(), item.getAmount());
            else
                toRemove.put(item.getType(), toRemove.get(item.getType())+item.getAmount());
        }
        for(ItemStack item : inventory.getContents()){
            if(item==null) continue;
            if(toRemove.containsKey(item.getType())){
                if(item.getAmount() <= toRemove.get(item.getType())){
                    toRemove.put(item.getType(), toRemove.get(item.getType())-item.getAmount());
                    item.setAmount(0);
                } else{
                    item.setAmount(item.getAmount()-toRemove.get(item.getType()));
                    toRemove.put(item.getType(), 0);
                }
            }
        }
    }

    public static boolean inventoryContains(Inventory inventory, Collection<ItemStack> itemStacks){
        HashMap<Material, Integer> toRemove = new HashMap<Material, Integer>();
        for(ItemStack item : itemStacks){
            if(!toRemove.containsKey(item.getType()))
                toRemove.put(item.getType(), item.getAmount());
            else
                toRemove.put(item.getType(), toRemove.get(item.getType())+item.getAmount());
        }
        for(ItemStack item : inventory.getContents()){
            if (item == null) continue;
            if(toRemove.containsKey(item.getType())){
                if(item.getAmount() <= toRemove.get(item.getType())){
                    toRemove.put(item.getType(), toRemove.get(item.getType())-item.getAmount());
                } else{
                    toRemove.put(item.getType(), 0);
                }
            }
        }
        for(int i : toRemove.values()) if (i!=0) return false;
        return true;
    }

    public static boolean inventoryContains(ItemStack[] inventory, Collection<ItemStack> itemStacks){
        HashMap<Material, Integer> toRemove = new HashMap<Material, Integer>();
        for(ItemStack item : itemStacks){
            if(!toRemove.containsKey(item.getType()))
                toRemove.put(item.getType(), item.getAmount());
            else
                toRemove.put(item.getType(), toRemove.get(item.getType())+item.getAmount());
        }
        for(ItemStack item : inventory){
            if (item == null) continue;
            if(toRemove.containsKey(item.getType())){
                if(item.getAmount() <= toRemove.get(item.getType())){
                    toRemove.put(item.getType(), toRemove.get(item.getType())-item.getAmount());
                } else{
                    toRemove.put(item.getType(), 0);
                }
            }
        }
        for(int i = 0; i < toRemove.size(); i++){
            if (toRemove.get(i)!=0) return false;
        }
        return true;
    }

    public static List<ItemStack> sanitizeISList(Collection<ItemStack> itemStacks) {

        return itemStacks.parallelStream().filter(Predicate.isEqual(null)).collect(Collectors.toList());

    }

}
