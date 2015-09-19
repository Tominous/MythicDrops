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

import com.google.common.reflect.TypeToken;

import com.tealcube.minecraft.sponge.mythicdrops.api.registries.Registry;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * An abstract base class that partially implements {@code Registry}.
 * @param <I> identifier for Registry components
 * @param <C> components for Registry
 */
public abstract class AbstractRegistry<I, C> implements Registry<I, C> {

    private final Type identifierType;
    private final Type componentType;

    /**
     * Constructs a new AbstractRegistry instance.
     */
    protected AbstractRegistry() {
        identifierType = resolveType(0);
        componentType = resolveType(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Type identifierType() {
        return identifierType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Type componentType() {
        return componentType;
    }

    /**
     * A method that resolves the Type of a generic based on the index in which it occurs.
     * @param index index
     * @return Type of a generic
     */
    protected Type resolveType(int index) {
        TypeToken<Registry<?, ?>> token = new TypeToken<Registry<?, ?>>() {
        };
        TypeVariable<Class<Registry>>[] typeParameters = Registry.class.getTypeParameters();
        TypeToken<?> componentType = token.resolveType(typeParameters[Math.min(Math.max(0, index),
                typeParameters.length)]);
        return componentType.getType();
    }

}
