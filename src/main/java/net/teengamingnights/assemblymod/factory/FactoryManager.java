package net.teengamingnights.assemblymod.factory;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FactoryManager {
    private List<Factory> factories = new ArrayList<>();

    public Factory[] getRegisteredFactories() {
        Factory[] ret = new Factory[factories.size()];
        factories.toArray(ret);
        return ret;
    }

    public void registerFactory(Factory factory) { factories.add(factory); }
    public void unregisterFactory(Factory factory) { factories.remove(factory); }

    public void createFactory(List<ItemStack> items, Block center) {
        items = new ArrayList<>(items);
        items.removeAll(Collections.singleton(null));
        for (Factory factory : factories) {
            // Thanks Altii!
            List<ItemStack> requirements = factory.getRequiredMaterials();
            if (requirements.size() == items.size()) {
                for (int i = 0; i < requirements.size(); i++) {
                    if (!requirements.get(i).equals(items.get(i))) {
                        return;
                    }
                }
                System.out.println("[DEBUG] Created factory at " + center.getLocation().toString());
            }
        }
    }
}
