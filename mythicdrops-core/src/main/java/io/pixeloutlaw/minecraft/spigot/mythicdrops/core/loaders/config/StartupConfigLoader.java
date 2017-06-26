package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.config;

import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.config.StartupConfig;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.MythicDropsPlugin;

import javax.inject.Inject;

public final class StartupConfigLoader extends VersionedConfigConfigurateMythicLoader<StartupConfig> {

    /**
     * {@inheritDoc}
     */
    @Inject
    public StartupConfigLoader(MythicDropsPlugin mythicDropsPlugin) {
        super(StartupConfig.class, mythicDropsPlugin, "startup.yml");
    }

}