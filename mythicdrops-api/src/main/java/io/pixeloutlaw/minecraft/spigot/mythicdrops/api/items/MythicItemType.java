package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items;

import com.google.common.base.Preconditions;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public abstract class MythicItemType {

  public static MythicItemType of(String name, Collection<Material> materials) {
    return ImmutableMythicItemType.of(name, materials);
  }

  public static Builder builder() {
    return ImmutableMythicItemType.builder();
  }

  @Value.Parameter
  public abstract String name();

  @Value.Parameter
  public abstract Collection<Material> materials();

  @Value.Check
  void check() {
    Preconditions.checkState(!StringUtils.isBlank(name()), "Name cannot be empty or null");
    Preconditions.checkState(!materials().isEmpty(), "Materials cannot be empty");
  }

  public interface Builder {
    Builder name(String name);

    Builder materials(Collection<Material> materials);

    MythicItemType build();
  }

}
