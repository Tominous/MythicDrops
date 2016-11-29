/**
 * This file is part of mythicdrops-core, licensed under the MIT License.
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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.MythicDrops;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.inject.MythicDropsModule;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public final class MythicDropsPlugin extends JavaPlugin implements MythicDrops {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicDropsPlugin.class);

    private LoaderManager loaderManager;

    @Override
    public void onEnable() {
        LOGGER.debug("onEnable() - ENTER");

        // Print the various versions and environments in use
        LOGGER.debug("Using MythicDrops Hilt version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.hilt.PomData.VERSION);
        LOGGER.debug("Using MythicDrops Common version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.common.PomData.VERSION);
        LOGGER.debug("Using MythicDrops API version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.api.PomData.VERSION);
        LOGGER.debug("Starting MythicDrops Core version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.core.PomData.VERSION);
        LOGGER.debug("Running in environment " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.core.PomData.ENVIRONMENT);

        // Start Google Guice
        LOGGER.debug("Starting Google Guice...");
        Injector injector = Guice.createInjector(new MythicDropsModule(this));
        injector.injectMembers(this);
        LOGGER.debug("Google Guice started!");

        LOGGER.debug("onEnable() - EXIT");
    }

    @Override
    public void onDisable() {
        LOGGER.debug("onDisable() - ENTER");
        LOGGER.debug("onDisable() - EXIT");
    }

    @Override
    public LoaderManager getLoaderManager() {
        return loaderManager;
    }

    @Inject
    public void setLoaderManager(LoaderManager loaderManager) {
        this.loaderManager = loaderManager;
    }

}
