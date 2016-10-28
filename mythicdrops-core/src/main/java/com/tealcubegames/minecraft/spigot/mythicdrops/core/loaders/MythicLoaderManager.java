package com.tealcubegames.minecraft.spigot.mythicdrops.core.loaders;

import com.google.common.base.Preconditions;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers.MythicTierComponent;
import com.tealcubegames.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.util.Collection;

/**
 * An implementation of the LoaderManager.
 *
 * @author Richard Harrah
 */
public final class MythicLoaderManager implements LoaderManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicLoaderManager.class);
    private MythicTierLoaderFactory mythicTierLoaderFactory;

    @Inject
    public MythicLoaderManager(MythicTierLoaderFactory mythicTierLoaderFactory) {
        this.mythicTierLoaderFactory = mythicTierLoaderFactory;
    }

    @Override
    public MythicLoader<MythicTier> createNewMythicTierLoader(File file) {
        Preconditions.checkNotNull(file, "file cannot be null");
        LOGGER.debug("Creating a new MythicTier loader for file: {}", file.getAbsolutePath());
        return mythicTierLoaderFactory.create(file);
    }

    @Override
    public Collection<MythicLoader<MythicTierComponent>> getMythicTierComponentLoaders() {
        throw new NotImplementedException();
    }

    @Override
    public boolean registerMythicTierComponentLoader(MythicLoader<MythicTierComponent> loader) {
        throw new NotImplementedException();
    }

}
