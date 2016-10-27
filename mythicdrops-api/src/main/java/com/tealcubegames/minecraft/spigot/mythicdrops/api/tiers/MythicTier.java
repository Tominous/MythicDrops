/**
 * This file is part of MythicDrops_API, licensed under the MIT License.
 *
 * Copyright (C) 2013 Teal Cube Games
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers;

import com.tealcubegames.minecraft.spigot.mythicdrops.api.items.MythicEnchantment;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.items.MythicItemType;
import org.bukkit.ChatColor;
import org.immutables.value.Value;

import java.util.Collection;

/**
 * An interface that represents a tier.
 *
 * @author Richard Harrah
 */
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE)
@Value.Immutable
public abstract class MythicTier {

    public abstract String displayName();

    public abstract ChatColor displayColor();

    public abstract ChatColor identifierColor();

    public abstract Collection<MythicItemType> itemTypes();

    public abstract boolean broadcastOnFind();

    public abstract boolean infiniteDurability();

    public abstract double minimumDurabilityPercentage();

    public abstract double maximumDurabilityPercentage();

    public abstract Collection<MythicEnchantment> baseEnchantments();

    public abstract Collection<MythicEnchantment> bonusEnchantments();

    public abstract int minimumBonusEnchantments();

    public abstract int maximumBonusEnchantments();

    public abstract Collection<String> baseLore();

    public abstract Collection<String> bonusLore();

    public abstract int minimumBonusLore();

    public abstract int maximumBonusLore();

    public abstract double chanceToSpawnOnAMonster();

    public abstract double chanceToDropOnMonsterDeath();

    public abstract Collection<MythicTierComponent> components();

    public static Builder builder() {
        return ImmutableMythicTier.builder();
    }

    public interface Builder {
        Builder displayName(String displayName);

        Builder displayColor(ChatColor displayColor);

        Builder identifierColor(ChatColor identifierColor);

        Builder itemTypes(Collection<MythicItemType> itemTypes);

        Builder broadcastOnFind(boolean broadcastOnFind);

        Builder infiniteDurability(boolean infiniteDurability);

        Builder minimumDurabilityPercentage(double minimumDurabilityPercentage);

        Builder maximumDurabilityPercentage(double maximumDurabilityPercentage);

        Builder baseEnchantments(Collection<MythicEnchantment> baseEnchantments);

        Builder bonusEnchantments(Collection<MythicEnchantment> bonusEnchantments);

        Builder minimumBonusEnchantments(int minimumBonusEnchantments);

        Builder maximumBonusEnchantments(int maximumBonusEnchantments);

        Builder baseLore(Collection<String> baseLore);

        Builder bonusLore(Collection<String> bonusLore);

        Builder minimumBonusLore(int minimumBonusLore);

        Builder maximumBonusLore(int maximumBonusLore);

        Builder chanceToSpawnOnAMonster(double chanceToSpawnOnAMonster);

        Builder chanceToDropOnMonsterDeath(double chanceToDropOnMonsterDeath);

        Builder components(Collection<MythicTierComponent> components);

        MythicTier build();
    }

}
