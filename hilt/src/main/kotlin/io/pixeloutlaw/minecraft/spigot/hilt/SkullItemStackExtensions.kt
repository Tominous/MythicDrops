package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

fun ItemStack.getOrCreateSkullMeta() =
    this.getOrCreateItemMeta() as? SkullMeta

fun ItemStack.hasSkullMeta() =
    this.getOrCreateItemMeta() is SkullMeta