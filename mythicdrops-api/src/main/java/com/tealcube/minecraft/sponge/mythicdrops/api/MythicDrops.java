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
package com.tealcube.minecraft.sponge.mythicdrops.api;

import org.slf4j.Logger;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.File;

/**
 * Represents the interface for MythicDrops on Sponge.
 *
 * @author Richard Harrah
 * @version 09212015
 */
public interface MythicDrops {

    /**
     * Fetches and returns the {@code Logger} used for displaying messages.
     *
     * @return logger
     */
    Logger getLogger();

    /**
     * Fetches and returns the {@code PluginContainer} wrapped around the {@code @Plugin} annotation on this plugin.
     *
     * @return plugin container
     */
    PluginContainer getPluginContainer();

    /**
     * Fetches and returns the {@code File} where all configuration for this plugin is stored.
     *
     * @return configuration directory
     */
    File getConfigurationDirectory();

    /**
     * Fetches and returns a {@code String} that that represents which text strings will be used.
     *
     * @return locale type
     */
    String getLocale();

    /**
     * Represents the configuration files.
     */
    enum ConfFile {
        /**
         * Contains plugin-wide settings.
         */
        DEFAULT("default.conf"),
        /**
         * Contains localized/customizable message strings.
         */
        LOCALE("locale.conf"),
        /**
         * Contains the tiers for this server.
         */
        TIER("tier.conf");

        private final String path;

        ConfFile(String path) {
            this.path = path;
        }

        /**
         * Returns the path of this ConfFile.
         *
         * @return path
         */
        public String path() {
            return path;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return path();
        }
    }

}
