package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders;

import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierComponent;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierEnchantments;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierLore;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;

import java.util.Collection;

/**
 * Represents a general manager to deal with {@code MythicLoader}s.
 *
 * @author Richard Harrah
 */
public interface LoaderManager {

  /**
   * Creates a new {@link MythicLoader} for {@link MythicTier}s.
   *
   * @param fileName File from which to load
   * @return new MythicLoader for a MythicTier
   */
  MythicLoader<MythicTier> createNewMythicTierLoader(String fileName);

  /**
   * Creates a new {@link MythicLoader} for {@link MythicTierEnchantments}es.
   *
   * @param fileName File from which to load
   * @return a new MythicLoader for a MythicTierEnchantments
   */
  MythicLoader<MythicTierEnchantments> createMythicTierEnchantmentsLoader(String fileName);

  MythicLoader<MythicTierLore> createMythicTierLoreLoader(String fileName);

  /**
   * Gets all of the registered {@link MythicLoader}s for {@link MythicTierComponent}s.
   *
   * @return registered MythicLoaders for MythicTierComponents
   */
  Collection<MythicLoader<MythicTierComponent>> getMythicTierComponentLoaders();

  /**
   * Register a {@link MythicLoader} for a {@link MythicTierComponent}.
   *
   * @param loader MythicLoader for a MythicTierComponent
   * @return if registration was successful
   */
  boolean registerMythicTierComponentLoader(MythicLoader<MythicTierComponent> loader);

}