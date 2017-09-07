/*
 * The MIT License
 * Copyright © 2013 Pixel Outlaw
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
import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.values.Validated;
import org.apache.commons.lang3.StringUtils;

@AutoValue
public abstract class MythicTierComponent extends Validated {

    public static Builder builder() {
        return new AutoValue_MythicTierComponent.Builder();
    }

    public abstract String id();

    public abstract MythicLoader<MythicTierComponent> loader();

    @Override
    protected void check() {
        Preconditions.checkState(!StringUtils.isBlank(id()), "ID cannot be empty or null");
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder id(String id);

        public abstract Builder loader(MythicLoader<MythicTierComponent> loader);

        public MythicTierComponent build() {
            MythicTierComponent mythicTierComponent = autoBuild();
            mythicTierComponent.check();
            return mythicTierComponent;
        }

        abstract MythicTierComponent autoBuild();

    }

}
