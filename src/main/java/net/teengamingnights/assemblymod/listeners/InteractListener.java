package net.teengamingnights.assemblymod.listeners;

import net.teengamingnights.assemblymod.factory.FactoryManager;
import net.teengamingnights.assemblymod.utils.blocks.BlockUtil;
import net.teengamingnights.assemblymod.utils.blocks.FaceDirection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
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
        ItemStack is = e.getItem();
        Action action = e.getAction();

        // Return if they didn't use their hand to interact, or they didn't click a block.
        if (is!=null || action != Action.LEFT_CLICK_BLOCK){
            return;
        }

        // Try and figure out what to do with the click
        Block b = e.getClickedBlock();
        Material type = b.getType();
        BlockFace face = e.getBlockFace();
        boolean isNotFacBlock = !BlockUtil.isFactoryBlock(b);

        if (isNotFacBlock) return;

        switch (type) {

            case CRAFTING_TABLE:
                // Create the factory if it doesn't exist yet.
                createFactory(b, face);
                break;

            case FURNACE:
                // Enable / Disable the factory.
                toggleFactory();
                break;

            case CHEST:
                // Bring up recipe list.
                viewRecipes();
                break;
                
        }

    }

    @EventHandler
    public void onBlockBroken(BlockBreakEvent e){
        if (!BlockUtil.isFactoryBlock(e.getBlock())) return;
        if (!factoryManager.isBlockFac(e.getBlock())) return;

        // The destroyed block was part of a factory, so unregister it.
        factoryManager.unregisterFactory(factoryManager.getFactoryAt(e.getBlock().getLocation()));
    }


    private void createFactory(Block center, BlockFace clickedFace) {
        FaceDirection faceD = FaceDirection.getEquivalent(clickedFace);
        EnumSet<BlockFace> oppositeBFS = faceD.getOppositeBFS();
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
