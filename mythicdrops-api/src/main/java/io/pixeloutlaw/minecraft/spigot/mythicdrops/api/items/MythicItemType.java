package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.values.Validated;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;

@AutoValue
public abstract class MythicItemType extends Validated {

  public static Builder builder() {
    return new AutoValue_MythicItemType.Builder();
  }

  public abstract String name();

  public abstract Collection<Material> materials();

  @Override
  protected void check() {
    Preconditions.checkState(!StringUtils.isBlank(name()), "Name cannot be empty or null");
    Preconditions.checkState(!materials().isEmpty(), "Materials cannot be empty");
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder name(String name);

    public abstract Builder materials(Collection<Material> materials);

    public MythicItemType build() {
      MythicItemType mythicItemType = autoBuild();
      mythicItemType.check();
      return mythicItemType;
    }

    abstract MythicItemType autoBuild();
  }

}
