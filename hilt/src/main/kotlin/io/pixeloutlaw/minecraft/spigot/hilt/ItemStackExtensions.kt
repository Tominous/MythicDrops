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

/**
 * Attempts to get any existing ItemMeta off of the ItemStack. Will create and apply
 * an ItemMeta if one doesn't exist.
 *
 * @return existing or new ItemMeta
 */
fun ItemStack.getOrCreateItemMeta(): ItemMeta {
    if (!this.hasItemMeta()) {
        this.itemMeta = Bukkit.getItemFactory().getItemMeta(this.type)
    }
    return this.itemMeta
}

/**
 * Acquires the ItemMeta for the ItemStack that matches ```T```.
 *
 * Effectively the same as calling ```getOrCreateItemMeta() as? T```.
 *
 * @param T subclass of ItemMeta
 * @return ItemMeta cast to T
 */
inline fun <reified T : ItemMeta> ItemStack.acquireItemMeta(): T {
    return this.getOrCreateItemMeta() as T
}

inline fun <reified IM : ItemMeta, R> ItemStack.acquireThenSetItemMeta(action: IM.() -> R): R {
    val itemMeta = this.acquireItemMeta<IM>()
    val retValue = action(itemMeta)
    this.itemMeta = itemMeta
    return retValue
}

inline fun <reified IM : ItemMeta> ItemStack.acquireThenSetItemMeta(action: IM.() -> Unit) {
    val itemMeta = this.acquireItemMeta<IM>()
    action(itemMeta)
    this.itemMeta = itemMeta
}

fun ItemStack.addAttributeModifier(attribute: Attribute, attributeModifier: AttributeModifier) =
    acquireThenSetItemMeta<ItemMeta, Boolean> { this.addAttributeModifier(attribute, attributeModifier) }

fun ItemStack.addItemFlags(vararg itemFlags: ItemFlag) =
    acquireThenSetItemMeta<ItemMeta> { this.addItemFlags(*itemFlags) }

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
    acquireThenSetItemMeta<ItemMeta, Boolean> { this.removeAttributeModifier(attribute) }

fun ItemStack.removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean =
    acquireThenSetItemMeta<ItemMeta, Boolean> { this.removeAttributeModifier(attribute, modifier) }

fun ItemStack.removeAttributeModifier(slot: EquipmentSlot): Boolean =
    acquireThenSetItemMeta<ItemMeta, Boolean> { this.removeAttributeModifier(slot) }

fun ItemStack.removeItemFlags(vararg itemFlags: ItemFlag) =
    acquireThenSetItemMeta<ItemMeta> { this.removeItemFlags(*itemFlags) }

fun ItemStack.setAttributeModifiers(attributeModifiers: Multimap<Attribute, AttributeModifier>) =
    acquireThenSetItemMeta<ItemMeta> { this.attributeModifiers = attributeModifiers }

fun ItemStack.setDisplayName(string: String?) =
    acquireThenSetItemMeta<ItemMeta> { this.displayName = string }

fun ItemStack.setLocalizedName(string: String?) =
    acquireThenSetItemMeta<ItemMeta> { this.localizedName = string }

fun ItemStack.setLore(list: List<String>) =
    acquireThenSetItemMeta<ItemMeta> { this.lore = list }

fun ItemStack.setUnbreakable(unbreakable: Boolean) =
    acquireThenSetItemMeta<ItemMeta> { this.isUnbreakable = unbreakable }
