package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.TropicalFishBucketMeta

fun ItemStack.getOrCreateTropicalFishBucketMeta() =
    this.getOrCreateItemMeta() as? TropicalFishBucketMeta

fun ItemStack.hasTropicalFishBucketMeta() =
    this.getOrCreateItemMeta() is TropicalFishBucketMeta