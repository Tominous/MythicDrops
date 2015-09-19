/*
 * This file is part of MythicDrops, licensed under the MIT License (MIT)
 *
 * Copyright (c) 2015 Richard Harrah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.tealcube.minecraft.sponge.mythicdrops.impl;

import com.google.common.base.Optional;

import com.tealcube.minecraft.sponge.mythicdrops.api.registries.Registry;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

/**
 * Contains all of the data for Hoard, as well as interacting with creating new components.
 */
public final class MythicDropsRegistry extends AbstractRegistry<Type, Registry<?, ?>> {

    private final Map<Type, Registry<?, ?>> registryMap;

    public MythicDropsRegistry() {
        super();
        registryMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean has(@Nonnull Type id) {
        return registryMap.containsKey(id);
    }

    @Override
    public Registry<Type, Registry<?, ?>> add(@Nonnull Registry<?, ?> registry) {
        registryMap.put(registry.identifierType(), registry);
        return this;
    }

    @Override
    public Registry<Type, Registry<?, ?>> remove(@Nonnull Type id) {
        registryMap.remove(id);
        return this;
    }

    @Override
    public Set<Registry<?, ?>> get() {
        Set<Registry<?, ?>> registries = new HashSet<>(registryMap.values());
        return Collections.unmodifiableSet(registries);
    }

    @Override
    public Optional<Registry<?, ?>> get(@Nonnull Type id) {
        return Optional.fromNullable(registryMap.get(id));
    }

}
