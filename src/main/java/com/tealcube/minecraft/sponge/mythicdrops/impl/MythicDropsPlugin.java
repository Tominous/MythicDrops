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
import com.google.inject.Injector;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.tealcube.minecraft.sponge.mythicdrops.Version;
import com.tealcube.minecraft.sponge.mythicdrops.api.MythicDrops;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
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

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

/**
 * {@inheritDoc}
 */
@Plugin(id = Version.ARTIFACT, name = Version.NAME, version = Version.VERSION)
public final class MythicDropsPlugin implements MythicDrops {

    @Inject private Game game;
    @Inject private PluginContainer pluginContainer;
    @Inject private Logger logger;
    @Inject @ConfigDir(sharedRoot = false) private File configurationDirectory;
    @Inject private Injector pluginInjector;

    private File configConfFile;
    private File localeConfFile;
    private File tierConfFile;

    private ConfigurationNode configConfNode;
    private ConfigurationNode localeConfNode;
    private ConfigurationNode tierConfNode;

    private Handlebars handlebars;

    @Listener
    public void onPreInitialization(GamePreInitializationEvent event) {
        handlebars = new Handlebars();
        handlebars.handlebarsJsFile("/handlebars-v2.0.0.js");
        // This is where I'd put my Guice modules...
        // IF I HAD ANY
    }

    @Listener
    public void onInitialization(GameInitializationEvent event) {
        configConfNode = getConfigurationNode("MythicDrops.conf");
        localeConfNode = getConfigurationNode("locale.conf");
        tierConfNode = getConfigurationNode("tier.conf");

        try {
            Template template = handlebars.compileInline(localeConfNode.getNode(configConfNode.getNode("locale")
                    .getString(), "debug").getString());
            logger.info(template.apply(new Version()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConfigurationNode getConfigurationNode(@Nonnull String fileName) {
        File file = new File(configurationDirectory, fileName);

        ConfigurationLoader<CommentedConfigurationNode> loader =
                HoconConfigurationLoader.builder().setFile(file).build();
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
            logger.error(String.format("Unable to load %s", fileName), e);
        }

        return node;
    }

}
