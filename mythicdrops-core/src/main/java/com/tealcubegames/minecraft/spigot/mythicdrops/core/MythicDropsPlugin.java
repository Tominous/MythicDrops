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
package com.tealcubegames.minecraft.spigot.mythicdrops.core;

import com.tealcubegames.minecraft.spigot.mythicdrops.api.MythicDrops;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MythicDropsPlugin extends JavaPlugin implements MythicDrops {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicDropsPlugin.class);

    @Override
    public void onEnable() {
        LOGGER.debug("onEnable() - ENTER");
        LOGGER.debug("Using MythicDrops Common version " +
                com.tealcubegames.minecraft.spigot.mythicdrops.common.PomData.VERSION);
        LOGGER.debug("Using MythicDrops API version " +
                com.tealcubegames.minecraft.spigot.mythicdrops.api.PomData.VERSION);
        LOGGER.debug("Starting MythicDrops Core version " +
                com.tealcubegames.minecraft.spigot.mythicdrops.core.PomData.VERSION);
        LOGGER.debug("Running in environment " +
                com.tealcubegames.minecraft.spigot.mythicdrops.core.PomData.ENVIRONMENT);
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            throw new NullPointerException("this is a test NPE");
        }, 30 * 20L);
        LOGGER.debug("onEnable() - EXIT");
    }

    @Override
    public void onDisable() {
        LOGGER.debug("onDisable() - ENTER");
        LOGGER.debug("onDisable() - EXIT");
    }

    @Override
    public LoaderManager getLoaderManager() {
        return null;
    }

}
