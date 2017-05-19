package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items;

import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import org.bukkit.enchantments.Enchantment;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public abstract class MythicEnchantment {

  private static final Range<Integer> ACCEPTABLE_ENCHANTMENT_RANGE = Range.closed(1, 127);

  public static MythicEnchantment of(Enchantment enchantment, int minimumLevel, int maximumLevel) {
    return ImmutableMythicEnchantment.of(enchantment, minimumLevel, maximumLevel);
  }

  public static Builder builder() {
    return ImmutableMythicEnchantment.builder();
  }

  @Value.Parameter
  public abstract Enchantment enchantment();

  @Value.Parameter
  public abstract int minimumLevel();

  @Value.Parameter
  public abstract int maximumLevel();

  @Value.Check
  void check() {
    Preconditions.checkState(enchantment() != null, "Enchantment cannot be null");
    Preconditions.checkState(ACCEPTABLE_ENCHANTMENT_RANGE.contains(minimumLevel()),
        "minimumLevel must be between " + ACCEPTABLE_ENCHANTMENT_RANGE.toString());
    Preconditions.checkState(ACCEPTABLE_ENCHANTMENT_RANGE.contains(maximumLevel()),
        "maximumLevel must be between " + ACCEPTABLE_ENCHANTMENT_RANGE.toString());
    Preconditions.checkState(minimumLevel() <= maximumLevel(),
        "minimumLevel must be less than or equal to maximumLevel");
    Preconditions.checkState(maximumLevel() >= minimumLevel(),
        "maximumLevel must be greater than or equal to minimumLevel");
  }

  public interface Builder {
    Builder enchantment(Enchantment enchantment);

    Builder minimumLevel(int minimumLevel);

    Builder maximumLevel(int maximumLevel);

    MythicEnchantment build();
  }

}
