package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers;

/**
 * Factory to load MythicTierEnchantmentsLoaders.
 *
 * @author Richard Harrah
 */
public interface MythicTierEnchantmentsLoaderFactory {

    /**
     * Create a MythicTierEnchantmentsLoader using a File.
     * @param fileName File from which to load a MythicTierEnchantments
     * @return a new MythicTierEnchantmentsLoader
     */
    MythicTierEnchantmentsLoader create(String fileName);

}
