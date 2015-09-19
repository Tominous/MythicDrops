/*
 * This file is part of MythicDrops, licensed under the MIT License (MIT)
 *
 * Copyright (c) 2015 Richard Harrah
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
package com.tealcube.minecraft.sponge.mythicdrops.api.tiers;

import com.tealcube.minecraft.sponge.mythicdrops.common.MythicEnchantment;

import org.spongepowered.api.text.format.TextColor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a "tier" of items that can be dropped.
 *
 * Concept was derived from the likes of Diablo and Torchlight.
 *
 * @author Richard Harrah
 * @version 09162015
 */
public interface Tier extends Comparable<Tier> {

    /**
     * A simple text unique identifier for this particular tier.
     *
     * This field is only ever used internally and should never be modified.
     *
     * @return identifier for this tier
     */
    String id();

    /**
     * A human readable name for this particular tier. Usually something like "common" or "rare".
     *
     * @return name of the tier
     */
    String name();

    /**
     * The color of the tier's name. Similar to how legendary items in Diablo are orange.
     *
     * @return color of the tier's name
     */
    TextColor color();

    /**
     * An unmodifiable {@code List} of {@code String}s that goes on every item of this tier.
     *
     * This field contains Strings that may contain the newline character (\n). Strings that contain this character will
     * be split on the character and the size of the resulting list will be changed, not this list.
     *
     * @return description of this tier
     */
    List<String> description();

    /**
     * An unmodifiable {@code Map} of {@code String}s and their weights that can go on any items from this tier.
     *
     * A line of lore is a String that may contain the newline character (\n). Strings that contain this character will
     * be split on the character and the size of the resulting list will be changed, not the value in this Map.
     *
     * @return lore of this tier
     */
    Map<String, Integer> lore();

    /**
     * Represents the minimum number of values from the {@link #lore()} {@code Map} that will exist for items from this
     * tier.
     *
     * @return minimum lines of lore
     */
    int minimumLore();

    /**
     * Represents the maximum number of values from the {@link #lore()} {@code Map} that will exist for items from this
     * tier.
     *
     * @return maximum lines of lore
     */
    int maximumLore();

    /**
     * An unmodifiable {@code Set} of {@code HoardEnchantment}s that goes on every item of this tier.
     *
     * By default, these will be applied regardless of the enchantment's ability to normally be placed on the item.
     *
     * @return Enchantments to be placed on items of this tier
     */
    Set<MythicEnchantment> enchantments();

    /**
     * An unmodifiable {@code Map} of {@code HoardEnchantment}s and their weights that can go on any items from this
     * tier.
     *
     * By default, these can be applied regardless of the enchantment's ability to normally be placed on the item.
     *
     * @return Enchantments that can be placed on items of this tier
     */
    Map<MythicEnchantment, Integer> randomEnchantments();

    /**
     * Represents the minimum number of values from the {@link #randomEnchantments()} {@code Map} that will exist for
     * items from this tier.
     *
     * @return minimum random Enchantments
     */
    int minimumRandomEnchantments();

    /**
     * Represents the maximum number of values from the {@link #randomEnchantments()} {@code Map} that will exist for
     * items from this tier.
     *
     * @return maximum random Enchantments
     */
    int maximumRandomEnchantments();

    /**
     * Represents the minimum durability percentage for items from this tier.
     *
     * For example, if an item has a maximum durability of 255 and the value of this function is 0.75, the minimum
     * durability calculation becomes (255 - (0.75 * 255)), resulting in all items from this tier having a minimum
     * durability of 191.
     *
     * @return minimum durability percentage
     */
    double minimumDurabilityPercentage();

    /**
     * Represents the maximum durability percentage for items from this tier.
     *
     * For example, if an item has a maximum durability of 255 and the value of this function is 0.75, the maximum
     * durability calculation becomes (255 - (0.75 * 255)), resulting in all items from this tier having a maximum
     * durability of 191.
     *
     * @return maximum durability percentage
     */
    double maximumDurabilityPercentage();

    /**
     * Represents an Object with the ability to create a {@code Tier}.
     *
     * @author Richard Harrah
     * @version 09172015
     */
    interface Builder {

        /**
         * Sets the name of the resulting {@code Tier}.
         * @param name name
         * @return this Builder
         */
        Builder setName(String name);

        /**
         * Sets the color of the resulting {@code Tier}.
         * @param color color
         * @return this Builder
         */
        Builder setColor(TextColor color);

        /**
         * Sets the description of the resulting {@code Tier}.
         * @param description description
         * @return this Builder
         */
        Builder setDescription(List<String> description);

        /**
         * Sets the lore of the resulting {@code Tier}.
         * @param lore lore
         * @return this Builder
         */
        Builder setLore(Map<String, Integer> lore);

        /**
         * Sets the minimum lore of the resulting {@code Tier}.
         * @param minimumLore minimumLore
         * @return this Builder
         */
        Builder setMinimumLore(int minimumLore);

        /**
         * Sets the maximumLore of the resulting {@code Tier}.
         * @param maximumLore maximumLore
         * @return this Builder
         */
        Builder setMaximumLore(int maximumLore);

        /**
         * Sets the enchantments of the resulting {@code Tier}.
         * @param enchantments enchantments
         * @return this Builder
         */
        Builder setEnchantments(Set<MythicEnchantment> enchantments);

        /**
         * Sets the random enchantments of the resulting {@code Tier}.
         * @param randomEnchantments random enchantments
         * @return this Builder
         */
        Builder setRandomEnchantments(Map<MythicEnchantment, Integer> randomEnchantments);

        /**
         * Sets the minimum random enchantments of the resulting {@code Tier}.
         * @param minimumRandomEnchantments minimum random enchantments
         * @return this Builder
         */
        Builder setMinimumRandomEnchantments(int minimumRandomEnchantments);

        /**
         * Sets the maximum random enchantments of the resulting {@code Tier}.
         * @param maximumRandomEnchantments maximum random enchantments
         * @return this Builder
         */
        Builder setMaximumRandomEnchantments(int maximumRandomEnchantments);

        /**
         * Sets the minimum durability percentage of the resulting {@code Tier}.
         * @param minimumDurabilityPercentage minimum durability percentage
         * @return this Builder
         */
        Builder setMinimumDurabilityPercentage(double minimumDurabilityPercentage);

        /**
         * Sets the maximum durability percentage of the resulting {@code Tier}.
         * @param maximumDurabilityPercentage maximum durability percentage
         * @return this Builder
         */
        Builder setMaximumDurabilityPercentage(double maximumDurabilityPercentage);

        /**
         * Builds the Tier using the given parameters and returns it.
         * @return Tier with given options
         */
        Tier build();

    }

}
