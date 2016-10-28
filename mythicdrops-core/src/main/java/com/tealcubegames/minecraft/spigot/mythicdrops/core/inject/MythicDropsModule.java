package com.tealcubegames.minecraft.spigot.mythicdrops.core.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.MythicDrops;
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
