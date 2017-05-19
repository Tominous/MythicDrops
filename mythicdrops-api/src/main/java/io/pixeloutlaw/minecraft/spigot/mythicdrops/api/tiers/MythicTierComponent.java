package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.values.Validated;
import org.apache.commons.lang3.StringUtils;

@AutoValue
public abstract class MythicTierComponent extends Validated {

  public static Builder builder() {
    return new AutoValue_MythicTierComponent.Builder();
  }

  public abstract String id();

  public abstract MythicLoader<MythicTierComponent> loader();

  @Override
  protected void check() {
    Preconditions.checkState(!StringUtils.isBlank(id()), "ID cannot be empty or null");
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder id(String id);

    public abstract Builder loader(MythicLoader<MythicTierComponent> loader);

    public MythicTierComponent build() {
      MythicTierComponent mythicTierComponent = autoBuild();
      mythicTierComponent.check();
      return mythicTierComponent;
    }

    abstract MythicTierComponent autoBuild();

  }

}
