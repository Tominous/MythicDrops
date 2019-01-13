package io.pixeloutlaw.minecraft.spigot.hilt

import com.google.common.collect.Multimap
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * Acquires the ItemMeta, runs an action on it, then sets the ItemMeta.
 *
 * @param action Action to perform on the ItemMeta
 */
inline fun ItemStack.getThenSetItemMeta(action: ItemMeta.() -> Unit) {
    val itemMeta = this.itemMeta
    action(itemMeta)
    this.itemMeta = itemMeta
}

/**
 * Acquires the ItemMeta, runs an action on it, sets the ItemMeta, and returns the result of the action.
 *
 * @param R Return type of the Action
 * @param action Action to perform on the ItemMeta
 * @return result of the action
 */
inline fun <R> ItemStack.getThenSetItemMeta(action: ItemMeta.() -> R): R {
    val itemMeta = this.itemMeta
    val retValue = action(itemMeta)
    this.itemMeta = itemMeta
    return retValue
}

/**
 * Checks if the ItemMeta of the current ItemStack is of type `IM`.
 *
 * @param IM Subclass of ItemMeta
 * @return if ItemMeta is of expected type
 */
inline fun <reified IM : ItemMeta> ItemStack.hasItemMetaOf(): Boolean {
    return this.itemMeta is IM
}

/**
 * Acquires the ItemMeta of type `IM`, runs an action on it, then sets the ItemMeta.
 *
 * @param IM Subclass of ItemMeta
 * @param action Action to perform on the ItemMeta
 */
inline fun <reified IM : ItemMeta> ItemStack.getThenSetItemMetaAs(action: IM.() -> Unit) {
    val itemMeta = this.itemMeta as IM
    action(itemMeta)
    this.itemMeta = itemMeta
}

/**
 * Acquires the ItemMeta of type `IM`, runs an action on it, sets the ItemMeta,
 * and returns the result of the action.
 *
 * @param IM Subclass of ItemMeta
 * @param R Return type of action
 * @param action Action to perform on the ItemMeta
 * @return result of the action
 */
inline fun <reified IM : ItemMeta, R> ItemStack.getThenSetItemMetaAs(action: IM.() -> R): R {
    val itemMeta = this.itemMeta as IM
    val retValue = action(itemMeta)
    this.itemMeta = itemMeta
    return retValue
}

fun ItemStack.addAttributeModifier(attribute: Attribute, attributeModifier: AttributeModifier) =
    getThenSetItemMeta<Boolean> { this.addAttributeModifier(attribute, attributeModifier) }

fun ItemStack.addItemFlags(vararg itemFlags: ItemFlag) =
    getThenSetItemMeta { this.addItemFlags(*itemFlags) }

fun ItemStack.getAttributeModifiers(): Multimap<Attribute, AttributeModifier> =
    this.itemMeta.attributeModifiers

fun ItemStack.getAttributeModifiers(attribute: Attribute): Collection<AttributeModifier> =
    this.itemMeta.getAttributeModifiers(attribute)

fun ItemStack.getAttributeModifiers(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier> =
    this.itemMeta.getAttributeModifiers(slot)

fun ItemStack.getDisplayName(): String? = this.itemMeta.let {
    return if (it.hasDisplayName()) {
        it.displayName
    } else {
        null
    }
}

fun ItemStack.getItemFlags(): Set<ItemFlag> = this.itemMeta.itemFlags

fun ItemStack.getLocalizedName(): String? = this.itemMeta.let {
    return if (it.hasLocalizedName()) {
        it.localizedName
    } else {
        null
    }
}

fun ItemStack.getLore(): List<String> = this.itemMeta.let {
    return if (it.hasLore()) {
        it.lore
    } else {
        emptyList()
    }
}

fun ItemStack.hasAttributeModifiers(): Boolean = this.itemMeta.hasAttributeModifiers()

fun ItemStack.hasConflictingEnchantment(ench: Enchantment): Boolean =
    this.itemMeta.hasConflictingEnchant(ench)

fun ItemStack.hasDisplayName(): Boolean = this.itemMeta.hasDisplayName()

fun ItemStack.hasItemFlag(flag: ItemFlag): Boolean = this.itemMeta.hasItemFlag(flag)

fun ItemStack.hasLocalizedName(): Boolean = this.itemMeta.hasLocalizedName()

fun ItemStack.hasLore(): Boolean = this.itemMeta.hasLore()

fun ItemStack.isUnbreakable(): Boolean = this.itemMeta.isUnbreakable

fun ItemStack.removeAttributeModifier(attribute: Attribute) =
    getThenSetItemMeta<Boolean> { this.removeAttributeModifier(attribute) }

fun ItemStack.removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier) =
    getThenSetItemMeta<Boolean> { this.removeAttributeModifier(attribute, modifier) }

fun ItemStack.removeAttributeModifier(slot: EquipmentSlot) =
    getThenSetItemMeta<Boolean> { this.removeAttributeModifier(slot) }

fun ItemStack.removeItemFlags(vararg itemFlags: ItemFlag) =
    getThenSetItemMeta { this.removeItemFlags(*itemFlags) }

fun ItemStack.setAttributeModifiers(attributeModifiers: Multimap<Attribute, AttributeModifier>) =
    getThenSetItemMeta { this.attributeModifiers = attributeModifiers }

fun ItemStack.setDisplayName(string: String?) =
    getThenSetItemMeta { this.displayName = string }

fun ItemStack.setLocalizedName(string: String?) =
    getThenSetItemMeta { this.localizedName = string }

fun ItemStack.setLore(list: List<String>) =
    getThenSetItemMeta { this.lore = list }

fun ItemStack.setUnbreakable(unbreakable: Boolean) =
    getThenSetItemMeta { this.isUnbreakable = unbreakable }
