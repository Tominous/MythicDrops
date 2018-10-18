package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta

class HiltEnchantedBook(enchantmentMap: Map<Enchantment, Int>) : ItemStack(Material.ENCHANTED_BOOK) {
    init {
        setStoredEnchantments(enchantmentMap)
    }

    fun addStoredEnchantment(ench: Enchantment, level: Int, ignoreLevelRestriction: Boolean): Boolean? =
        enchantmentStorageMeta()?.addStoredEnchant(ench, level, ignoreLevelRestriction)

    fun getStoredEnchantmentLevel(ench: Enchantment): Int? =
        enchantmentStorageMeta()?.getStoredEnchantLevel(ench)

    fun getStoredEnchantments(): Map<Enchantment, Int> =
        enchantmentStorageMeta()?.storedEnchants ?: emptyMap()

    fun hasConflictingStoredEnchantment(ench: Enchantment): Boolean? =
        enchantmentStorageMeta()?.hasConflictingStoredEnchant(ench)

    fun hasStoredEnchant(ench: Enchantment): Boolean? =
        enchantmentStorageMeta()?.hasStoredEnchant(ench)

    fun hasStoredEnchants(): Boolean? =
        enchantmentStorageMeta()?.hasStoredEnchants()

    fun removeStoredEnchantment(ench: Enchantment): Boolean? =
        enchantmentStorageMeta()?.removeStoredEnchant(ench)

    fun setStoredEnchantments(enchantments: Map<Enchantment, Int>) {
        enchantmentStorageMeta()?.let {
            if (it.hasStoredEnchants()) {
                for (entry in it.storedEnchants) {
                    it.removeStoredEnchant(entry.key)
                }
            }
            for ((key, value) in enchantments) {
                it.addStoredEnchant(key, value, true)
            }
        }
    }

    private fun enchantmentStorageMeta(): EnchantmentStorageMeta? =
        (this.getOrCreateItemMeta() as? EnchantmentStorageMeta)
}