package dev.hybridlabs.aquatic.access;

import dev.hybridlabs.aquatic.world.gen.biome.HABiomePlacement;

public interface PlacedBiomeSource {
    default void setBiomePlacement(HABiomePlacement.Placement value) {
        throw new AssertionError();
    }
}
