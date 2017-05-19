package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.values.Validated;
import java.util.List;

@AutoValue
public abstract class MythicTierLore extends Validated {

  public static Builder builder() {
    return AutoValue_MythicTierLore.builder();
  }

  public abstract List<String> baseLore();

  public abstract List<String> bonusLore();

  public abstract int minimumBonusLore();

  public abstract int maximumBonusLore();

  @Override
  protected void check() {
    Preconditions.checkState(minimumBonusLore() >= 0,
        "minimumBonusLore must be greater than or equal to 0");
    Preconditions.checkState(maximumBonusLore() >= 0,
        "maximumBonusLore must be greater than or equal to 0");
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder baseLore(List<String> baseLore);

    public abstract Builder bonusLore(List<String> bonusLore);

    public abstract Builder minimumBonusLore(int minimumBonusLore);

    public abstract Builder maximumBonusLore(int maximumBonusLore);

    public MythicTierLore build() {
      MythicTierLore mythicTierLore = autoBuild();
      mythicTierLore.check();
      return mythicTierLore;
    }

    abstract MythicTierLore autoBuild();
  }

}

