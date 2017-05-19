package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import javax.inject.Inject;

/**
 * Represents the startup configuration file.
 */
@ConfigSerializable
public class StartupConfig extends Config {

  @Setting("debug")
  private boolean debugEnabled;

  @Inject
  public StartupConfig() {
    // don't need to do anything here
  }

  public boolean isDebugEnabled() {
    return debugEnabled;
  }

}
