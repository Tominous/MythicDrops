/**
 * This file is part of mythicdrops-core, licensed under the MIT License.
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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.config;

import com.github.zafarkhaja.semver.Version;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.config.Config;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class VersionedConfigConfigurateMythicLoader<T extends Config> extends ConfigConfigurateMythicLoader<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionedConfigConfigurateMythicLoader.class);
    private static final Comparator<Version> VERSION_COMPARATOR = new VersionComparator();

    /**
     * Creates a new instance of this class designed to load from the given {@code fileName}.
     *
     * @param configClazz
     * @param plugin      Plugin for which to load
     * @param fileName    File from which to load
     * @throws NullPointerException          if {@code fileName} is null
     * @throws IllegalArgumentException      if {@code fileName} does not have an extension
     * @throws UnsupportedOperationException if {@code fileName} is not YAML, JSON, or HOCON
     */
    protected VersionedConfigConfigurateMythicLoader(Class<T> configClazz, Plugin plugin, String fileName) {
        super(configClazz, plugin, fileName);
    }

    public T update() {
        // Load config objects
        Pair<T, T> pair = load();

        // Create Version objects from config objects' version fields
        T fileConfig = pair.getLeft();
        T resourceConfig = pair.getRight();
        Version fileVersion = fileConfig.getVersion() != null ? Version.valueOf(fileConfig.getVersion()) : null;
        Version resourceVersion;
        if (resourceConfig.getVersion() != null) {
            resourceVersion = Version.valueOf(resourceConfig.getVersion());
        } else {
            resourceVersion = null;
        }

        // Compare Version objects to determine if fileConfig is up-to-date
        int compared = VERSION_COMPARATOR.compare(fileVersion, resourceVersion);
        LOGGER.debug("Comparing file version ({}) to resource version ({}): {}",
                fileVersion, resourceVersion, compared);
        if (compared == 0) {
            return fileConfig;
        }

        // If we're here, we want to save the resource config over the file config
        LOGGER.debug("Updating {}", fileName);
        save(Pair.of(null, resourceConfig));

        // Recurse :)
        return update();
    }

    private static class VersionComparator implements Comparator<Version> {
        @Override
        public int compare(Version o1, Version o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            return o1.compareTo(o2);
        }
    }
}
