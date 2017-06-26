package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders;

import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierComponent;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierEnchantments;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierLore;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierEnchantmentsLoaderFactory;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierLoaderFactory;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierLoreLoaderFactory;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;

public class MythicLoaderManager implements LoaderManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicLoaderManager.class);
    private MythicTierLoaderFactory mythicTierLoaderFactory;
    private MythicTierEnchantmentsLoaderFactory mythicTierEnchantmentsLoaderFactory;
    private MythicTierLoreLoaderFactory mythicTierLoreLoaderFactory;

    @Inject
    public MythicLoaderManager(MythicTierLoaderFactory mythicTierLoaderFactory,
                               MythicTierEnchantmentsLoaderFactory mythicTierEnchantmentsLoaderFactory,
                               MythicTierLoreLoaderFactory mythicTierLoreLoaderFactory) {
        this.mythicTierLoaderFactory = mythicTierLoaderFactory;
        this.mythicTierEnchantmentsLoaderFactory = mythicTierEnchantmentsLoaderFactory;
        this.mythicTierLoreLoaderFactory = mythicTierLoreLoaderFactory;
    }

    @Override
    public MythicLoader<MythicTier> createNewMythicTierLoader(String fileName) {
        Preconditions.checkNotNull(fileName, "file cannot be null");
        LOGGER.debug("Creating a new MythicTier loader for file: {}", fileName);
        return mythicTierLoaderFactory.create(fileName);
    }

    @Override
    public MythicLoader<MythicTierEnchantments> createMythicTierEnchantmentsLoader(String fileName) {
        Preconditions.checkNotNull(fileName, "file cannot be null");
        LOGGER.debug("Creating a new MythicTierEnchantments loader for file: {}", fileName);
        return mythicTierEnchantmentsLoaderFactory.create(fileName);
    }

    @Override
    public MythicLoader<MythicTierLore> createMythicTierLoreLoader(String fileName) {
        Preconditions.checkNotNull(fileName, "file cannot be null");
        LOGGER.debug("Creating a new MythicTierLore loader for file: {}", fileName);
        return mythicTierLoreLoaderFactory.create(fileName);
    }

    @Override
    public Collection<MythicLoader<MythicTierComponent>> getMythicTierComponentLoaders() {
        throw new NotImplementedException("MythicTierComponents are not implemented yet!");
    }

    @Override
    public boolean registerMythicTierComponentLoader(MythicLoader<MythicTierComponent> loader) {
        throw new NotImplementedException("MythicTierComponents are not implemented yet!");
    }

}
