/*
 * This file is part of MythicDrops, licensed under the MIT License (MIT)
 *
 * Copyright (c) 2015 Richard Harrah
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

import com.tealcube.minecraft.sponge.mythicdrops.api.registries.Registry;

import org.slf4j.Logger;

import java.lang.reflect.Type;

import ninja.leaping.configurate.ConfigurationNode;

/**
 * Represents the API of the Hoard plugin for Sponge.
 *
 * Hoard is the Sponge port of the Bukkit/Spigot plugin MythicDrops.
 *
 * @author Richard Harrah
 * @version 09162015
 */
public interface MythicDrops {

    /**
     * Fetches the {@code Logger} used by the plugin to help debug and show messages.
     *
     * @return Logger for messages
     */
    Logger getLogger();

    /**
     * Fetches the {@code ConfigurationNode} for the default configuration file.
     * @return ConfigurationNode for mythicdrops.conf
     */
    ConfigurationNode getDefaultConfigurationNode();

    /**
     * Fetches the {@code ConfigurationNode} for the tier configuration file.
     * @return ConfigurationNode for tier.conf
     */
    ConfigurationNode getTierConfigurationNode();

    /**
     * Fetches the Registry of Registries.
     * @return Registry for use
     */
    Registry<Type, Registry<?, ?>> getRegistry();

}
