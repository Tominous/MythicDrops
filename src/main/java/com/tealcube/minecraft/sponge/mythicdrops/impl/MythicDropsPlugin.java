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
package com.tealcube.minecraft.sponge.mythicdrops.impl;

import com.google.common.io.Files;
import com.google.inject.Inject;

import com.tealcube.minecraft.sponge.mythicdrops.Version;
import com.tealcube.minecraft.sponge.mythicdrops.api.MythicDrops;
import com.tealcube.minecraft.sponge.mythicdrops.api.registries.Registry;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.DefaultConfig;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;

import javax.annotation.Nonnull;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

/**
 * Represents the implementation of the Hoard plugin for Sponge.
 *
 * Hoard is the Sponge port of the Bukkit/Spigot plugin MythicDrops.
 *
 * @author Richard Harrah
 * @version 09162015
 */
@Plugin(id = Version.ARTIFACT, name = Version.NAME, version = Version.VERSION)
public final class MythicDropsPlugin implements MythicDrops {

    @Inject
    private Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private File defaultConfigFile;

    private ConfigurationNode defaultConfigurationNode;
    private ConfigurationNode tierConfigurationNode;
    private Registry<Type, Registry<?, ?>> registry;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        File tierConfigFile = new File(defaultConfigFile.getParentFile(), "tier.conf");

        defaultConfigurationNode = getConfigurationNode(defaultConfigFile.getName());
        tierConfigurationNode = getConfigurationNode(tierConfigFile.getName());

        registry = new MythicDropsRegistry();
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public ConfigurationNode getDefaultConfigurationNode() {
        return defaultConfigurationNode;
    }

    @Override
    public ConfigurationNode getTierConfigurationNode() {
        return tierConfigurationNode;
    }

    @Override
    public Registry<Type, Registry<?, ?>> getRegistry() {
        return registry;
    }

    private ConfigurationNode getConfigurationNode(@Nonnull String fileName) {
        File file = new File(defaultConfigFile.getParentFile(), fileName);

        ConfigurationLoader<CommentedConfigurationNode> loader =
                HoconConfigurationLoader.builder().setFile(defaultConfigFile).build();
        ConfigurationNode node = loader.createEmptyNode(ConfigurationOptions.defaults());

        try {
            Files.createParentDirs(file);
            if (!file.exists()) {
                URL resource = getClass().getResource(fileName);
                loader = HoconConfigurationLoader.builder().setURL(resource).build();
                node = loader.load();
                loader.save(node);
            } else {
                node = loader.load();
            }
        } catch (IOException e) {
            getLogger().error(String.format("Unable to load %s", fileName), e);
        }

        return node;
    }

}
