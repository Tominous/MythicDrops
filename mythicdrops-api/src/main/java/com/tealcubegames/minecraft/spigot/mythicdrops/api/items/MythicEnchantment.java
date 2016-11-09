/**
 * This file is part of MythicDrops_API, licensed under the MIT License.
 * <p>
 * Copyright (C) 2013 Teal Cube Games
 * <p>
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.tealcubegames.minecraft.spigot.mythicdrops.api.items;

import org.bukkit.enchantments.Enchantment;
import org.immutables.value.Value;

/**
 * An interface that represents an enchantment with a minimum and maximum level.
 */
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE)
@Value.Immutable
public abstract class MythicEnchantment {

    public static Builder builder() {
        return ImmutableMythicEnchantment.builder();
    }

    public abstract Enchantment enchantment();

    public abstract int minimumLevel();

    public abstract int maximumLevel();

    public interface Builder {
        Builder enchantment(Enchantment enchantment);

        Builder minimumLevel(int minimumLevel);

        Builder maximumLevel(int maximumLevel);

        MythicEnchantment build();
    }

}
