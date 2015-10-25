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
