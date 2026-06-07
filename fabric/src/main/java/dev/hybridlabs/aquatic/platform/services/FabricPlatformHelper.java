package dev.hybridlabs.aquatic.platform.services;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.aquatic.block.HABlocks;
import dev.hybridlabs.aquatic.item.AnemoneBlockItem;
import dev.hybridlabs.aquatic.item.GiantGreenAnemoneBlockItem;
import dev.hybridlabs.aquatic.item.StrawberryAnemoneBlockItem;
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking;
import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import dev.hybridlabs.aquatic.utils.HASpawnGroup;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Override
    public @Nullable MobCategory getHybridMobCategoryByName(String name) {
        return HASpawnGroup.byName(name);
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
        return new dev.hybridlabs.aquatic.item.MessageInABottleItem(properties);
    }

    @Override
    public void sendHookToServer(int entityId, ItemStack entityData) {
        FriendlyByteBuf packetData = PacketByteBufs.create();
        packetData.writeInt(entityId);
        ResourceLocation packetId = HybridAquaticNetworking.INSTANCE.getFISHING_BOBBER_LURE();
        if (ClientPlayNetworking.canSend(packetId))
            ClientPlayNetworking.send(packetId, packetData);
    }

}
