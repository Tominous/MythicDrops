package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

fun ItemStack.getOrCreateBookMeta() =
    this.getOrCreateItemMeta() as? BookMeta

fun ItemStack.hasBookMeta() =
    this.getOrCreateItemMeta() is BookMeta