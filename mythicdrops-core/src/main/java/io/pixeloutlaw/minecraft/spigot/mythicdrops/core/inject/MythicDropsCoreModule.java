package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.config.StartupConfig;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierEnchantments;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierLore;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.LoggerManipulator;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.MessageUtils;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.TextManipulator;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.MythicDropsPlugin;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.MythicLoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.*;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.utils.LoggerManipulatorImpl;
import org.reflections.Reflections;

import javax.inject.Singleton;

public final class MythicDropsCoreModule extends AbstractModule {

    private MythicDropsPlugin mythicDropsPlugin;
    private Reflections reflections;

    public MythicDropsCoreModule(MythicDropsPlugin mythicDropsPlugin) {
        this.mythicDropsPlugin = mythicDropsPlugin;
    }

    @Override
    protected void configure() {
        // Utility Classes
        bindUtilityClasses();

        // Config Classes
        bindConfigClasses();

        // Managers
        bindManagers();

        // Factories
        installFactories();
    }

    @Provides
    protected MythicDropsPlugin provideMythicDropsPlugin() {
        return mythicDropsPlugin;
    }

    @Provides
    @Singleton
    protected Reflections provideReflections() {
        if (reflections == null) {
            this.reflections = new Reflections("io.pixeloutlaw.minecraft.spigot.mythicdrops");
        }
        return reflections;
    }

    private void bindConfigClasses() {
        bind(StartupConfig.class);
    }

    private void bindManagers() {
        bind(LoaderManager.class).to(MythicLoaderManager.class);
    }

    private void bindUtilityClasses() {
        bind(LoggerManipulator.class).to(LoggerManipulatorImpl.class);
        bind(TextManipulator.class);
        bind(MessageUtils.class);
    }

    private void installFactories() {
        TypeLiteral<MythicLoader<MythicTier>> mythicTierLoaderLiteral =
                new TypeLiteral<MythicLoader<MythicTier>>() {
                };
        TypeLiteral<MythicLoader<MythicTierEnchantments>> mythicTierEnchantmentsLoaderLiteral =
                new TypeLiteral<MythicLoader<MythicTierEnchantments>>() {
                };
        TypeLiteral<MythicLoader<MythicTierLore>> mythicTierLoreLoaderLiteral =
                new TypeLiteral<MythicLoader<MythicTierLore>>() {
                };
        install(new FactoryModuleBuilder().implement(mythicTierLoaderLiteral, MythicTierLoader.class)
                .build(MythicTierLoaderFactory.class));
        install(new FactoryModuleBuilder().implement(mythicTierEnchantmentsLoaderLiteral,
                MythicTierEnchantmentsLoader.class).build(MythicTierEnchantmentsLoaderFactory.class));
        install(new FactoryModuleBuilder().implement(mythicTierLoreLoaderLiteral,
                MythicTierLoreLoader.class).build(MythicTierLoreLoaderFactory.class));
    }
}
