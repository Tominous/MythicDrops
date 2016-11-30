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

import ch.qos.logback.classic.Level;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.MythicDrops;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.inject.MythicDropsModule;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class MythicDropsPlugin extends JavaPlugin implements MythicDrops {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicDropsPlugin.class);
    private static final List<String> ROOT_LOGGER_LIST = Collections.singletonList(Logger.ROOT_LOGGER_NAME);

    private LoaderManager loaderManager;
    private TemporaryListener temporaryListener;

    @Override
    public void onEnable() {
        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    @Override
    public LoaderManager getLoaderManager() {
        return loaderManager;
    }

    @Override
    public void enable() {
        LOGGER.debug("enable() - ENTER");

        // Print the various versions and environments in use
        LOGGER.debug("Using MythicDrops Hilt version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.hilt.PomData.VERSION);
        LOGGER.debug("Using MythicDrops Common version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.common.PomData.VERSION);
        LOGGER.debug("Using MythicDrops API version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.api.PomData.VERSION);
        LOGGER.debug("Starting MythicDrops Core version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.core.PomData.VERSION);

        // Start Google Guice
        LOGGER.debug("Starting Google Guice...");
        Injector injector = Guice.createInjector(new MythicDropsModule(this));
        injector.injectMembers(this);
        LOGGER.debug("Google Guice started!");

        LOGGER.debug("Registering event listeners...");
        getServer().getPluginManager().registerEvents(temporaryListener, this);
        LOGGER.debug("Listeners registered!");

        LOGGER.debug("enable() - EXIT");
    }

    @Override
    public void disable() {
        LOGGER.debug("disable() - ENTER");
        LOGGER.debug("disable() - EXIT");
    }

    @Inject
    public void setLoaderManager(LoaderManager loaderManager) {
        this.loaderManager = loaderManager;
    }

    public TemporaryListener getTemporaryListener() {
        return temporaryListener;
    }

    @Inject
    public void setTemporaryListener(TemporaryListener temporaryListener) {
        this.temporaryListener = temporaryListener;
    }

}
