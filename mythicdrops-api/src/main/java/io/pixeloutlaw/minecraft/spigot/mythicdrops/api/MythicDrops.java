package io.pixeloutlaw.minecraft.spigot.mythicdrops.api;

import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.LoggerManipulator;

/**
 * An interface for interacting with the MythicDrops system.
 */
public interface MythicDrops {

  /**
   * Gets the current LoaderManager instance.
   * @return current LoaderManager instance
   */
  LoaderManager getLoaderManager();

  /**
   * Gets the LoggerManipulator instance.
   * @return current LoggerManipulator instance
   */
  LoggerManipulator getLoggerManipulator();

  /**
   * Runs through the enable process.
   */
  void enable();

  /**
   * Runs through the disable process.
   */
  void disable();

}
