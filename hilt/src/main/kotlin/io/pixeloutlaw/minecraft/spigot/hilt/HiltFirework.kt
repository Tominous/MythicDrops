package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkMeta

class HiltFirework(fireworkEffects: Collection<FireworkEffect>, power: Int) : ItemStack(Material.FIREWORK_ROCKET) {
    init {
        addFireworkEffects(fireworkEffects)
        setPower(power)
    }

    fun addFireworkEffect(effect: FireworkEffect) {
        fireworkMeta()?.addEffect(effect)
    }

    fun addFireworkEffects(effects: Iterable<FireworkEffect>) {
        fireworkMeta()?.addEffects(effects)
    }

    fun addFireworkEffects(vararg effects: FireworkEffect) {
        fireworkMeta()?.addEffects(*effects)
    }

    fun clearEffects() {
        fireworkMeta()?.clearEffects()
    }

    fun getEffects(): List<FireworkEffect> =
        fireworkMeta()?.effects ?: emptyList()

    fun getEffectsSize(): Int? =
        fireworkMeta()?.effectsSize

    fun getPower(): Int? =
        fireworkMeta()?.power

    fun hasEffects(): Boolean? =
        fireworkMeta()?.hasEffects()

    fun removeEffect(index: Int) {
        fireworkMeta()?.removeEffect(index)
    }

    fun setPower(power: Int) {
        fireworkMeta()?.power = power
    }

    private fun fireworkMeta(): FireworkMeta? =
        (this.getOrCreateItemMeta() as? FireworkMeta)

}