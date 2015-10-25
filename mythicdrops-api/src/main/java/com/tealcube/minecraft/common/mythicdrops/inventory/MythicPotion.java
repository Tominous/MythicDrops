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

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Represents an implementation-neutral potion used by MythicDrops.
 */
public interface MythicPotion {

    /**
     * Adds an effect to this item. If an effect with the same type exists, it isn't added.
     *
     * @param effect effect to add
     * @return this item
     */
    MythicPotion addEffect(Effect effect);

    /**
     * Adds an effect to this item with the option to overwrite an existing effect.
     *
     * @param effect    effect to add
     * @param overwrite to overwrite or not to overwrite
     * @return this item
     */
    MythicPotion addEffect(Effect effect, boolean overwrite);

    /**
     * Removes all effects from this item.
     *
     * @return this item
     */
    MythicPotion clearEffects();

    /**
     * Fetches and returns an unmodifiable list of effects on this item.
     *
     * @return effects on this item
     */
    List<Effect> getEffects();

    /**
     * Determines if this item has the given effect type.
     *
     * @param type type to check
     * @return if this item has the type
     */
    boolean hasEffect(String type);

    /**
     * Removes the effect type from this item.
     *
     * @param type type to remove
     * @return this item
     */
    MythicPotion removeEffect(String type);

    /**
     * Sets the main effect on this item.
     *
     * @param type type to set as main effect
     * @return this item
     */
    MythicPotion setMainEffect(String type);

    /**
     * Represents an implementation-neutral potion effect used by MythicDrops.
     */
    interface Effect {
        /**
         * Fetches and returns the type of this effect.
         *
         * @return type of this effect
         */
        String getType();

        /**
         * Sets the type of this effect.
         *
         * @param type type to set
         * @return this effect
         */
        Effect setType(@Nonnull String type);

        /**
         * Fetches and returns the amplifier of this effect. Will always be 0 or greater.
         *
         * @return amplifier of this effect
         */
        int getAmplifier();

        /**
         * Sets the amplifier of this effect.
         *
         * Parameters less than 0 will be represented as 0.
         *
         * @param amplifier amplifier to set
         * @return this effect
         */
        Effect setAmplifier(int amplifier);

        /**
         * Fetches and returns the duration of this effect in ticks.
         *
         * @return duration of this effect
         */
        int getDuration();

        /**
         * Sets the duration of this effect.
         *
         * @param duration duration to set in ticks
         * @return this effect
         */
        Effect setDuration(int duration);

        /**
         * Fetches and returns if this effect is ambient.
         *
         * @return if this effect is ambient
         */
        boolean isAmbient();

        /**
         * Sets if this effect is ambient.
         *
         * @param ambient to be or not to be ambient
         * @return this effect
         */
        Effect setAmbient(boolean ambient);
    }

}
