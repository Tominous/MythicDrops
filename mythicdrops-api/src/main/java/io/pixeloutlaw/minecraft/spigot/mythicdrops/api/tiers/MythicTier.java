package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items.MythicItemType;
import java.util.Collection;
import org.bukkit.ChatColor;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public abstract class MythicTier {

  public static Builder builder() {
    return ImmutableMythicTier.builder();
  }

  public abstract String key();

  public abstract String displayName();

  public abstract ChatColor displayColor();

  public abstract ChatColor identifierColor();

  public abstract Collection<MythicItemType> itemTypes();

  public abstract boolean broadcastOnFind();

  public abstract boolean infiniteDurability();

  public abstract double minimumDurabilityPercentage();

  public abstract double maximumDurabilityPercentage();

  public abstract MythicTierEnchantments mythicTierEnchantments();

  public abstract MythicTierLore mythicTierLore();

  public abstract double chanceToSpawnOnAMonster();

  public abstract double chanceToDropOnMonsterDeath();

  public abstract Collection<MythicTierComponent> components();

  public interface Builder {
    Builder key(String key);

    Builder displayName(String displayName);

    Builder displayColor(ChatColor displayColor);

    Builder identifierColor(ChatColor identifierColor);

    Builder itemTypes(Collection<MythicItemType> itemTypes);

    Builder broadcastOnFind(boolean broadcastOnFind);

    Builder infiniteDurability(boolean infiniteDurability);

    Builder minimumDurabilityPercentage(double minimumDurabilityPercentage);

    Builder maximumDurabilityPercentage(double maximumDurabilityPercentage);

    Builder mythicTierEnchantments(MythicTierEnchantments mythicTierEnchantments);

    Builder mythicTierLore(MythicTierLore mythicTierLore);

    Builder chanceToSpawnOnAMonster(double chanceToSpawnOnAMonster);

    Builder chanceToDropOnMonsterDeath(double chanceToDropOnMonsterDeath);

    Builder components(Collection<MythicTierComponent> components);

    MythicTier build();
  }


}
