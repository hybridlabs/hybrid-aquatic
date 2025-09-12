package dev.hybridlabs.aquatic.platform.services;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks;
import dev.hybridlabs.aquatic.item.*;
import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import dev.hybridlabs.aquatic.utils.HybridAquaticSpawnGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.*;
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
    public @Nullable MobCategory getMobCategoryByName(String name) {
        return HybridAquaticSpawnGroup.byName(name.toLowerCase());
    }

    @Override
    public ArmorItem createArmor(ArmorMaterial material, ArmorItem.Type type, Item.Properties settings) {
        if (material.equals(HybridAquaticArmorMaterials.DIVING)) {
            return new DivingArmorItem(material, type, settings);
        } else if (material.equals(HybridAquaticArmorMaterials.SEASHELL)) {
            return new SeashellArmorItem(material, type, settings);
        } else if (material.equals(HybridAquaticArmorMaterials.MANGLERFISH)) {
            return new ManglerfishArmorItem(material, type, settings);
        } else if (material.equals(HybridAquaticArmorMaterials.EEL)) {
            return new EelArmorItem(material, type, settings);
        } else if (material.equals(HybridAquaticArmorMaterials.TURTLE)) {
            return new TurtleArmorItem(material, type, settings);
        } else if (material.equals(HybridAquaticArmorMaterials.MOONJELLYFISH)) {
            return new MoonJellyfishArmorItem(material, type, settings);
        }
        return new ArmorItem(material, type, settings);
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

}
