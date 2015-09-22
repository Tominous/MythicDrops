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

import com.google.inject.Inject;

import com.tealcube.minecraft.sponge.mythicdrops.api.ApiVersion;
import com.tealcube.minecraft.sponge.mythicdrops.api.MythicDrops;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.service.config.ConfigDir;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import ninja.leaping.configurate.ConfigurationNode;
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

    private Configs configs;

    @Listener
    public void onGamePreInitialization(GamePreInitializationEvent event) {
        configs = new ConfigsImpl();
        configs.load();
    }

    @Listener
    public void onGameInitialization(GameInitializationEvent event) {
        String locale = getConfigs().getProperty(ConfFile.DEFAULT, "locale").getString("en");
        String apiString = getConfigs().getProperty(ConfFile.LOCALE, locale + ".debug.api").getString(locale +
                ".debug.api");
        String pluginString = getConfigs().getProperty(ConfFile.LOCALE, locale + ".debug.plugin").getString(locale +
                ".debug.plugin");

        logger.info(String.format(apiString, ApiVersion.ARTIFACT, ApiVersion.VERSION));
        logger.info(String.format(
                pluginString, pluginContainer.getId(), pluginContainer.getName(), pluginContainer.getVersion()));
    }

    @Listener
    public void onGameStoppingServer(GameStoppingServerEvent event) {
        configs.save();
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
    public Configs getConfigs() {
        return configs;
    }

    /**
     * {@inheritDoc}
     */
    private class ConfigsImpl implements Configs {
        private final Map<ConfFile, CommentedConfigurationNode> configMap = new HashMap<>();

        /**
         * {@inheritDoc}
         */
        @Override
        public void load() {
            for (ConfFile confFile : ConfFile.values()) {
                File file = new File(MythicDropsPlugin.this.getConfigurationDirectory(), confFile.path());

                if (file.exists()) {
                    CommentedConfigurationNode node = load(file);
                    configMap.put(confFile, node);
                } else {
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists() && !parentFile.mkdirs()) {
                        MythicDropsPlugin.this.getLogger().warn(String.format(
                                "Unable to make configuration directory for %s", confFile.path()));
                        continue;
                    }
                    try {
                        if (!file.createNewFile()) {
                            MythicDropsPlugin.this.getLogger().warn(String.format(
                                    "Unable to create file for %s", confFile.path()));
                            continue;
                        }
                    } catch (IOException e) {
                        MythicDropsPlugin.this.getLogger().warn(String.format(
                                "Unable to create file for %s", confFile.path()));
                        continue;
                    }
                    CommentedConfigurationNode node = createConfigurationNode(confFile);
                    configMap.put(confFile, node);
                }
            }
            MythicDropsPlugin.this.getLogger().info("Configuration loaded");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void save() {
            for (ConfFile confFile : ConfFile.values()) {
                File file = new File(MythicDropsPlugin.this.getConfigurationDirectory(), confFile.path());
                save(confFile, file);
            }
            MythicDropsPlugin.this.getLogger().info("Configuration saved");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ConfigurationNode getProperty(@Nonnull ConfFile conf, @Nonnull String path) {
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().build();
            CommentedConfigurationNode node = loader.createEmptyNode(ConfigurationOptions.defaults());

            if (!configMap.containsKey(conf)) {
                MythicDropsPlugin.this.getLogger().warn(
                        String.format("Attempting to get a property (%s) from a nonexistent configuration file (%s)",
                                path, conf.path()));
                return node;
            }

            if (configMap.get(conf) == null) {
                MythicDropsPlugin.this.getLogger().warn(
                        String.format("Attempting to get a property (%s) from an unloaded configuration file (%s)",
                                path, conf.path()));
                return node;
            }

            node = configMap.get(conf);

            return node.getNode(path.split("\\."));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean setProperty(@Nonnull ConfFile conf, @Nonnull String path, @Nonnull Object value) {
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().build();
            CommentedConfigurationNode node = loader.createEmptyNode(ConfigurationOptions.defaults());

            if (!configMap.containsKey(conf)) {
                MythicDropsPlugin.this.getLogger().warn(
                        String.format("Attempting to set a property (%s) for a nonexistent configuration file (%s)",
                                path, conf.path()));
                return false;
            }

            if (configMap.get(conf) == null) {
                MythicDropsPlugin.this.getLogger().warn(
                        String.format("Attempting to set a property (%s) for an unloaded configuration file (%s)",
                                path, conf.path()));
                return false;
            }

            node = configMap.get(conf).getNode(path.split("\\."));
            node.setValue(value);

            return true;
        }

        private CommentedConfigurationNode createConfigurationNode(ConfFile confFile) {
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().build();
            CommentedConfigurationNode node = loader.createEmptyNode(ConfigurationOptions.defaults());

            switch (confFile) {
                case DEFAULT:
                    node.getNode("locale").setValue("en");
                    break;
                case LOCALE:
                    node.getNode("en", "debug", "api").setValue("API: %s v%s").setComment("API debug string");
                    node.getNode("en", "debug", "plugin").setValue(
                            "id = %s, name = %s, version = %s").setComment("Plugin debug string");
                    break;
                case TIER:
                    // do nothing here for now
                    break;
                default:
                    // do nothing here
                    break;
            }

            return node;
        }

        private boolean save(ConfFile confFile, File file) {
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    MythicDropsPlugin.this.getLogger().warn(String.format(
                            "Unable to make configuration directory for %s", confFile.path()));
                    return false;
                }
                try {
                    if (!file.createNewFile()) {
                        MythicDropsPlugin.this.getLogger().warn(String.format(
                                "Unable to create file for %s", confFile.path()));
                        return false;
                    }
                } catch (IOException e) {
                    MythicDropsPlugin.this.getLogger().warn(String.format(
                            "Unable to create file for %s", confFile.path()));
                    return false;
                }
            }
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
                    .setFile(file).build();
            if (!configMap.containsKey(confFile)) {
                MythicDropsPlugin.this.getLogger().warn(String.format(
                        "Unable to save %s", confFile.path()));
                return false;
            }

            CommentedConfigurationNode node = configMap.get(confFile);
            if (node == null) {
                node = createConfigurationNode(confFile);
            }
            try {
                loader.save(node);
            } catch (IOException e) {
                MythicDropsPlugin.this.getLogger().warn(String.format(
                        "Unable to save %s", confFile.path()));
                return false;
            }
            return true;
        }

        private CommentedConfigurationNode load(File f) {
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
                    .setFile(f).build();
            CommentedConfigurationNode node = loader.createEmptyNode(ConfigurationOptions.defaults());

            try {
                node = loader.load();
            } catch (IOException ex) {
                MythicDropsPlugin.this.getLogger().warn(
                        String.format("Unable to load %s: %s", f.getName(), ex.getMessage()));
            }

            return node;
        }
    }

}
