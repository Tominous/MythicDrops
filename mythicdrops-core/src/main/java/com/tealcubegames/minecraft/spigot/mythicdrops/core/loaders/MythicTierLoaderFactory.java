package com.tealcubegames.minecraft.spigot.mythicdrops.core.loaders;

import java.io.File;

/**
 * Factory to load MythicTierLoaders.
 *
 * @author Richard Harrah
 */
public interface MythicTierLoaderFactory {

    /**
     * Create a MythicTierLoader using a File.
     * @param file File from which to load a MythicTier
     * @return a new MythicTierLoader
     */
    MythicTierLoader create(File file);

}
