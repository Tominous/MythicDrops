/**
 * This file is part of mythicdrops-core, licensed under the MIT License.
 *
 * Copyright (C) 2016 Richard Harrah <topplethenunnery@gmail.com>
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
