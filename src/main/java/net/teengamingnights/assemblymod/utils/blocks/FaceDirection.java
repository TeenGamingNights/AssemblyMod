package net.teengamingnights.assemblymod.utils.blocks;

import org.bukkit.block.BlockFace;

import java.util.EnumSet;

/*
    Makes more sense to use X / Y than EW and NS.
    Imagine as if you were looking down upon the factory from above.
 */
public enum FaceDirection {
    X(BlockFace.EAST, BlockFace.WEST),
    Y(BlockFace.NORTH, BlockFace.SOUTH);

    // Added equivalent BlockFaces for cleaner translation of the two types of enums
    // also moved it with block utils bc I felt it fit here
    private final BlockFace firstEqual;
    private final BlockFace lastEqual;

    FaceDirection(BlockFace face1, BlockFace face2) {
        firstEqual = face1;
        lastEqual = face2;
    }

    public static FaceDirection getEquivalent(BlockFace face) {

        for (FaceDirection dir : FaceDirection.values()) {
            if (dir.getBlockFaces().contains(face)) return dir;
        }

        // Non-cardinal directions are treated as if they are EW
        return X;

    }

    public EnumSet<BlockFace> getBlockFaces() {

        return EnumSet.of(this.firstEqual, this.lastEqual);

    }

    public EnumSet<BlockFace> getOppositeBFS() {

        return EnumSet.of(getOpposite().firstEqual, getOpposite().lastEqual);

    }

    public FaceDirection getOpposite() {

        return this.equals(X) ? Y : X;

    }

}
