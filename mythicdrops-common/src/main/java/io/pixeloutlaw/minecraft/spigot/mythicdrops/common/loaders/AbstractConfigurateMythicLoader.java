/**
 * This file is part of mythicdrops-common, licensed under the MIT License.
 *
 * Copyright (C) 2016 - 2017 Pixel Outlaw <topplethenun@pixeloutlaw.io>
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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.net.URL;

/**
 * Represents a MythicLoader that uses Configurate as a tool to load objects.
 *
 * @param <T> Type of Object to load
 * @author Richard Harrah
 */
public abstract class AbstractConfigurateMythicLoader<T> implements MythicLoader<T> {

    protected ConfigurationLoader<? extends ConfigurationNode> fileConfigurationLoader;
    protected ConfigurationLoader<? extends ConfigurationNode> resourceConfigurationLoader;
    protected String fileName;
    protected File file;
    private Plugin plugin;

    /**
     * Creates a new instance of this class designed to load from the given {@code fileName}.
     *
     * @param plugin Plugin for which to load
     * @param fileName File from which to load
     * @throws NullPointerException          if {@code fileName} is null
     * @throws IllegalArgumentException      if {@code fileName} does not have an extension
     * @throws UnsupportedOperationException if {@code fileName} is not YAML, JSON, or HOCON
     */
    protected AbstractConfigurateMythicLoader(Plugin plugin, String fileName) {
        Preconditions.checkNotNull(plugin);
        Preconditions.checkNotNull(fileName);
        this.plugin = plugin;
        this.fileName = fileName;
        Preconditions.checkArgument(fileName.contains("."));
        Preconditions.checkArgument(!fileName.endsWith("."));
        String extension = Files.getFileExtension(fileName);
        File file = new File(plugin.getDataFolder(), fileName);
        Preconditions.checkArgument(file.exists() || createFile(fileName), "file must exist or be created");
        URL url = plugin.getClass().getClassLoader().getResource(fileName);
        switch (extension.toLowerCase()) {
            case "yml":
                this.fileConfigurationLoader = YAMLConfigurationLoader.builder().setPath(file.toPath()).build();
                this.resourceConfigurationLoader = YAMLConfigurationLoader.builder().setURL(url).build();
                break;
            case "json":
                this.fileConfigurationLoader = GsonConfigurationLoader.builder().setPath(file.toPath()).build();
                this.resourceConfigurationLoader = GsonConfigurationLoader.builder().setURL(url).build();
                break;
            case "conf":
                this.fileConfigurationLoader = HoconConfigurationLoader.builder().setPath(file.toPath()).build();
                this.resourceConfigurationLoader = HoconConfigurationLoader.builder().setURL(url).build();
                break;
            default:
                throw new UnsupportedOperationException(fileName + " is not YAML, JSON, or HOCON");
        }
    }

    private boolean createFile(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (file.exists()) {
            return true;
        }
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (Exception e) {
            return false;
        }
    }

}
