package dev.hybridlabs.aquatic.access;

public interface CustomPlayerEntityData {
    default void setHybridHurtTime(int value) {
        throw new AssertionError();
    }

    default int getHybridHurtTime() {
        throw new AssertionError();
    }
}
