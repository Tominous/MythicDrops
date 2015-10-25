package com.tealcube.minecraft.common.mythicdrops.inventory;

import java.awt.*;

import javax.annotation.Nullable;

/**
 * Represents an implementation-neutral piece of leather armor used by MythicDrops.
 */
public interface MythicLeatherArmor extends MythicItem {

    /**
     * Fetches and returns the color of this item.
     *
     * @return color of this item
     */
    Color getColor();

    /**
     * Sets the color of this item. A null parameter removes the color.
     *
     * @param color color to set
     * @return this item
     */
    MythicLeatherArmor setColor(@Nullable Color color);

}
