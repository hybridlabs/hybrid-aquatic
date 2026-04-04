package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.block.HABlocks;
import dev.hybridlabs.aquatic.item.AnemoneBlockItem;
import dev.hybridlabs.aquatic.item.GiantGreenAnemoneBlockItem;
import dev.hybridlabs.aquatic.item.MessageInABottleItem;
import dev.hybridlabs.aquatic.item.StrawberryAnemoneBlockItem;
import dev.hybridlabs.aquatic.network.HANetworking;
import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;
import thedarkcolour.kotlinforforge.KotlinModContainer;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ForgePlatformHelper implements PlatformHelper {

    public static IEventBus getEventBus() {
        final ModContainer cont =
                ModList.get().getModContainerById(Constants.FORGE_MOD_ID).orElseThrow();
        if (cont instanceof FMLModContainer fmlModContainer) {
            return fmlModContainer.getEventBus();
        } else if (cont instanceof KotlinModContainer kotlinModContainer) {
            return kotlinModContainer.getEventBus$kfflang();
        } else {
            throw new ClassCastException(
                    "The container of the mod " + Constants.FORGE_MOD_ID + " is not a FML one!");
        }
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
            Supplier<EntityType<T>> entityType,
            int backgroundColor,
            int highlightColor) {
        return CommonClass.ITEMS.register(
                name,
                () ->
                        new ForgeSpawnEggItem(
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
            SpawnPlacements.Type decoratorType,
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
        return ForgeMod.BLOCK_REACH.get();
    }

    private record SpawnPlacementRegistrationHandler<T extends LivingEntity>(
            RegistryObject<EntityType<T>> type,
            SpawnPlacements.Type decoratorType,
            Heightmap.Types heightMapType,
            SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {

        private void handleEvent(SpawnPlacementRegisterEvent event) {
            event.register(
                    type.get(),
                    decoratorType,
                    heightMapType,
                    decoratorPredicate,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
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

    @Override
    public MobCategory getMobCategoryByName(String name) {
        return MobCategory.valueOf(name);
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
    public Item createMessageInABottleItem(Item.Properties properties) {
        return new MessageInABottleItem(properties);
    }

    @Override
    public void sendHookToServer(int entityId, ItemStack entityData) {
        HANetworking.INSTANCE.sendHookPacket(entityId, entityData);
    }
}
