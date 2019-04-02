package io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin

import com.google.common.base.Strings
import io.pixeloutlaw.minecraft.spigot.hilt.getDisplayName
import io.pixeloutlaw.minecraft.spigot.hilt.setDisplayName
import io.pixeloutlaw.minecraft.spigot.mythicdrops.BuildConfig
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
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
    fun onBlockDamageEvent(event: BlockDamageEvent) {
        val itemInHand = event.itemInHand
        itemInHand.setDisplayName("${Strings.nullToEmpty(itemInHand.getDisplayName())}d")
        event.player.updateInventory()
    }
}