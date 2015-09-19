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
package com.tealcube.minecraft.sponge.mythicdrops.api.registries;

import com.google.common.base.Optional;

import java.lang.reflect.Type;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * Represents a managed group of components.
 *
 * @param <I> Identifier for Component
 * @param <C> Type of Component
 */
public interface Registry<I, C> {

    /**
     * Returns true if the {@code Component} with the given id is managed by this Registry, false if not.
     *
     * @param id id of component
     * @return if component is managed
     */
    boolean has(@Nonnull I id);

    /**
     * Adds the given component to this Registry. Updates existing entry if already managed.
     *
     * @param component component to add
     * @return this Registry
     */
    Registry<I, C> add(@Nonnull C component);

    /**
     * Removes the {@code Component} with the given {@code Identifier} from this Registry. Does nothing if not already
     * managed.
     *
     * @param id id of component
     * @return this Registry
     */
    Registry<I, C> remove(@Nonnull I id);

    /**
     * Fetches an unmodifiable copy of the components managed by this Registry.
     *
     * @return unmodifiable copy of components
     */
    Set<C> get();

    /**
     * Fetches and returns an Optional wrapper around the component with the given id.
     *
     * @param id id of component
     * @return Optional wrapper around component with given id
     */
    Optional<C> get(@Nonnull I id);

    /**
     * Returns the Type of the identifier for components in this Registry.
     *
     * @return identifier Type
     */
    Type identifierType();

    /**
     * Returns the Type of the components in this Registry.
     *
     * @return component Type
     */
    Type componentType();

}
