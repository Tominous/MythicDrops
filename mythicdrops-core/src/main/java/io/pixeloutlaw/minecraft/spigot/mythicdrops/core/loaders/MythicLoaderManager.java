/**
 * This file is part of mythicdrops-core, licensed under the MIT License.
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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders;

import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierComponent;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tier.MythicTierLoaderFactory;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;

/**
 * An implementation of the LoaderManager.
 *
 * @author Richard Harrah
 */
public final class MythicLoaderManager implements LoaderManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicLoaderManager.class);
    private MythicTierLoaderFactory mythicTierLoaderFactory;

    @Inject
    public MythicLoaderManager(MythicTierLoaderFactory mythicTierLoaderFactory) {
        this.mythicTierLoaderFactory = mythicTierLoaderFactory;
    }

    @Override
    public MythicLoader<MythicTier> createNewMythicTierLoader(String fileName) {
        Preconditions.checkNotNull(fileName, "file cannot be null");
        LOGGER.debug("Creating a new MythicTier loader for file: {}", fileName);
        return mythicTierLoaderFactory.create(fileName);
    }

    @Override
    public Collection<MythicLoader<MythicTierComponent>> getMythicTierComponentLoaders() {
        throw new NotImplementedException();
    }

    @Override
    public boolean registerMythicTierComponentLoader(MythicLoader<MythicTierComponent> loader) {
        throw new NotImplementedException();
    }

}
