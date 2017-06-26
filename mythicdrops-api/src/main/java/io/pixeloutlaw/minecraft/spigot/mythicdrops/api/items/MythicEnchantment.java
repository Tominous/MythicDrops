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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.values.Validated;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.enchantments.Enchantment;

@AutoValue
public abstract class MythicEnchantment extends Validated {

  private static final Range<Integer> ACCEPTABLE_ENCHANTMENT_RANGE = Range.closed(1, 127);

  public static Builder builder(String s) {
    Preconditions.checkNotNull(s);
    Preconditions.checkArgument(s.contains(":"));
    String[] split = s.split(":");
    Preconditions.checkArgument(split.length >= 2);
    Enchantment ench = null;
    int value1 = 0;
    int value2 = 0;
    switch (split.length) {
      case 0:
        break;
      case 1:
        break;
      case 2:
        ench = Enchantment.getByName(split[0]);
        if (ench == null) {
          break;
        }
        value1 = value2 = NumberUtils.toInt(split[1], 1);
        break;
      default:
        ench = Enchantment.getByName(split[0]);
        if (ench == null) {
          break;
        }
        value1 = NumberUtils.toInt(split[1], 1);
        value2 = NumberUtils.toInt(split[2], 1);
        break;
    }
    return MythicEnchantment.builder().enchantment(ench)
        .minimumLevel(clamp(Math.min(value1, value2)))
        .maximumLevel(clamp(Math.max(value1, value2)));
  }

  public static Builder builder() {
    return new AutoValue_MythicEnchantment.Builder();
  }

  private static int clamp(int toClamp) {
    return Math.max(ACCEPTABLE_ENCHANTMENT_RANGE.lowerEndpoint(),
        Math.min(ACCEPTABLE_ENCHANTMENT_RANGE.upperEndpoint(), toClamp));
  }

  public abstract Enchantment enchantment();

  public abstract int minimumLevel();

  public abstract int maximumLevel();

  @Override
  protected void check() {
    Preconditions.checkState(enchantment() != null, "Enchantment cannot be null");
    Preconditions.checkState(ACCEPTABLE_ENCHANTMENT_RANGE.contains(minimumLevel()),
        "minimumLevel must be between " + ACCEPTABLE_ENCHANTMENT_RANGE.toString());
    Preconditions.checkState(ACCEPTABLE_ENCHANTMENT_RANGE.contains(maximumLevel()),
        "maximumLevel must be between " + ACCEPTABLE_ENCHANTMENT_RANGE.toString());
    Preconditions.checkState(minimumLevel() <= maximumLevel(),
        "minimumLevel must be less than or equal to maximumLevel");
    Preconditions.checkState(maximumLevel() >= minimumLevel(),
        "maximumLevel must be greater than or equal to minimumLevel");
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder enchantment(Enchantment enchantment);

    public abstract Builder minimumLevel(int minimumLevel);

    public abstract Builder maximumLevel(int maximumLevel);

    public MythicEnchantment build() {
      MythicEnchantment mythicEnchantment = autoBuild();
      mythicEnchantment.check();
      return mythicEnchantment;
    }

    abstract MythicEnchantment autoBuild();

  }

}
