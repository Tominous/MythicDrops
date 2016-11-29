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
package com.tealcubegames.minecraft.spigot.mythicdrops.api.items;

import com.google.auto.value.AutoValue;
import org.bukkit.Material;

import java.util.Collection;

/**
 * An interface that represents an item type.
 *
 * @author Richard Harrah
 */
@AutoValue
public abstract class MythicItemType {

    public static Builder builder() {
        return new AutoValue_MythicItemType.Builder();
    }

    public abstract String name();

    public abstract Collection<Material> materials();

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder name(String name);

        public abstract Builder materials(Collection<Material> materials);

        public abstract MythicItemType build();
    }

}
