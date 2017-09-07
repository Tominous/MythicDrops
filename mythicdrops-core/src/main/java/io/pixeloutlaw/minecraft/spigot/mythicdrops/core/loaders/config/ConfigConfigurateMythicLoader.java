/*
 * The MIT License
 * Copyright © 2013 Pixel Outlaw
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.config;

import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.config.Config;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.AbstractConfigurateMythicLoader;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.SimpleConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ConfigConfigurateMythicLoader<T extends Config> extends AbstractConfigurateMythicLoader<Pair<T, T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigConfigurateMythicLoader.class);
    private final Class<T> configClazz;

    /**
     * Creates a new instance of this class designed to load from the given {@code fileName}.
     *
     * @param plugin   Plugin for which to load
     * @param fileName File from which to load
     * @throws NullPointerException          if {@code fileName} is null
     * @throws IllegalArgumentException      if {@code fileName} does not have an extension
     * @throws UnsupportedOperationException if {@code fileName} is not YAML, JSON, or HOCON
     */
    protected ConfigConfigurateMythicLoader(Class<T> configClazz, Plugin plugin, String fileName) {
        super(plugin, fileName);
        this.configClazz = configClazz;
    }

    @Override
    public Pair<T, T> load() {
        return Pair.of(loadFromFile(), loadFromResource());
    }

    @Override
    public void save(Pair<T, T> ttPair) {
        ConfigurationNode configurationNode = SimpleConfigurationNode.root();
        try {
            if (ttPair.getLeft() != null) {
                ObjectMapper.forObject(ttPair.getLeft()).serialize(configurationNode);
            } else if (ttPair.getRight() != null) {
                ObjectMapper.forObject(ttPair.getRight()).serialize(configurationNode);
            }
            fileConfigurationLoader.save(configurationNode);
        } catch (Exception e) {
            LOGGER.debug("Unable to save startup config", e);
        }
    }

    private T loadFromFile() {
        ConfigurationNode nodeFromFile = null;
        try {
            nodeFromFile = fileConfigurationLoader.load();
        } catch (IOException e) {
            LOGGER.debug("Unable to load config from file", e);
        }
        T configFromFile = null;
        try {
            if (nodeFromFile != null) {
                configFromFile = ObjectMapper.forClass(configClazz).bindToNew().populate(nodeFromFile);
            }
        } catch (ObjectMappingException e) {
            LOGGER.debug("Unable to map node to Object from file", e);
        }
        return configFromFile;
    }

    private T loadFromResource() {
        ConfigurationNode nodeFromResource = null;
        try {
            nodeFromResource = resourceConfigurationLoader.load();
        } catch (IOException e) {
            LOGGER.debug("Unable to load config from resource", e);
        }
        T configFromResource = null;
        try {
            if (nodeFromResource != null) {
                configFromResource = ObjectMapper.forClass(configClazz).bindToNew().populate(nodeFromResource);
            }
        } catch (ObjectMappingException e) {
            LOGGER.debug("Unable to map node to Object from resource", e);
        }
        return configFromResource;
    }

}