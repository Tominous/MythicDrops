package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta

fun ItemStack.getOrCreateLeatherArmorMeta() =
    this.getOrCreateItemMeta() as? LeatherArmorMeta

fun ItemStack.hasLeatherArmorMeta() =
    this.getOrCreateItemMeta() is LeatherArmorMeta