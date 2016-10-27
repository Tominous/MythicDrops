/**
 * This file is part of MythicDrops_API, licensed under the MIT License.
 *
 * Copyright (C) 2013 Teal Cube Games
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.tealcubegames.minecraft.spigot.mythicdrops.api.loaders;

import com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers.MythicTierComponent;
import com.tealcubegames.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;

import java.io.File;
import java.util.Collection;

/**
 * Represents a general manager to deal with {@code MythicLoader}s.
 *
 * @author Richard Harrah
 */
public interface LoaderManager {

    /**
     * Creates a new {@link MythicLoader} for {@link MythicTier}s.
     *
     * @param file File from which to load
     * @return new MythicLoader for a MythicTier
     */
    MythicLoader<MythicTier> createNewMythicTierLoader(File file);

    /**
     * Gets all of the registered {@link MythicLoader}s for {@link MythicTierComponent}s.
     *
     * @return registered MythicLoaders for MythicTierComponents
     */
    Collection<MythicLoader<MythicTierComponent>> getMythicTierComponentLoaders();

    /**
     * Register a {@link MythicLoader} for a {@link MythicTierComponent}.
     *
     * @param loader MythicLoader for a MythicTierComponent
     * @return if registration was successful
     */
    boolean registerMythicTierComponentLoader(MythicLoader<MythicTierComponent> loader);

}
