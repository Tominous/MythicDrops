package io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.annotation.plugin.Plugin

@Plugin(name = BuildConfig.NAME, version = BuildConfig.VERSION)
class MythicDropsPlugin : JavaPlugin() {

    override fun onEnable() {
        logger.info("onEnable")
    }

    override fun onDisable() {
        logger.info("onDisable")
    }
}