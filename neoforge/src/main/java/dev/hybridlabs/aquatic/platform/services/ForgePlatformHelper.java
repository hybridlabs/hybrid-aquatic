package dev.hybridlabs.aquatic.platform.services;


import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks;
import dev.hybridlabs.aquatic.item.AnemoneBlockItem;
import dev.hybridlabs.aquatic.item.GiantGreenAnemoneBlockItem;
import dev.hybridlabs.aquatic.item.MessageInABottleItem;
import dev.hybridlabs.aquatic.item.StrawberryAnemoneBlockItem;
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking;
import dev.hybridlabs.aquatic.platform.registration.RegistryObject;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ForgePlatformHelper implements PlatformHelper {

    public static IEventBus getEventBus() {
        final ModContainer cont =
                ModList.get().getModContainerById(Constants.FORGE_MOD_ID).orElseThrow();
        return cont.getEventBus();
    }

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(
            @NotNull String name,
            EntityType<T> entityType,
            int backgroundColor,
            int highlightColor) {
        return CommonClass.ITEMS.register(
                name,
                () ->
                        new SpawnEggItem(
                                entityType,
                                backgroundColor,
                                highlightColor,
                                new Item.Properties()));
    }

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public <T extends Mob> void registerSpawnPlacement(
            RegistryObject<EntityType<T>> entityType,
            SpawnPlacementType decoratorType,
            Heightmap.Types heightMapType,
            SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {

        var handler =
                new SpawnPlacementRegistrationHandler<T>(
                        entityType, decoratorType, heightMapType, decoratorPredicate);
        ForgePlatformHelper.getEventBus().addListener(handler::handleEvent);
    }

    @Override
    public <T extends LivingEntity> void registerAttributes(
            @NotNull String id,
            EntityType<T> entityType,
            Callable<AttributeSupplier.Builder> attributeContainer) {
        var handler = new AttributeRegistrationHandler(id, entityType, attributeContainer);
        ForgePlatformHelper.getEventBus().addListener(handler::handleEvent);
    }

    @Override
    public MobCategory getMobCategoryByName(String name) {
        return MobCategory.valueOf(name.toLowerCase());
    }

    @Override
    public Item createBlockItem(Block block, Item.Properties properties) {
        if (block.equals(HybridAquaticBlocks.INSTANCE.getANEMONE().get())) {
            return new AnemoneBlockItem(block, properties);
        } else if (block.equals(HybridAquaticBlocks.INSTANCE.getSTRAWBERRY_ANEMONE().get())) {
            return new StrawberryAnemoneBlockItem(block, properties);
        } else if (block.equals(HybridAquaticBlocks.INSTANCE.getGIANT_GREEN_ANEMONE().get())) {
            return new GiantGreenAnemoneBlockItem(block, properties);
        }
        return new BlockItem(block, properties);
    }

    @Override
    public Item createMessageInABottleItem(Item.Properties properties) {
        return new MessageInABottleItem(properties);
    }

    @Override
    public void sendHookToServer(int entityId, ItemStack entityData) {
        HybridAquaticNetworking.INSTANCE.sendHookPacket(entityId, entityData);
    }

    private record SpawnPlacementRegistrationHandler<T extends LivingEntity>(
            RegistryObject<EntityType<T>> type,
            SpawnPlacementType decoratorType,
            Heightmap.Types heightMapType,
            SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {

        private void handleEvent(RegisterSpawnPlacementsEvent event) {
            event.register(
                    type.get(),
                    decoratorType,
                    heightMapType,
                    decoratorPredicate,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE);
        }
    }

    private record AttributeRegistrationHandler(
            String id,
            EntityType<? extends LivingEntity> type,
            Callable<AttributeSupplier.Builder> supplier) {

        private void handleEvent(EntityAttributeCreationEvent event) {
            try {
                event.put(type, supplier.call().build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
