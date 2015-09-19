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
package com.tealcube.minecraft.sponge.mythicdrops.impl.tiers;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import com.tealcube.minecraft.sponge.mythicdrops.api.tiers.Tier;
import com.tealcube.minecraft.sponge.mythicdrops.common.MythicEnchantment;

import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * Represents a "tier" of items that can be dropped.
 *
 * Implementation of {@link com.tealcube.minecraft.sponge.mythicdrops.api.tiers.Tier}.
 *
 * Concept was derived from the likes of Diablo and Torchlight.
 *
 * @author Richard Harrah
 * @version 09172015
 */
public final class TierImpl implements Tier {

    private final String id;
    private final String name;
    private final TextColor color;
    private final List<String> description;
    private final Map<String, Integer> lore;
    private final int minimumLore;
    private final int maximumLore;
    private final Set<MythicEnchantment> enchantments;
    private final Map<MythicEnchantment, Integer> randomEnchantments;
    private final int minimumRandomEnchantments;
    private final int maximumRandomEnchantments;
    private final double minimumDurabilityPercentage;
    private final double maximumDurabilityPercentage;

    TierImpl(String id, String name, TextColor color, List<String> description, Map<String, Integer> lore,
             int minimumLore, int maximumLore, Set<MythicEnchantment> enchantments,
             Map<MythicEnchantment, Integer> randomEnchantments, int minimumRandomEnchantments,
             int maximumRandomEnchantments, double minimumDurabilityPercentage,
             double maximumDurabilityPercentage) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.description = description;
        this.lore = lore;
        this.minimumLore = minimumLore;
        this.maximumLore = maximumLore;
        this.enchantments = enchantments;
        this.randomEnchantments = randomEnchantments;
        this.minimumRandomEnchantments = minimumRandomEnchantments;
        this.maximumRandomEnchantments = maximumRandomEnchantments;
        this.minimumDurabilityPercentage = minimumDurabilityPercentage;
        this.maximumDurabilityPercentage = maximumDurabilityPercentage;
    }

    /**
     * A simple text unique identifier for this particular tier.
     *
     * This field is only ever used internally and should never be modified.
     *
     * @return identifier for this tier
     */
    @Override
    public String id() {
        return id;
    }

    /**
     * A human readable name for this particular tier. Usually something like "common" or "rare".
     *
     * @return name of the tier
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * The color of the tier's name. Similar to how legendary items in Diablo are orange.
     *
     * @return color of the tier's name
     */
    @Override
    public TextColor color() {
        return color;
    }

    /**
     * An unmodifiable {@code List} of {@code String}s that goes on every item of this tier.
     *
     * This field contains Strings that may contain the newline character (\n). Strings that contain this character will
     * be split on the character and the size of the resulting list will be changed, not this list.
     *
     * @return description of this tier
     */
    @Override
    public List<String> description() {
        return description;
    }

    /**
     * An unmodifiable {@code Map} of {@code String}s and their weights that can go on any items from this tier.
     *
     * A line of lore is a String that may contain the newline character (\n). Strings that contain this character will
     * be split on the character and the size of the resulting list will be changed, not the value in this Map.
     *
     * @return lore of this tier
     */
    @Override
    public Map<String, Integer> lore() {
        return lore;
    }

    /**
     * Represents the minimum number of values from the {@link #lore()} {@code Map} that will exist for items from this
     * tier.
     *
     * @return minimum lines of lore
     */
    @Override
    public int minimumLore() {
        return minimumLore;
    }

    /**
     * Represents the maximum number of values from the {@link #lore()} {@code Map} that will exist for items from this
     * tier.
     *
     * @return maximum lines of lore
     */
    @Override
    public int maximumLore() {
        return maximumLore;
    }

    /**
     * An unmodifiable {@code Set} of {@code HoardEnchantment}s that goes on every item of this tier.
     *
     * By default, these will be applied regardless of the enchantment's ability to normally be placed on the item.
     *
     * @return Enchantments to be placed on items of this tier
     */
    @Override
    public Set<MythicEnchantment> enchantments() {
        return Collections.unmodifiableSet(enchantments);
    }

    /**
     * An unmodifiable {@code Map} of {@code HoardEnchantment}s and their weights that can go on any items from this
     * tier.
     *
     * By default, these can be applied regardless of the enchantment's ability to normally be placed on the item.
     *
     * @return Enchantments that can be placed on items of this tier
     */
    @Override
    public Map<MythicEnchantment, Integer> randomEnchantments() {
        return Collections.unmodifiableMap(randomEnchantments);
    }

    /**
     * Represents the minimum number of values from the {@link #randomEnchantments()} {@code Map} that will exist for
     * items from this tier.
     *
     * @return minimum random Enchantments
     */
    @Override
    public int minimumRandomEnchantments() {
        return minimumRandomEnchantments;
    }

    /**
     * Represents the maximum number of values from the {@link #randomEnchantments()} {@code Map} that will exist for
     * items from this tier.
     *
     * @return maximum random Enchantments
     */
    @Override
    public int maximumRandomEnchantments() {
        return maximumRandomEnchantments;
    }

    /**
     * Represents the minimum durability percentage for items from this tier.
     *
     * For example, if an item has a maximum durability of 255 and the value of this function is 0.75, the minimum
     * durability calculation becomes (255 - (0.75 * 255)), resulting in all items from this tier having a minimum
     * durability of 191.
     *
     * @return minimum durability percentage
     */
    @Override
    public double minimumDurabilityPercentage() {
        return minimumDurabilityPercentage;
    }

    /**
     * Represents the maximum durability percentage for items from this tier.
     *
     * For example, if an item has a maximum durability of 255 and the value of this function is 0.75, the maximum
     * durability calculation becomes (255 - (0.75 * 255)), resulting in all items from this tier having a maximum
     * durability of 191.
     *
     * @return maximum durability percentage
     */
    @Override
    public double maximumDurabilityPercentage() {
        return maximumDurabilityPercentage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TierImpl tier = (TierImpl) o;
        return Objects.equal(minimumLore, tier.minimumLore) &&
                Objects.equal(maximumLore, tier.maximumLore) &&
                Objects.equal(minimumRandomEnchantments, tier.minimumRandomEnchantments) &&
                Objects.equal(maximumRandomEnchantments, tier.maximumRandomEnchantments) &&
                Objects.equal(minimumDurabilityPercentage, tier.minimumDurabilityPercentage) &&
                Objects.equal(maximumDurabilityPercentage, tier.maximumDurabilityPercentage) &&
                Objects.equal(id, tier.id) &&
                Objects.equal(name, tier.name) &&
                Objects.equal(color, tier.color) &&
                Objects.equal(description, tier.description) &&
                Objects.equal(lore, tier.lore) &&
                Objects.equal(enchantments, tier.enchantments) &&
                Objects.equal(randomEnchantments, tier.randomEnchantments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, color, description, lore, minimumLore, maximumLore, enchantments,
                randomEnchantments, minimumRandomEnchantments, maximumRandomEnchantments,
                minimumDurabilityPercentage, maximumDurabilityPercentage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(@Nonnull Tier o) {
        return (id == null) ? -1 : ((o.id() == null) ? 1 : id.compareTo(o.id()));
    }

    /**
     * {@inheritDoc}
     */
    public class Builder implements Tier.Builder {

        private String id;
        private String name;
        private TextColor color = TextColors.WHITE;
        private List<String> description = new ArrayList<>();
        private Map<String, Integer> lore = new HashMap<>();
        private int minimumLore = 0;
        private int maximumLore = 0;
        private Set<MythicEnchantment> enchantments = new HashSet<>();
        private Map<MythicEnchantment, Integer> randomEnchantments = new HashMap<>();
        private int minimumRandomEnchantments = 0;
        private int maximumRandomEnchantments = 0;
        private double minimumDurabilityPercentage = 1.0;
        private double maximumDurabilityPercentage = 1.0;

        public Builder(@Nonnull String id) {
            Preconditions.checkNotNull(id);
            this.id = id;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setColor(TextColor color) {
            this.color = color;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setDescription(List<String> description) {
            this.description = description;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setLore(Map<String, Integer> lore) {
            this.lore = lore;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setMinimumLore(int minimumLore) {
            this.minimumLore = minimumLore;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setMaximumLore(int maximumLore) {
            this.maximumLore = maximumLore;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setEnchantments(Set<MythicEnchantment> enchantments) {
            this.enchantments = enchantments;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setRandomEnchantments(Map<MythicEnchantment, Integer> randomEnchantments) {
            this.randomEnchantments = randomEnchantments;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setMinimumRandomEnchantments(int minimumRandomEnchantments) {
            this.minimumRandomEnchantments = minimumRandomEnchantments;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setMaximumRandomEnchantments(int maximumRandomEnchantments) {
            this.maximumRandomEnchantments = maximumRandomEnchantments;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setMinimumDurabilityPercentage(double minimumDurabilityPercentage) {
            this.minimumDurabilityPercentage = minimumDurabilityPercentage;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder setMaximumDurabilityPercentage(double maximumDurabilityPercentage) {
            this.maximumDurabilityPercentage = maximumDurabilityPercentage;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Tier build() {
            return new TierImpl(id, name != null ? name : id, color, description, lore, minimumLore, maximumLore,
                    enchantments, randomEnchantments, minimumRandomEnchantments, maximumRandomEnchantments,
                    minimumDurabilityPercentage, maximumDurabilityPercentage);
        }

    }

}
