package com.tealcubegames.minecraft.spigot.mythicdrops.core.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.MythicDrops;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import com.tealcubegames.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import com.tealcubegames.minecraft.spigot.mythicdrops.core.loaders.MythicLoaderManager;
import com.tealcubegames.minecraft.spigot.mythicdrops.core.loaders.MythicTierLoader;
import com.tealcubegames.minecraft.spigot.mythicdrops.core.loaders.MythicTierLoaderFactory;
import org.reflections.Reflections;

import javax.inject.Singleton;

/**
 * The Google Guice module for use with IoC.
 *
 * @author Richard Harrah
 */
public final class MythicDropsModule extends AbstractModule {

    private MythicDrops mythicDrops;
    private Reflections reflections;

    public MythicDropsModule(MythicDrops mythicDrops) {
        this.mythicDrops = mythicDrops;
    }

    @Override
    protected void configure() {
        bind(LoaderManager.class).to(MythicLoaderManager.class);

        TypeLiteral<MythicLoader<MythicTier>> mythicTierLoaderLiteral = new TypeLiteral<MythicLoader<MythicTier>>() {
        };
        install(new FactoryModuleBuilder().implement(mythicTierLoaderLiteral, MythicTierLoader.class)
                .build(MythicTierLoaderFactory.class));
    }

    @Provides
    protected MythicDrops provideMythicDrops() {
        return mythicDrops;
    }

    @Provides
    @Singleton
    protected Reflections provideReflections() {
        if (reflections == null) {
            this.reflections = new Reflections("com.tealcubegames.minecraft.spigot.mythicdrops");
        }
        return reflections;
    }

}
