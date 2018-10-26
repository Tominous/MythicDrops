package io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.annotation.plugin.Plugin

@Plugin(name = BuildConfig.NAME, version = BuildConfig.VERSION)
class MythicDropsPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        logger.info("onEnable")
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        logger.info("onDisable")
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        logger.info("Hi mom!")
    }
}