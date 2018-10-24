package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkEffectMeta

fun ItemStack.getOrCreateFireworkEffectMeta() =
    this.getOrCreateItemMeta() as? FireworkEffectMeta

fun ItemStack.hasFireworkEffectMeta() =
    this.getOrCreateItemMeta() is FireworkEffectMeta