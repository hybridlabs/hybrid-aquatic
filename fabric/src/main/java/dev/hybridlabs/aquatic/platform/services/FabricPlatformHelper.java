package dev.hybridlabs.aquatic.platform.services;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(@NotNull String name,
                                                                       Supplier<EntityType<T>> entityType,
                                                                       int backgroundColor, int highlightColor) {
        return CommonClass.ITEMS.register(name, () -> new SpawnEggItem(entityType.get(), backgroundColor,
                highlightColor, new Item.Properties()));
    }

    @Override
    public Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public <T extends Mob> void registerSpawnPlacement(RegistryObject<EntityType<T>> entityType,
                                                       SpawnPlacements.Type decoratorType,
                                                       Heightmap.Types heightMapType,
                                                       SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        SpawnPlacements.register(entityType.get(), decoratorType, heightMapType, decoratorPredicate);
    }

    @Override
    public <T extends LivingEntity> void registerAttributes(@NotNull String id, EntityType<T> entityType,
                                                            Callable<AttributeSupplier.Builder> attributeContainer) {
        try {
            FabricDefaultAttributeRegistry.register(entityType, attributeContainer.call());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Attribute getReachAttribute() {
        return ReachEntityAttributes.REACH;
    }

    public BlockBehaviour.Properties getBlockSettings() {
        return BlockBehaviour.Properties.of();
    }
}
