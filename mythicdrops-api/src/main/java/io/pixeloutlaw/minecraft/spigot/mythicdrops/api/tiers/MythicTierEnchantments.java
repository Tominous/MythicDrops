package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items.MythicEnchantment;
import java.util.Collection;

@AutoValue
public abstract class MythicTierEnchantments {

  public static Builder builder() {
    return new AutoValue_MythicTierEnchantments.Builder();
  }

  public abstract Collection<MythicEnchantment> baseEnchantments();

  public abstract Collection<MythicEnchantment> bonusEnchantments();

  public abstract int minimumBonusEnchantments();

  public abstract int maximumBonusEnchantments();

  void check() {
    Preconditions.checkState(minimumBonusEnchantments() >= 0,
        "minimumBonusEnchantments must be greater than or equal to 0");
    Preconditions.checkState(maximumBonusEnchantments() >= 0,
        "maximumBonusEnchantments must be greater than or equal to 0");
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder baseEnchantments(Collection<MythicEnchantment> baseEnchantments);

    public abstract Builder bonusEnchantments(Collection<MythicEnchantment> bonusEnchantments);

    public abstract Builder minimumBonusEnchantments(int minimumBonusEnchantments);

    public abstract Builder maximumBonusEnchantments(int maximumBonusEnchantments);

    public MythicTierEnchantments build() {
      MythicTierEnchantments mythicTierEnchantments = autoBuild();
      mythicTierEnchantments.check();
      return mythicTierEnchantments;
    }

    abstract MythicTierEnchantments autoBuild();
  }

}
