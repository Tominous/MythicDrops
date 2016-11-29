/**
 * This file is part of mythicdrops-api, licensed under the MIT License.
 *
 * Copyright (C) 2016 Pixel Outlaw <topplethenun@pixeloutlaw.io>
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

import com.google.auto.value.AutoValue;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.items.MythicEnchantment;
import com.tealcubegames.minecraft.spigot.mythicdrops.api.items.MythicItemType;
import org.bukkit.ChatColor;

import java.util.Collection;

/**
 * An interface that represents a tier.
 *
 * @author Richard Harrah
 */
@AutoValue
public abstract class MythicTier {

    public static Builder builder() {
        return new AutoValue_MythicTier.Builder();
    }

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

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder displayName(String displayName);

        public abstract Builder displayColor(ChatColor displayColor);

        public abstract Builder identifierColor(ChatColor identifierColor);

        public abstract Builder itemTypes(Collection<MythicItemType> itemTypes);

        public abstract Builder broadcastOnFind(boolean broadcastOnFind);

        public abstract Builder infiniteDurability(boolean infiniteDurability);

        public abstract Builder minimumDurabilityPercentage(double minimumDurabilityPercentage);

        public abstract Builder maximumDurabilityPercentage(double maximumDurabilityPercentage);

        public abstract Builder baseEnchantments(Collection<MythicEnchantment> baseEnchantments);

        public abstract Builder bonusEnchantments(Collection<MythicEnchantment> bonusEnchantments);

        public abstract Builder minimumBonusEnchantments(int minimumBonusEnchantments);

        public abstract Builder maximumBonusEnchantments(int maximumBonusEnchantments);

        public abstract Builder baseLore(Collection<String> baseLore);

        public abstract Builder bonusLore(Collection<String> bonusLore);

        public abstract Builder minimumBonusLore(int minimumBonusLore);

        public abstract Builder maximumBonusLore(int maximumBonusLore);

        public abstract Builder chanceToSpawnOnAMonster(double chanceToSpawnOnAMonster);

        public abstract Builder chanceToDropOnMonsterDeath(double chanceToDropOnMonsterDeath);

        public abstract Builder components(Collection<MythicTierComponent> components);

        public abstract MythicTier build();
    }

}
