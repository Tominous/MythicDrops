package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items.MythicEnchantment;
import java.util.Collection;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public abstract class MythicTierEnchantments {

  public static MythicTierEnchantments of(Collection<MythicEnchantment> baseEnchantments,
      Collection<MythicEnchantment> bonusEnchantments,
      int minimumBonusEnchantments,
      int maximumBonusEnchantments) {
    return ImmutableMythicTierEnchantments.of(baseEnchantments, bonusEnchantments, minimumBonusEnchantments,
        maximumBonusEnchantments);
  }

  public static Builder builder() {
    return ImmutableMythicTierEnchantments.builder();
  }

  @Value.Parameter
  public abstract Collection<MythicEnchantment> baseEnchantments();

  @Value.Parameter
  public abstract Collection<MythicEnchantment> bonusEnchantments();

  @Value.Parameter
  public abstract int minimumBonusEnchantments();

  @Value.Parameter
  public abstract int maximumBonusEnchantments();

  @Value.Check
  void check() {
    Preconditions.checkState(minimumBonusEnchantments() >= 0,
        "minimumBonusEnchantments must be greater than or equal to 0");
    Preconditions.checkState(maximumBonusEnchantments() >= 0,
        "maximumBonusEnchantments must be greater than or equal to 0");
  }

  public interface Builder {
    Builder baseEnchantments(Collection<MythicEnchantment> baseEnchantments);

    Builder bonusEnchantments(Collection<MythicEnchantment> bonusEnchantments);

    Builder minimumBonusEnchantments(int minimumBonusEnchantments);

    Builder maximumBonusEnchantments(int maximumBonusEnchantments);

    MythicTierEnchantments build();
  }

}
