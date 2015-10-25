package com.tealcube.minecraft.common.mythicdrops.inventory;

import javax.annotation.Nullable;

/**
 * Represents an implementation-neutral skull used by MythicDrops.
 */
public interface MythicSkull extends MythicItem {

    /**
     * Fetches and returns the owner of this item.
     *
     * @return owner of this item
     */
    String getOwner();

    /**
     * Sets the owner of this item. A null parameter removes the owner.
     *
     * @param owner owner to set
     * @return this item
     */
    MythicSkull setOwner(@Nullable String owner);

}
