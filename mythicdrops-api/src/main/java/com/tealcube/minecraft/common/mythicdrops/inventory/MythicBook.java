/*
 * This file is part of MythicDrops, licensed under the MIT License (MIT)
 *
 * Copyright (c) 2015 ToppleTheNun <https://github.com/Nunnery>
 * Copyright (c) contributors
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
package com.tealcube.minecraft.common.mythicdrops.inventory;

import javax.annotation.Nullable;

/**
 * Represents an implementation-neutral book used by MythicDrops.
 */
public interface MythicBook extends MythicItem {

    /**
     * Gets the author of this item.
     *
     * Returns an empty string if this item cannot have an author.
     *
     * @return author of this item
     */
    String getAuthor();

    /**
     * Sets the author of this item. A null parameter removes the author.
     *
     * @param author author to set
     * @return this item
     */
    MythicBook setAuthor(@Nullable String author);

    /**
     * Gets the pages of this item.
     *
     * Returns an empty {@code String[]} if there are no pages.
     *
     * @return pages of this item
     */
    String[] getPages();

    /**
     * Sets the pages of this item. A null parameter removes the pages.
     *
     * @param pages pages to set
     * @return this item
     */
    MythicBook setPages(@Nullable String[] pages);

}
