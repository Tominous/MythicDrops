package com.tealcube.minecraft.common.mythicdrops.inventory;

/**
 * Represents an implementation-neutral map used by MythicDrops.
 */
public interface MythicMap extends MythicItem {

    /**
     * Fetches and returns if this item is scalable.
     *
     * @return if scalable
     */
    boolean isScalable();

    /**
     * Sets whether or not this map is scalable.
     *
     * @param scalable scalable to set
     * @return this item
     */
    MythicMap setScalable(boolean scalable);

}
