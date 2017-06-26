package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers;

/**
 * Factory to load MythicTierLoaders.
 *
 * @author Richard Harrah
 */
public interface MythicTierLoaderFactory {

    /**
     * Create a MythicTierLoader using a File.
     * @param fileName File from which to load a MythicTier
     * @return a new MythicTierLoader
     */
    MythicTierLoader create(String fileName);

}