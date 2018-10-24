package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.KnowledgeBookMeta

fun ItemStack.getOrCreateKnowledgeBookMeta() =
    this.getOrCreateItemMeta() as? KnowledgeBookMeta

fun ItemStack.hasKnowledgeBookMeta() =
    this.getOrCreateItemMeta() is KnowledgeBookMeta