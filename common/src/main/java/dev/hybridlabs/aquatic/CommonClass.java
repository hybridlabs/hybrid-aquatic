package dev.hybridlabs.aquatic;

import dev.hybridlabs.aquatic.platform.Services;
import dev.hybridlabs.aquatic.platform.registration.RegistrationProvider;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;

import java.nio.file.Path;

import static dev.hybridlabs.aquatic.Constants.MOD_ID;
import static dev.hybridlabs.aquatic.Constants.MOD_NAME;

// This class is part of the common project meaning it is shared between all supported loaders. Code
//  written here can only  import and access the vanilla codebase, libraries used by vanilla, and
// optionally third party  libraries that provide  common compatible binaries. This means common
// code can not directly use loader specific concepts  such as Forge events however it will be
// compatible with all supported mod loaders.
public class CommonClass {

    // Create some static RegistrationProviders for reuse elsewhere.
    public static final RegistrationProvider<Block> BLOCKS =
            RegistrationProvider.get(BuiltInRegistries.BLOCK, MOD_ID);
    public static final RegistrationProvider<Item> ITEMS =
            RegistrationProvider.get(BuiltInRegistries.ITEM, MOD_ID);
    public static final RegistrationProvider<Instrument> INSTRUMENTS =
            RegistrationProvider.get(BuiltInRegistries.INSTRUMENT, MOD_ID);
    public static final RegistrationProvider<SoundEvent> SOUND_EVENTS =
            RegistrationProvider.get(BuiltInRegistries.SOUND_EVENT, MOD_ID);
    public static final RegistrationProvider<Fluid> FLUIDS =
            RegistrationProvider.get(BuiltInRegistries.FLUID, MOD_ID);
    //public static final RegistrationProvider<PaintingVariant> PAINTINGS =
    //        RegistrationProvider.get(BuiltInRegistries.PAINTING_VARIANT, MOD_ID);
    public static final RegistrationProvider<Potion> POTIONS =
            RegistrationProvider.get(BuiltInRegistries.POTION, MOD_ID);
    public static final RegistrationProvider<MobEffect> MOB_EFFECTS =
            RegistrationProvider.get(BuiltInRegistries.MOB_EFFECT, MOD_ID);
    public static final RegistrationProvider<MenuType<?>> MENU =
            RegistrationProvider.get(BuiltInRegistries.MENU, MOD_ID);
    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES =
            RegistrationProvider.get(BuiltInRegistries.ENTITY_TYPE, MOD_ID);
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            RegistrationProvider.get(BuiltInRegistries.BLOCK_ENTITY_TYPE, MOD_ID);
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS =
            RegistrationProvider.get(BuiltInRegistries.CREATIVE_MODE_TAB, MOD_ID);
    public static final RegistrationProvider<Feature<?>> FEATURE =
            RegistrationProvider.get(BuiltInRegistries.FEATURE, MOD_ID);
    public static final RegistrationProvider<LootPoolEntryType> LOOT_POOL_ENTRY_TYPE =
            RegistrationProvider.get(BuiltInRegistries.LOOT_POOL_ENTRY_TYPE, MOD_ID);
	public static final RegistrationProvider<ParticleType<?>> PARTICLE_TYPE =
			RegistrationProvider.get(BuiltInRegistries.PARTICLE_TYPE, MOD_ID);
	public static final RegistrationProvider<MenuType<?>> MENU_TYPE =
			RegistrationProvider.get(BuiltInRegistries.MENU, MOD_ID);
	
    public static final Path CONFIG_FILE = Services.PLATFORM.getConfigDir().resolve(MOD_ID + ".json");

    public static ResourceLocation locate(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        if (Services.PLATFORM.isModLoaded(MOD_ID)) {
            Constants.LOGGER.info("{} loaded.", MOD_NAME);
        }
    }
}
