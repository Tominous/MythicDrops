/**
 * This file is part of MythicDrops_Core, licensed under the MIT License.
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
package com.tealcubegames.minecraft.spigot.mythicdrops.core.loaders;

import com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import com.tealcubegames.minecraft.spigot.mythicdrops.common.loaders.AbstractConfigurateMythicLoader;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of MythicLoader for loading MythicTiers.
 */
public final class MythicTierLoader extends AbstractConfigurateMythicLoader<MythicTier> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicTierLoader.class);

    public MythicTierLoader(File file) {
        super(file);
    }

    @Override
    public MythicTier load() {
        MythicTier mythicTier = null;
        try {
            ConfigurationNode configurationNode = configurationLoader.load();
        } catch (IOException e) {
            LOGGER.error("Unable to load tier", e);
        }
        return mythicTier;
    }

    @Override
    public void save(MythicTier mythicTier) {

    }

}
