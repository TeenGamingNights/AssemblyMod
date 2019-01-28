package net.teengamingnights.assemblymod.listeners;

import net.teengamingnights.assemblymod.factory.Factory;
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
import org.bukkit.event.inventory.FurnaceBurnEvent;
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
                if(factoryManager.factoryExistsAt(b.getLocation()))
                    toggleFactory(b.getLocation());
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

        // The destroyed block was part of a factory, so unregister it and do the refund
        Factory fac = factoryManager.getFactoryAt(e.getBlock().getLocation());
        factoryManager.unregisterFactory(fac);
        for(ItemStack item : fac.getType().getRefund(fac.getHealth()))
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), item);
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent e){
        if(factoryManager.isBlockFac(e.getBlock())){
            // The furnace that just got coal is part of a factory and shouldn't burn it
            //e.setCancelled(true);
        }
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

    private void toggleFactory(Location loc) {
        Factory f = factoryManager.getFactoryAt(loc);
        f.toggle();
    }

    private void viewRecipes() {

        // TODO: stub method - have to implement click behavior later
        return;

    }

}
