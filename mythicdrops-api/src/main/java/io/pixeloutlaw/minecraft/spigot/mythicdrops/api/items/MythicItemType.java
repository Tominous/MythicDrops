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
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.values.Validated;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;

@AutoValue
public abstract class MythicItemType extends Validated {

  public static Builder builder() {
    return new AutoValue_MythicItemType.Builder();
  }

  public abstract String name();

  public abstract Collection<Material> materials();

  @Override
  protected void check() {
    Preconditions.checkState(!StringUtils.isBlank(name()), "Name cannot be empty or null");
    Preconditions.checkState(!materials().isEmpty(), "Materials cannot be empty");
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder name(String name);

    public abstract Builder materials(Collection<Material> materials);

    public MythicItemType build() {
      MythicItemType mythicItemType = autoBuild();
      mythicItemType.check();
      return mythicItemType;
    }

    abstract MythicItemType autoBuild();
  }

}
