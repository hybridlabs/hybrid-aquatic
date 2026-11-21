package dev.hybridlabs.aquatic.platform.services;


import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.block.HABlocks;
import dev.hybridlabs.aquatic.item.AnemoneBlockItem;
import dev.hybridlabs.aquatic.item.GiantGreenAnemoneBlockItem;
import dev.hybridlabs.aquatic.item.StrawberryAnemoneBlockItem;
import dev.hybridlabs.aquatic.network.HybridAquaticNetworkingForge;
import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.Locale;

public class ForgePlatformHelper implements PlatformHelper {

    private static final Map<BlockEntityType<?>, List<Block>> PENDING_VALID_BLOCKS = new HashMap<>();
    private static boolean validBlocksListenerRegistered;

    public static IEventBus getEventBus() {
        final ModContainer cont =
                ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
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

        return !FMLEnvironment.isProduction();
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(
            @NotNull String name,
            Supplier<EntityType<T>> entityType,
            int backgroundColor,
            int highlightColor) {
        return CommonClass.ITEMS.register(
                name,
                () -> new SpawnEggItem(new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, CommonClass.locate(name)))
                        .spawnEgg(entityType.get())));
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
    public Attribute getReachAttribute() {
        return Attributes.BLOCK_INTERACTION_RANGE.value();
    }

    @Override
    public MobCategory getHybridMobCategoryByName(String name) {
        return MobCategory.valueOf((Constants.MOD_ID + '_' + name).toUpperCase(Locale.ENGLISH));
    }

    @Override
    public Item createBlockItem(Block block, Item.Properties properties) {
        if (block.equals(HABlocks.INSTANCE.getANEMONE().get())) {
            return new AnemoneBlockItem(block, properties);
        } else if (block.equals(HABlocks.INSTANCE.getSTRAWBERRY_ANEMONE().get())) {
            return new StrawberryAnemoneBlockItem(block, properties);
        } else if (block.equals(HABlocks.INSTANCE.getGIANT_GREEN_ANEMONE().get())) {
            return new GiantGreenAnemoneBlockItem(block, properties);
        }
        return new BlockItem(block, properties);
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(
            BlockEntityType.BlockEntitySupplier<? extends T> factory, Block... blocks) {
        return new BlockEntityType<>(factory, java.util.Set.of(blocks));
    }

    @Override
    public <T extends BlockEntity> void addBlockEntityTypeBlocks(BlockEntityType<T> type, Block... blocks) {
        registerValidBlocksListener();
        PENDING_VALID_BLOCKS.computeIfAbsent(type, blockEntityType -> new ArrayList<>())
                .addAll(Arrays.asList(blocks));
    }

    private static void registerValidBlocksListener() {
        if (validBlocksListenerRegistered) {
            return;
        }
        validBlocksListenerRegistered = true;
        getEventBus().addListener((BlockEntityTypeAddBlocksEvent event) -> {
            PENDING_VALID_BLOCKS.forEach(
                    (type, blocks) -> event.modify(type, blocks.toArray(new Block[0])));
            PENDING_VALID_BLOCKS.clear();
        });
    }

    @Override
    public void sendHookToServer(int entityId, ItemStack entityData) {
        HybridAquaticNetworkingForge.INSTANCE.sendHookPacket(entityId, entityData);
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
