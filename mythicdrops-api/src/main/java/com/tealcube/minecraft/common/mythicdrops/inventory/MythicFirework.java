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

import java.awt.*;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Represents an implementation-neutral firework item used by MythicDrops.
 */
public interface MythicFirework extends MythicItem {

    /**
     * Add an effect to this firework.
     *
     * @param effect effect to add
     * @return this item
     */
    MythicFirework addEffect(Effect effect);

    /**
     * Add several effects to this firework.
     *
     * @param effects effects to add
     * @return this item
     */
    MythicFirework addEffects(Effect[] effects);

    /**
     * Add several effects to this firework.
     *
     * @param effects effects to add
     * @return this item
     */
    MythicFirework addEffects(Iterable<Effect> effects);

    /**
     * Removes all effects from this firework.
     *
     * @return this item
     */
    MythicFirework clearEffects();

    /**
     * Returns an unmodifiable collection of effects on this firework.
     *
     * @return this item
     */
    List<Effect> getEffects();

    /**
     * Removes the effect at the given index on this firework.
     *
     * @param index index to remove
     * @return this item
     * @throws IllegalArgumentException if {@code index < 0 || index >= getEffects().size()}
     */
    MythicFirework removeEffect(int index);

    /**
     * Fetches and returns the power of this firework.
     *
     * @return power of this firework
     */
    int getPower();

    /**
     * Sets the power of this firework.
     *
     * @param power power to set
     * @return this item
     */
    MythicFirework setPower(int power);

    /**
     * Shape of the firework.
     */
    enum Shape {
        /**
         * A small ball effect.
         */
        BALL,
        /**
         * A large ball effect.
         */
        BALL_LARGE,
        /**
         * A burst effect.
         */
        BURST,
        /**
         * A creeper face effect.
         */
        CREEPER,
        /**
         * A star-shaped effect.
         */
        STAR
    }

    /**
     * Effect contained by the firework.
     */
    interface Effect {
        /**
         * Fetches the shape of this effect.
         *
         * @return shape of this effect
         */
        Shape getShape();

        /**
         * Fetches the colors of this firework.
         *
         * Returns an empty {@code List<Color>} if there are no colors.
         *
         * @return colors of this item
         */
        List<Color> getColors();

        /**
         * Sets the colors of this item. A null parameter removes the colors.
         *
         * @param colors colors to set
         * @return this item
         */
        Effect setColors(@Nullable List<Color> colors);

        /**
         * Fetches the fade colors of this firework.
         *
         * Returns an empty {@code List<Color>} if there are no fade colors.
         *
         * @return fade colors of this item
         */
        List<Color> getFadeColors();

        /**
         * Sets the fade colors of this item. A null parameter removes the fade colors.
         *
         * @param fadeColors fade colors to set
         * @return this item
         */
        Effect setFadeColors(@Nullable List<Color> fadeColors);

        /**
         * Fetches if the firework flickers.
         *
         * @return if the firework flickers
         */
        boolean hasFlicker();

        /**
         * Sets if the firework flickers.
         *
         * @param flicker to flicker or not to flicker
         * @return this item
         */
        Effect setFlicker(boolean flicker);
    }

}
