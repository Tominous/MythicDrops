/*
 * This file is part of MythicDrops, licensed under the MIT License (MIT)
 *
 * Copyright (c) 2015 ToppleTheNun <https://github.com/Nunnery>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.tealcube.minecraft.common.mythicdrops.inventory;

import com.google.common.base.Optional;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents an implementation-neutral item used by MythicDrops.
 */
public interface MythicItem {

    /**
     * Fetches and returns the material id of this item.
     *
     * @return material id
     */
    String getMaterial();

    /**
     * Sets the material id of this item and returns this item.
     *
     * @param material material id
     * @return this item
     * @throws NullPointerException     if material is null
     * @throws IllegalArgumentException if material is empty or is not a valid material
     */
    MythicItem setMaterial(@Nonnull String material);

    /**
     * Fetches and returns the amount in this item.
     *
     * @return amount
     */
    int getAmount();

    /**
     * Sets the amount in this item.
     *
     * @param amount amount to set
     * @return this item
     * @throws IllegalArgumentException if amount is less than 0 or greater than max stack size
     */
    MythicItem setAmount(int amount);

    /**
     * Calculates the durability remaining and returns it. 0 means that the item is about to break.
     *
     * @return durability remaining on the item
     */
    short getDurabilityRemaining();

    /**
     * Sets the durability remaining on the item.
     *
     * @param durabilityRemaining new durability remaining
     * @return this item
     * @throws IllegalArgumentException if durabilityRemaining is below 0 or above maximumDurability
     */
    MythicItem setDurabilityRemaining(short durabilityRemaining);

    /**
     * Fetches and returns the maximum amount of durability that this item can have.
     *
     * @return maximum amount of durability
     */
    short getMaximumDurability();

    /**
     * Fetches and returns the display name of this item. Returns an empty {@code Optional<String>} if it doesn't have
     * one.
     *
     * @return Optional wrapped around the display name
     */
    Optional<String> getDisplayName();

    /**
     * Sets the display name of this item. A null parameter removes the name.
     *
     * @param displayName display name to set
     * @return this item
     */
    MythicItem setDisplayName(@Nullable String displayName);

    /**
     * Fetches and returns the lore of this item. Returns an empty {@code List<String>} if there is no lore.
     *
     * @return unmodifiable List of item's lore
     */
    List<String> getLore();

    /**
     * Sets the lore of this item. A null parameter removes the lore.
     *
     * @param lore lore to set
     * @return this item
     */
    MythicItem setLore(@Nullable List<String> lore);

    /**
     * Fetches and returns the enchantments on this item. Returns an empty {@code Map<MythicEnchantment, Integer>} if
     * there are no enchantments.
     *
     * @return unmodifiable Map of item's enchantments
     */
    Map<MythicEnchantment, Integer> getEnchantments();

    /**
     * Sets the enchantments on this item. A null parameter removes the enchantments.
     *
     * @param enchantments enchantments to set
     * @return this item
     */
    MythicItem setEnchantments(@Nullable Map<MythicEnchantment, Integer> enchantments);

    /**
     * Gets the author of this item.
     *
     * Returns an empty string if this item cannot have an author.
     *
     * @return author of this item
     */
    String getAuthor();

    /**
     * Sets the author of this item. A null parameter removes the author.
     *
     * @param author author to set
     * @return this item
     */
    MythicItem setAuthor(@Nullable String author);

}
