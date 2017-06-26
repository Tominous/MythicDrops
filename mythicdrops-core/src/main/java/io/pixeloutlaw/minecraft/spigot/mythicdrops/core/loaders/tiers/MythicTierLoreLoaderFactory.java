package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers;

/**
 * Factory to load MythicTierLoreLoaders.
 *
 * @author Richard Harrah
 */
public interface MythicTierLoreLoaderFactory {

    /**
     * Create a MythicTierLoreLoader using a File.
     * @param fileName File from which to load a MythicTierLore
     * @return a new MythicTierLoreLoader
     */
    MythicTierLoreLoader create(String fileName);

}