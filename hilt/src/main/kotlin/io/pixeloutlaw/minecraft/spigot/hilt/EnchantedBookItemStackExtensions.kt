package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta

fun ItemStack.getOrCreateEnchantmentStorageMeta() =
    this.getOrCreateItemMeta() as? EnchantmentStorageMeta

fun ItemStack.hasEnchantmentStorageMeta() =
    this.getOrCreateItemMeta() is EnchantmentStorageMeta