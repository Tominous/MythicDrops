package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import com.google.common.base.Preconditions;
import java.util.List;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public abstract class MythicTierLore {

  public static MythicTierLore of(List<String> baseLore, List<String> bonusLore,
      int minimumBonusLore, int maximumBonusLore) {
    return ImmutableMythicTierLore.of(baseLore, bonusLore, minimumBonusLore, maximumBonusLore);
  }

  public static Builder builder() {
    return ImmutableMythicTierLore.builder();
  }

  @Value.Parameter
  public abstract List<String> baseLore();

  @Value.Parameter
  public abstract List<String> bonusLore();

  @Value.Parameter
  public abstract int minimumBonusLore();

  @Value.Parameter
  public abstract int maximumBonusLore();

  @Value.Check
  void check() {
    Preconditions.checkState(minimumBonusLore() >= 0,
        "minimumBonusLore must be greater than or equal to 0");
    Preconditions.checkState(maximumBonusLore() >= 0,
        "maximumBonusLore must be greater than or equal to 0");
  }

  public interface Builder {
    Builder baseLore(Iterable<String> baseLore);

    Builder bonusLore(Iterable<String> bonusLore);

    Builder minimumBonusLore(int minimumBonusLore);

    Builder maximumBonusLore(int maximumBonusLore);

    MythicTierLore build();
  }

}

