package net.teengamingnights.assemblymod.listeners;

import net.teengamingnights.assemblymod.factory.FactoryManager;
import net.teengamingnights.assemblymod.utils.blocks.BlockUtil;
import net.teengamingnights.assemblymod.utils.blocks.FaceDirection;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumSet;

public class InteractListener implements Listener {

    private FactoryManager factoryManager;

    public InteractListener(FactoryManager factoryManager) {
        this.factoryManager = factoryManager;
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {

        /*
         Simplified and solidified logic (hopefully) on what is a factory,
         and moved it over to it's own class with a bunch of other helpful methods
          */

        ItemStack is = e.getItem();

        // Return if they didn't use their hand to interact
        if (is!=null){
            return;
        }
        Action action = e.getAction();

        if (action != Action.LEFT_CLICK_BLOCK) return;

        /*
         The weird order of variables and if statements
         is to prioritize returning if it is not a left click from a stick
          */

        Block b = e.getClickedBlock();
        Material type = b.getType();
        BlockFace face = e.getBlockFace();
        boolean isNotFacBlock = !BlockUtil.isFactoryBlock(b);

        if (isNotFacBlock) return;

        switch (type) {

            case CRAFTING_TABLE:
                createFactory(b, face);
                break;

            case FURNACE:
                toggleFactory();
                break;

            case CHEST:
                viewRecipes();
                break;
                
        }

    }

    private void createFactory(Block center, BlockFace clickedFace) {

        FaceDirection faceD = FaceDirection.getEquivalent(clickedFace);
        EnumSet<BlockFace> oppositeBFS = faceD.getOppositeBFS();

        /*
        See FactoryManager/Factory,
        if your curious as to why I added the chests and furnace as parameters to factory manager
        */

        if (BlockUtil.isAttemptedFactory(center, clickedFace)) {

            if (factoryManager.isBlockFac(center)) return;

            Chest chestInstance = BlockUtil.getRelativeChests(center, oppositeBFS).get(0);
            Furnace furnInstance = BlockUtil.getRelativeFurns(center, oppositeBFS).get(0);
            factoryManager.createFactory(center, chestInstance, furnInstance);

        }

    }

    private void toggleFactory() {

        // TODO: stub method - have to implement click behavior later
        return;

    }

    private void viewRecipes() {

        // TODO: stub method - have to implement click behavior later
        return;

    }

}
