package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers;

import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import org.apache.commons.lang3.StringUtils;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public abstract class MythicTierComponent {

  public static MythicTierComponent of(String id, MythicLoader<MythicTierComponent> loader) {
    return ImmutableMythicTierComponent.of(id, loader);
  }

  public static Builder builder() {
    return ImmutableMythicTierComponent.builder();
  }

  @Value.Parameter
  public abstract String id();

  @Value.Parameter
  public abstract MythicLoader<MythicTierComponent> loader();

  @Value.Check
  void check() {
    Preconditions.checkState(!StringUtils.isBlank(id()), "ID cannot be empty or null");
  }

  public interface Builder {
    Builder id(String id);

    Builder loader(MythicLoader<MythicTierComponent> loader);

    MythicTierComponent build();
  }

}
