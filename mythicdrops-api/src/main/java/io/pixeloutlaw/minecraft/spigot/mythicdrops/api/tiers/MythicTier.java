package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import com.google.auto.value.AutoValue;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items.MythicItemType;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.values.Validated;
import java.util.Collection;
import org.bukkit.ChatColor;

@AutoValue
public abstract class MythicTier extends Validated {

  public static Builder builder() {
    return new AutoValue_MythicTier.Builder();
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

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder key(String key);

    public abstract Builder displayName(String displayName);

    public abstract Builder displayColor(ChatColor displayColor);

    public abstract Builder identifierColor(ChatColor identifierColor);

    public abstract Builder itemTypes(Collection<MythicItemType> itemTypes);

    public abstract Builder broadcastOnFind(boolean broadcastOnFind);

    public abstract Builder infiniteDurability(boolean infiniteDurability);

    public abstract Builder minimumDurabilityPercentage(double minimumDurabilityPercentage);

    public abstract Builder maximumDurabilityPercentage(double maximumDurabilityPercentage);

    public abstract Builder mythicTierEnchantments(MythicTierEnchantments mythicTierEnchantments);

    public abstract Builder mythicTierLore(MythicTierLore mythicTierLore);

    public abstract Builder chanceToSpawnOnAMonster(double chanceToSpawnOnAMonster);

    public abstract Builder chanceToDropOnMonsterDeath(double chanceToDropOnMonsterDeath);

    public abstract Builder components(Collection<MythicTierComponent> components);

    public MythicTier build() {
      MythicTier mythicTier = autoBuild();
      mythicTier.check();
      return mythicTier;
    }

    abstract MythicTier autoBuild();
  }


}
