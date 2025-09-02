package dev.hybridlabs.aquatic.access;

public interface CustomPlayerEntityData {
    default void hybrid_aquatic$setHurtTime(int value) {
        throw new AssertionError();
    }

    default int hybrid_aquatic$getHurtTime() {
        throw new AssertionError();
    }
}
