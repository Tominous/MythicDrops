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
