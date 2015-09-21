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
package com.tealcube.minecraft.sponge.mythicdrops.plugin;

import com.google.common.io.Files;
import com.google.inject.Inject;

import com.tealcube.minecraft.sponge.mythicdrops.api.ApiVersion;
import com.tealcube.minecraft.sponge.mythicdrops.api.MythicDrops;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.service.config.ConfigDir;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.annotation.Nonnull;

import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

/**
 * {@inheritDoc}
 */
@Plugin(id = PluginVersion.ARTIFACT, name = PluginVersion.NAME, version = PluginVersion.VERSION)
public final class MythicDropsPlugin implements MythicDrops {

    @Inject private Logger logger;
    @Inject private PluginContainer pluginContainer;
    @Inject @ConfigDir(sharedRoot = false) private File configurationDirectory;

    private CommentedConfigurationNode defaultConfigurationNode;
    private CommentedConfigurationNode localeConfigurationNode;

    private String locale;

    @Listener
    public void onGamePreInitialization(GamePreInitializationEvent event) {
        defaultConfigurationNode = loadConfigurationNode("default.conf");
        localeConfigurationNode = loadConfigurationNode("locale.conf");

        locale = defaultConfigurationNode.getNode("locale").getString("en");
    }

    @Listener
    public void onGameInitialization(GameInitializationEvent event) {
        String apiString = localeConfigurationNode.getNode(locale, "debug", "api").getString(locale + ".debug.api");
        String pluginString = localeConfigurationNode.getNode(locale, "debug", "plugin").getString(
                locale + ".debug.plugin");

        logger.info(String.format(apiString, ApiVersion.ARTIFACT, ApiVersion.VERSION));
        logger.info(String.format(
                pluginString, pluginContainer.getId(), pluginContainer.getName(), pluginContainer.getVersion()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger() {
        return logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PluginContainer getPluginContainer() {
        return pluginContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getConfigurationDirectory() {
        return configurationDirectory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocale() {
        return locale;
    }

    private CommentedConfigurationNode loadConfigurationNode(@Nonnull String fileName) {
        File file = new File(configurationDirectory, fileName);

        ConfigurationLoader<CommentedConfigurationNode> loader =
                HoconConfigurationLoader.builder().setFile(file).build();
        CommentedConfigurationNode node = loader.createEmptyNode(ConfigurationOptions.defaults());

        try {
            Files.createParentDirs(file);
            if (!file.exists()) {
                URL resource = getClass().getResource("/" + fileName);
                loader = HoconConfigurationLoader.builder().setURL(resource).build();
                node = loader.load();
                loader.save(node);
            } else {
                node = loader.load();
            }
        } catch (IOException e) {
            logger.error(String.format("Unable to load %s: %s", fileName, e.getMessage()));
        }

        return node;
    }

}
