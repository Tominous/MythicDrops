package com.tealcubegames.minecraft.spigot.mythicdrops.core;

import com.tealcubegames.minecraft.spigot.mythicdrops.api.MythicDrops;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MythicDropsPlugin extends JavaPlugin implements MythicDrops {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicDropsPlugin.class);

    @Override
    public void onEnable() {
        LOGGER.debug("onEnable() - ENTER");
        LOGGER.debug("Using MythicDrops Common version " +
                com.tealcubegames.minecraft.spigot.mythicdrops.common.PomData.VERSION);
        LOGGER.debug("Using MythicDrops API version " +
                com.tealcubegames.minecraft.spigot.mythicdrops.api.PomData.VERSION);
        LOGGER.debug("Starting MythicDrops Core version " +
                com.tealcubegames.minecraft.spigot.mythicdrops.core.PomData.VERSION);
        LOGGER.debug("onEnable() - EXIT");
    }

    @Override
    public void onDisable() {
        LOGGER.debug("onDisable() - ENTER");
        LOGGER.debug("onDisable() - EXIT");
    }

}
