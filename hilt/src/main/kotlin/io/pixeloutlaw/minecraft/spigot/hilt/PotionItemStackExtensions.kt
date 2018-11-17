package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta

fun ItemStack.getOrCreatePotionMeta() =
    this.getOrCreateItemMeta() as? PotionMeta

fun ItemStack.hasPotionMeta() =
    this.getOrCreateItemMeta() is PotionMeta