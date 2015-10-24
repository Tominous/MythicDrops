package com.tealcube.minecraft.common.mythicdrops.inventory;

import javax.annotation.Nullable;

/**
 * Represents an implementation-neutral enchantment on an item.
 */
public interface MythicEnchantment {

    /**
     * Fetches and returns the internal id used by the server to track this enchantment.
     *
     * @return internal id used by the server
     */
    String getId();

    /**
     * Fetches and returns the minimum level that this enchantment can obtain.
     *
     * @return minimum level
     */
    int getMinimumLevel();

    /**
     * Fetches and returns the maximum level that this enchantment can obtain.
     *
     * @return maximum level
     */
    int getMaximumLevel();

    /**
     * Determines if this enchantment is compatible with another enchantment.
     *
     * Returns true if {@code enchantment} is null.
     *
     * @param enchantment MythicEnchantment to check compatibility with, can be null
     * @return if enchantments are compatible
     */
    boolean isCompatibleWith(@Nullable MythicEnchantment enchantment);

    /**
     * Determines if this enchantment can be applied to the given item.
     *
     * Returns false if {@code item} is null.
     *
     * @param item MythicItem to check compatibility with, can be null
     * @return if enchantment can be applied
     */
    boolean canApplyTo(@Nullable MythicItem item);

}
