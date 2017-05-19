package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 * Represents the base class for MythicDrops configuration objects.
 */
@ConfigSerializable
public class Config {

  @Setting("version")
  private String version;

  public String getVersion() {
    return version;
  }

}
