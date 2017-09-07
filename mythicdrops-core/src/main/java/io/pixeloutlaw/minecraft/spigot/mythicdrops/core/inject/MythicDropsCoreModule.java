/*
 * The MIT License
 * Copyright © 2013 Pixel Outlaw
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.config.StartupConfig;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.managers.TierManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierEnchantments;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierLore;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.LoggerManipulator;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.MessageUtils;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.TextManipulator;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.MythicDropsPlugin;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.MythicLoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierEnchantmentsLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierEnchantmentsLoaderFactory;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierLoaderFactory;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierLoreLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers.MythicTierLoreLoaderFactory;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.managers.MythicTierManager;
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
        bind(TierManager.class).to(MythicTierManager.class);
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
