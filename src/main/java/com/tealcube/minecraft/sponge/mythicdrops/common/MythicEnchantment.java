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
package com.tealcube.minecraft.sponge.mythicdrops.common;

import com.google.common.base.Objects;
import org.spongepowered.api.item.Enchantment;

import javax.annotation.Nonnull;

/**
 * A wrapper class for the {@code Enchantment} class that also contains a minimum and maximum level.
 */
public final class MythicEnchantment implements Comparable<MythicEnchantment> {

    private final Enchantment enchantment;
    private final int minimumLevel;
    private final int maximumLevel;

    /**
     * Constructs a new HoardEnchantment using the given Enchantment and levels.
     *
     * @param enchantment Enchantment to wrap
     * @param one         first of the level bounds
     * @param two         second of the level bounds
     */
    public MythicEnchantment(Enchantment enchantment, int one, int two) {
        this.enchantment = enchantment;
        this.minimumLevel = Math.min(one, two);
        this.maximumLevel = Math.max(one, two);
    }

    /**
     * Returns the Enchantment this wraps.
     *
     * @return wrapped Enchantment
     */
    public Enchantment enchantment() {
        return enchantment;
    }

    /**
     * Minimum level allowed for the Enchantment.
     *
     * @return minimum level
     */
    public int minimumLevel() {
        return minimumLevel;
    }

    /**
     * Maximum level allowed for the Enchantment.
     *
     * @return maximum level
     */
    public int maximumLevel() {
        return maximumLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MythicEnchantment that = (MythicEnchantment) o;
        return Objects.equal(minimumLevel, that.minimumLevel) &&
                Objects.equal(maximumLevel, that.maximumLevel) &&
                Objects.equal(enchantment, that.enchantment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(enchantment, minimumLevel, maximumLevel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(@Nonnull MythicEnchantment o) {
        if (enchantment == null) {
            if (o.enchantment == null) {
                return 0;
            }
            return -1;
        }
        if (o.enchantment == null) {
            return 1;
        }
        int cmp = enchantment.getName().compareTo(o.enchantment.getName());
        if (cmp != 0) {
            return cmp;
        }
        cmp = Integer.compare(minimumLevel, o.minimumLevel);
        if (cmp != 0) {
            return cmp;
        }
        cmp = Integer.compare(maximumLevel, o.maximumLevel);
        return cmp;
    }

}
