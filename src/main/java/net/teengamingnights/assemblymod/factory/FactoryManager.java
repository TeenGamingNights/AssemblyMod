package net.teengamingnights.assemblymod.factory;

import net.teengamingnights.assemblymod.utils.items.ItemsUtil;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FactoryManager {

    private List<Factory> factories = new ArrayList<>();

    public List<Factory> getRegFactories() { return new ArrayList<>(factories); }

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

        /*
        I removed the nested for-loop in favor for a much cleaner (and theoretically faster) alternative.
        I also made it so that the chest will remove the ingredients on factory creation.

        The new way a suitable factory is searched for is it takes a list of all the factories (as it did before),
        and removes the ones that do not match the ingredients in the chest).
        At the end, if all the factories have been iterated upon and there is only one left, that will be the factory
        that gets created.
         */


        /*
         Imma be real honest actually, I just realize that the instances of Factories and the factory templates I've been
         confusing the whole time so TODO: fix factory material logic.
         I would do this now, but I'm tired out bc I've been coding for like 4 hours (which is honestly all for nothing bc
         I just rewrote perfectly good code FUCK MY LIFE. /rant
          */

        List<ItemStack> fItems = Arrays.asList(chest.getBlockInventory().getContents());
        fItems.removeIf(Predicate.isEqual(null));

        if (fItems.isEmpty()) return;

        List<Factory> suitables = new ArrayList<>(factories);
        suitables.removeIf(factory -> !requirementsMet(factory, fItems));

        if (suitables.size() != 1) return;
        Factory factory = suitables.get(0);

        ItemsUtil.removeItemsFromInv(chest.getBlockInventory(), factory.getRequiredMaterials());
        System.out.println("[DEBUG] Created a " + factory.getType().getName() + " factory at " + center.getLocation().toString());

    }

    private boolean requirementsMet(Factory factory, List<ItemStack> materials) {

        List<ItemStack> requirements = factory.getRequiredMaterials();

        return materials.containsAll(requirements);

    }

}
