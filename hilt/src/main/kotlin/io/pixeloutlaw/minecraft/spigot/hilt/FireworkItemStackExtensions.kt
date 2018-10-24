package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkMeta

fun ItemStack.getOrCreateFireworkMeta() =
    this.getOrCreateItemMeta() as? FireworkMeta

fun ItemStack.hasFireworkMeta() =
    this.getOrCreateItemMeta() is FireworkMeta