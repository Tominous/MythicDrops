package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.block.banner.Pattern
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta

class HiltBanner(bannerType: BannerType, patterns: List<Pattern>) : ItemStack(bannerType.mat) {
    init {
        this.setPatterns(patterns)
    }

    fun addPattern(pattern: Pattern) {
        bannerMeta()?.addPattern(pattern)
    }

    fun getPattern(i: Int): Pattern? =
        bannerMeta()?.getPattern(i)

    fun getPatterns(): List<Pattern> =
        bannerMeta()?.patterns ?: emptyList()

    fun numberOfPatterns(): Int? =
        bannerMeta()?.numberOfPatterns()

    fun removePattern(i: Int): Pattern? =
        bannerMeta()?.removePattern(i)

    fun setPattern(i: Int, pattern: Pattern) {
        bannerMeta()?.setPattern(i, pattern)
    }

    fun setPatterns(patterns: List<Pattern>) {
        bannerMeta()?.patterns = patterns
    }

    private fun bannerMeta() = (this.getOrCreateItemMeta() as? BannerMeta)
}
