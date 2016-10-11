package com.tealcubegames.minecraft.spigot.mythicdrops.common.loaders;

/**
 * An interface defining an easy way to load and save objects.
 *
 * @param <T> Type of Object to load with MythicLoader
 * @author Richard Harrah
 */
public interface MythicLoader<T> {

    /**
     * Loads the object(s) that it is configured to load.
     *
     * @return loaded object(s)
     */
    T load();

    /**
     * Saves the object(s) that is is configured to save.
     *
     * @param t object(s) to save
     */
    void save(T t);

}
