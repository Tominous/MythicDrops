package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta

fun ItemStack.getOrCreateBannerMeta() =
    this.getOrCreateItemMeta() as? BannerMeta

fun ItemStack.hasBannerMeta() =
    this.getOrCreateItemMeta() is BannerMeta