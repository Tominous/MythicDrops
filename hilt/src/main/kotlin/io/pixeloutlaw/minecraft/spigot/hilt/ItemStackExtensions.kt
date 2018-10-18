package io.pixeloutlaw.minecraft.spigot.hilt

import com.google.common.collect.Multimap
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

fun ItemStack.getOrCreateItemMeta(): ItemMeta {
    if (!this.hasItemMeta()) {
        this.itemMeta = Bukkit.getItemFactory().getItemMeta(this.type)
    }
    return this.itemMeta
}

fun ItemStack.addAttributeModifier(attribute: Attribute, attributeModifier: AttributeModifier): Boolean =
    this.getOrCreateItemMeta().addAttributeModifier(attribute, attributeModifier)

fun ItemStack.addItemFlags(vararg itemFlags: ItemFlag) = this.getOrCreateItemMeta().addItemFlags(*itemFlags)

fun ItemStack.getAttributeModifiers(): Multimap<Attribute, AttributeModifier> =
    this.getOrCreateItemMeta().attributeModifiers

fun ItemStack.getAttributeModifiers(attribute: Attribute): Collection<AttributeModifier> =
    this.getOrCreateItemMeta().getAttributeModifiers(attribute)

fun ItemStack.getAttributeModifiers(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier> =
    this.getOrCreateItemMeta().getAttributeModifiers(slot)

fun ItemStack.getDisplayName(): String? = this.getOrCreateItemMeta().let {
    return if (it.hasDisplayName()) {
        it.displayName
    } else {
        null
    }
}

fun ItemStack.getItemFlags(): Set<ItemFlag> = this.getOrCreateItemMeta().itemFlags

fun ItemStack.getLocalizedName(): String? = this.getOrCreateItemMeta().let {
    return if (it.hasLocalizedName()) {
        it.localizedName
    } else {
        null
    }
}

fun ItemStack.getLore(): List<String> = this.getOrCreateItemMeta().let {
    return if (it.hasLore()) {
        it.lore
    } else {
        emptyList()
    }
}

fun ItemStack.hasAttributeModifiers(): Boolean = this.getOrCreateItemMeta().hasAttributeModifiers()

fun ItemStack.hasConflictingEnchantment(ench: Enchantment): Boolean =
    this.getOrCreateItemMeta().hasConflictingEnchant(ench)

fun ItemStack.hasDisplayName(): Boolean = this.getOrCreateItemMeta().hasDisplayName()

fun ItemStack.hasItemFlag(flag: ItemFlag): Boolean = this.getOrCreateItemMeta().hasItemFlag(flag)

fun ItemStack.hasLocalizedName(): Boolean = this.getOrCreateItemMeta().hasLocalizedName()

fun ItemStack.hasLore(): Boolean = this.getOrCreateItemMeta().hasLore()

fun ItemStack.isUnbreakable(): Boolean = this.getOrCreateItemMeta().isUnbreakable

fun ItemStack.removeAttributeModifier(attribute: Attribute): Boolean =
    this.getOrCreateItemMeta().removeAttributeModifier(attribute)

fun ItemStack.removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean =
    this.getOrCreateItemMeta().removeAttributeModifier(attribute, modifier)

fun ItemStack.removeAttributeModifier(slot: EquipmentSlot): Boolean =
    this.getOrCreateItemMeta().removeAttributeModifier(slot)

fun ItemStack.removeItemFlags(vararg itemFlags: ItemFlag) = this.getOrCreateItemMeta().removeItemFlags(*itemFlags)

fun ItemStack.setAttributeModifiers(attributeModifiers: Multimap<Attribute, AttributeModifier>) {
    this.getOrCreateItemMeta().attributeModifiers = attributeModifiers
}

fun ItemStack.setDisplayName(string: String?) {
    this.getOrCreateItemMeta().displayName = string
}

fun ItemStack.setLocalizedName(string: String?) {
    this.getOrCreateItemMeta().localizedName = string
}

fun ItemStack.setLore(list: List<String>) {
    this.getOrCreateItemMeta().lore = list
}

fun ItemStack.setUnbreakable(unbreakable: Boolean) {
    this.getOrCreateItemMeta().isUnbreakable = unbreakable
}
