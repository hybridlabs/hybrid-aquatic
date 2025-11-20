package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public interface PlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(@NotNull String name,
                                                                Supplier<EntityType<T>> entityType,
                                                                int backgroundColor, int highlightColor);

    Path getConfigDir();

    <T extends Mob> void registerSpawnPlacement(RegistryObject<EntityType<T>> entityType,
                                                SpawnPlacements.Type decoratorType, Heightmap.Types heightMapType,
                                                SpawnPlacements.SpawnPredicate<T> decoratorPredicate);

    <T extends LivingEntity> void registerAttributes(@NotNull String id, EntityType<T> entityType,
                                                     Callable<AttributeSupplier.Builder> attributeContainer);

    Attribute getReachAttribute();

    MobCategory getMobCategoryByName(String name);

    Item createBlockItem(Block block, Item.Properties properties);

    Item createMessageInABottleItem(Item.Properties properties);

    void sendHookToServer(int entityId, ItemStack entityData);
}
