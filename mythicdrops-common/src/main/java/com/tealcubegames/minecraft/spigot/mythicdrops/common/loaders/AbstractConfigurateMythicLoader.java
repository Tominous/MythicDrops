package com.tealcubegames.minecraft.spigot.mythicdrops.common.loaders;

import com.google.common.base.Preconditions;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.File;

/**
 * Represents a MythicLoader that uses Configurate as a tool to load objects.
 *
 * @param <T> Type of Object to load
 * @author Richard Harrah
 */
public abstract class AbstractConfigurateMythicLoader<T> implements MythicLoader<T> {

    protected ConfigurationLoader<? extends ConfigurationNode> configurationLoader;

    /**
     * Creates a new instance of this class designed to load from the given {@code File}.
     *
     * @param file File from which to load
     * @throws NullPointerException          if {@code file} is null
     * @throws IllegalArgumentException      if {@code file} does not exist or if {@code file} does not have an extension
     * @throws UnsupportedOperationException if {@code file} is not YAML, JSON, or HOCON
     */
    protected AbstractConfigurateMythicLoader(File file) {
        Preconditions.checkNotNull(file);
        Preconditions.checkArgument(file.exists());
        this.configurationLoader = createConfigurationLoader(file);
    }

    /**
     * Creates a {@link ConfigurationLoader} for the given {@code File}.
     *
     * @return ConfigurationLoader for File
     * @throws NullPointerException          if {@code file} is null
     * @throws IllegalArgumentException      if {@code file} does not exist or if {@code file} does not have an extension
     * @throws UnsupportedOperationException if {@code file} is not YAML, JSON, or HOCON
     */
    protected ConfigurationLoader<? extends ConfigurationNode> createConfigurationLoader(File file) {
        Preconditions.checkNotNull(file);
        Preconditions.checkArgument(file.exists());
        Preconditions.checkArgument(file.getName().contains("."));
        Preconditions.checkArgument(!file.getName().endsWith("."));
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        ConfigurationLoader<? extends ConfigurationNode> ret;
        switch (extension.toLowerCase()) {
            case "yml":
                ret = YAMLConfigurationLoader.builder().setPath(file.toPath()).build();
                break;
            case "json":
                ret = GsonConfigurationLoader.builder().setPath(file.toPath()).build();
                break;
            case "conf":
                ret = HoconConfigurationLoader.builder().setPath(file.toPath()).build();
                break;
            default:
                throw new UnsupportedOperationException(fileName + " is not YAML, JSON, or HOCON");
        }
        return ret;
    }

}
