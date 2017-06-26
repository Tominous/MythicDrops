package io.pixeloutlaw.minecraft.spigot.mythicdrops.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.MythicDrops;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.config.StartupConfig;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.LoggerManipulator;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.inject.MythicDropsCoreModule;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.config.StartupConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import javax.inject.Inject;

public final class MythicDropsPlugin extends JavaPlugin implements MythicDrops {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicDropsPlugin.class);

    private StartupConfigLoader startupConfigLoader;
    private StartupConfig startupConfig;

    private LoggerManipulator loggerManipulator;
    private LoaderManager loaderManager;

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new MythicDropsCoreModule(this));
        injector.injectMembers(this);

        getLogger().info("Loading startup properties...");
        setStartupConfigLoader(new StartupConfigLoader(this));
        setStartupConfig(getStartupConfigLoader().update());

        if (getStartupConfig().isDebugEnabled()) {
            getLoggerManipulator().setLoggerLevel(Level.DEBUG, "io.pixeloutlaw.minecraft.spigot.mythicdrops");
        }

        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    @Override
    public void enable() {
        LOGGER.debug("enable() - ENTER");
        LOGGER.debug("enable() - ENTER");

        // Print the various versions and environments in use
        LOGGER.debug("Using MythicDrops Common version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.common.PomData.VERSION);
        LOGGER.debug("Using MythicDrops API version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.api.PomData.VERSION);
        LOGGER.debug("Starting MythicDrops Core version " +
                io.pixeloutlaw.minecraft.spigot.mythicdrops.core.PomData.VERSION);

        LOGGER.debug("enable() - EXIT");
    }

    @Override
    public void disable() {
        LOGGER.debug("disable() - ENTER");
        LOGGER.debug("disable() - EXIT");
    }

    @Override
    public LoggerManipulator getLoggerManipulator() {
        return loggerManipulator;
    }

    @Inject
    public void setLoggerManipulator(LoggerManipulator loggerManipulator) {
        this.loggerManipulator = loggerManipulator;
    }

    @Override
    public LoaderManager getLoaderManager() {
        return loaderManager;
    }

    @Inject
    public void setLoaderManager(LoaderManager loaderManager) {
        this.loaderManager = loaderManager;
    }

    public StartupConfig getStartupConfig() {
        return startupConfig;
    }

    @Inject
    public void setStartupConfig(StartupConfig startupConfig) {
        this.startupConfig = startupConfig;
    }

    public StartupConfigLoader getStartupConfigLoader() {
        return startupConfigLoader;
    }

    public void setStartupConfigLoader(StartupConfigLoader startupConfigLoader) {
        this.startupConfigLoader = startupConfigLoader;
    }

}