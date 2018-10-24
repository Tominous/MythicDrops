package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta

fun ItemStack.getOrCreateMapMeta() =
    this.getOrCreateItemMeta() as? MapMeta

fun ItemStack.hasMapMeta() =
    this.getOrCreateItemMeta() is MapMeta