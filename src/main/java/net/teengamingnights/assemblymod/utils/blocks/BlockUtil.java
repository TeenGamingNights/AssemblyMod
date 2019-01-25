package net.teengamingnights.assemblymod.utils.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;

import java.util.*;

public class BlockUtil {

    private static final EnumSet<Material> factoryMaterials
            = EnumSet.of(Material.FURNACE, Material.CRAFTING_TABLE, Material.CHEST);

    public static boolean isAttemptedFactory(Block center, BlockFace clickedFace) {
        /*
        The logic behind this method is that is gets the opposite faces to the one you clicked,
        as according to how they're registered in FaceDirection. Also, any non-cardinal face direction is treated
        on as if it is on the X-axis (maybe in the future,
        we'll try and make it so that up and down can be accessed non-jankily).
         */

        EnumSet<BlockFace> opposites = FaceDirection.getEquivalent(clickedFace).getOppositeBFS();

        /*
        Then we get the relative blocks on those faces, and then we see if we can find any chests/furnaces.
         */

        List<Block> oppoBlocks = getRelatives(center, opposites);
        List<Chest> chestsFound = getChsFromList(oppoBlocks);
        List<Furnace> furnsFound = getFurnsFromList(oppoBlocks);

        /*
        And then, we see if the list has exactly one chest/furnace. Only under these circumstances will the center block
        and surrounding blocks be classified as a factory.
         */

        boolean isOneChest = !chestsFound.isEmpty() && chestsFound.size() < 2;
        boolean isOneFurn = !chestsFound.isEmpty() && furnsFound.size() < 2;

        return isOneChest && isOneFurn;
    }

    public static boolean isFactoryBlock(Block block) {
        Material type = block.getType();

        return factoryMaterials.contains(type);
    }

    /*
    Feel free to make the private methods public if you ever need them, especially getRelatives.
     */

    public static List<Chest> getRelativeChests(Block block, Collection<BlockFace> blockFaces) {
        List<Block> relatives = getRelatives(block, blockFaces);

        return getChsFromList(relatives);
    }

    public static List<Furnace> getRelativeFurns(Block block, Collection<BlockFace> blockFaces) {
        List<Block> relatives = getRelatives(block, blockFaces);

        return getFurnsFromList(relatives);
    }

    private static List<Block> getRelatives(Block block, Collection<BlockFace> blockFaces) {
        List<Block> relatives = new ArrayList<>(blockFaces.size());

        for (BlockFace blockFace : blockFaces) {

            relatives.add(block.getRelative(blockFace));

        }

        return relatives;
    }

    private static List<Chest> getChsFromList(Collection<Block> blocks) {
        List<Chest> chests = new ArrayList<>(blocks.size());

        for (Block block : blocks) {

            Material type = block.getType();
            Material chest = Material.CHEST;

            if (type.equals(chest)) chests.add((Chest) block.getState());

        }

        return chests;
    }

    private static List<Furnace> getFurnsFromList(Collection<Block> blocks) {
        List<Furnace> furnaces = new ArrayList<>(blocks.size());

        for (Block block : blocks) {

            Material type = block.getType();
            Material furnace = Material.FURNACE;

            if (type.equals(furnace)) furnaces.add((Furnace) block.getState());

        }

        return furnaces;
    }

    private static boolean areUniqueBlocks(Collection<Block> blocks) {
        Set<Material> materials = new HashSet<>(blocks.size());

        for (Block block : blocks) {

            materials.add(block.getType());

        }

        return materials.size() == blocks.size();
    }

}
