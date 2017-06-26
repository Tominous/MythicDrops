/**
 * The MIT License
 * Copyright (c) 2013 Pixel Outlaw
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import com.google.auto.value.AutoValue;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items.MythicItemType;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.values.Validated;
import java.util.Collection;
import org.bukkit.ChatColor;

@AutoValue
public abstract class MythicTier extends Validated {

  public static Builder builder() {
    return new AutoValue_MythicTier.Builder();
  }

  public abstract String key();

  public abstract String displayName();

  public abstract ChatColor displayColor();

  public abstract ChatColor identifierColor();

  public abstract Collection<MythicItemType> itemTypes();

  public abstract boolean broadcastOnFind();

  public abstract boolean infiniteDurability();

  public abstract double minimumDurabilityPercentage();

  public abstract double maximumDurabilityPercentage();

  public abstract MythicTierEnchantments mythicTierEnchantments();

  public abstract MythicTierLore mythicTierLore();

  public abstract double chanceToSpawnOnAMonster();

  public abstract double chanceToDropOnMonsterDeath();

  public abstract Collection<MythicTierComponent> components();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder key(String key);

    public abstract Builder displayName(String displayName);

    public abstract Builder displayColor(ChatColor displayColor);

    public abstract Builder identifierColor(ChatColor identifierColor);

    public abstract Builder itemTypes(Collection<MythicItemType> itemTypes);

    public abstract Builder broadcastOnFind(boolean broadcastOnFind);

    public abstract Builder infiniteDurability(boolean infiniteDurability);

    public abstract Builder minimumDurabilityPercentage(double minimumDurabilityPercentage);

    public abstract Builder maximumDurabilityPercentage(double maximumDurabilityPercentage);

    public abstract Builder mythicTierEnchantments(MythicTierEnchantments mythicTierEnchantments);

    public abstract Builder mythicTierLore(MythicTierLore mythicTierLore);

    public abstract Builder chanceToSpawnOnAMonster(double chanceToSpawnOnAMonster);

    public abstract Builder chanceToDropOnMonsterDeath(double chanceToDropOnMonsterDeath);

    public abstract Builder components(Collection<MythicTierComponent> components);

    public MythicTier build() {
      MythicTier mythicTier = autoBuild();
      mythicTier.check();
      return mythicTier;
    }

    abstract MythicTier autoBuild();
  }


}
