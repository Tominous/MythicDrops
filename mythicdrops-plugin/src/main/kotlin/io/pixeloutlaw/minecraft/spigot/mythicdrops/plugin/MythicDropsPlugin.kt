package io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin

import org.bukkit.plugin.java.JavaPlugin

class MythicDropsPlugin : JavaPlugin() {

    override fun onEnable() {
        logger.info("onEnable")
    }

    override fun onDisable() {
        logger.info("onDisable")
    }
}