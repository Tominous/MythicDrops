package com.tealcubegames.minecraft.spigot.mythicdrops.api.tiers;

import org.immutables.value.Value;

/**
 * An interface that represents an additional component of a tier beyond the standard configuration.
 *
 * @author Richard Harrah
 */
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE)
@Value.Immutable
public abstract class MythicTierComponent {

    public static Builder builder() {
        return ImmutableMythicTierComponent.builder();
    }

    public interface Builder {
        MythicTierComponent build();
    }

}
