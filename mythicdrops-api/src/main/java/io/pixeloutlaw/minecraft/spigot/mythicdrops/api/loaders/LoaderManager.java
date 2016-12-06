/**
 * This file is part of mythicdrops-api, licensed under the MIT License.
 *
 * Copyright (C) 2016 Pixel Outlaw <topplethenun@pixeloutlaw.io>
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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders;

import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierComponent;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierEnchantments;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierLore;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;

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
     * @param fileName File from which to load
     * @return new MythicLoader for a MythicTier
     */
    MythicLoader<MythicTier> createNewMythicTierLoader(String fileName);

    /**
     * Creates a new {@link MythicLoader} for {@link MythicTierEnchantments}es.
     *
     * @param fileName File from which to load
     * @return a new MythicLoader for a MythicTierEnchantments
     */
    MythicLoader<MythicTierEnchantments> createMythicTierEnchantmentsLoader(String fileName);

    MythicLoader<MythicTierLore> createMythicTierLoreLoader(String fileName);

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
