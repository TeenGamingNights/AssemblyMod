package net.teengamingnights.assemblymod.listeners;

import net.teengamingnights.assemblymod.factory.BlockType;
import net.teengamingnights.assemblymod.factory.FaceDirection;
import net.teengamingnights.assemblymod.factory.FactoryManager;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class InteractListener implements Listener {

    private FactoryManager factoryManager;

    public InteractListener(FactoryManager factoryManager) {
        this.factoryManager = factoryManager;
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {
        Block b = e.getClickedBlock();
        BlockFace face = e.getBlockFace();

        if (b.getType() == Material.CRAFTING_TABLE) {
            Block furnace;
            Block chest;
            if (face == BlockFace.NORTH || face == BlockFace.SOUTH) {
                furnace = furnaceOrChest(FaceDirection.Y, BlockType.FURNACE, b);
                chest = furnaceOrChest(FaceDirection.Y, BlockType.CHEST, b);
            } else if (face == BlockFace.EAST || face == BlockFace.WEST) {
                furnace = furnaceOrChest(FaceDirection.X, BlockType.FURNACE, b);
                chest = furnaceOrChest(FaceDirection.X, BlockType.CHEST, b);
            } else {
                return;
            }

            if ((furnace != null && chest != null) && furnace != chest) {
                Chest chestInstance = (Chest) chest.getState();
                List<ItemStack> items = Arrays.asList(chestInstance.getBlockInventory().getContents());
                factoryManager.createFactory(items, b);
            }
        }
    }

    private Block furnaceOrChest(FaceDirection direction, BlockType type, Block center) {
        World w = center.getWorld();
        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();

        Block left;
        Block right;

        switch (direction) {
            case Y:
                left = w.getBlockAt(x + 1, y, z);
                right = w.getBlockAt(x - 1, y, z);
                break;
            case X:
                left = w.getBlockAt(x, y, z + 1);
                right = w.getBlockAt(x, y, z - 1);
                break;
            default:
                return null;
        }
        switch (type) {
            case FURNACE:
                if (left.getType() == Material.FURNACE)
                    return left;
                if (right.getType() == Material.FURNACE)
                    return right;
                break;
            case CHEST:
                if (left.getType() == Material.CHEST)
                    return left;
                if (right.getType() == Material.CHEST)
                    return right;
        }
        return null;
    }
}
