package com.tealcubegames.minecraft.spigot.mythicdrops.api.loaders;

import com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers.MythicTierComponent;
import com.tealcubegames.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;

import java.io.File;
import java.util.Collection;

/**
 * Represents a general manager to deal with {@code MythicLoader}s.
 *
 * @author Richard Harrah
 */
public interface LoaderManager {

    /**
     * Creates a new {@link MythicLoader} for {@link MythicTier}s.
     *
     * @param file File from which to load
     * @return new MythicLoader for a MythicTier
     */
    MythicLoader<MythicTier> createNewMythicTierLoader(File file);

    /**
     * Gets all of the registered {@link MythicLoader}s for {@link MythicTierComponent}s.
     *
     * @return registered MythicLoaders for MythicTierComponents
     */
    Collection<MythicLoader<MythicTierComponent>> getMythicTierComponentLoaders();

    /**
     * Register a {@link MythicLoader} for a {@link MythicTierComponent}.
     *
     * @param loader MythicLoader for a MythicTierComponent
     * @return if registration was successful
     */
    boolean registerMythicTierComponentLoader(MythicLoader<MythicTierComponent> loader);

}
