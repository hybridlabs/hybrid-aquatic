package dev.hybridlabs.aquatic.platform;

import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.platform.services.*;

import java.util.ServiceLoader;

// Service loaders are a built-in Java feature that allow us to locate implementations of an
// interface that vary from one environment to another. In the context of MultiLoader we use this
// feature to access a mock API in the common code that  is swapped out for the platform specific
// implementation at runtime.
public class Services {

    // In this example we provide a platform helper which provides information about what platform
    // the mod is running on.  For example this can be used to check if the code is running on Forge
    // vs Fabric, or to ask the modloader if another mod is loaded.
    public static final PlatformHelper PLATFORM = load(PlatformHelper.class);


    public static final EelArmorProvider EEL_ARMOR_FACTORY = load(EelArmorProvider.class);
    public static final StripedEelArmorProvider STRIPED_EEL_ARMOR_FACTORY = load(StripedEelArmorProvider.class);
    public static final PinkHatxolotlArmorProvider PINK_HATXOLOTL_ARMOR_FACTORY = load(PinkHatxolotlArmorProvider.class);
    public static final BrownHatxolotlArmorProvider BROWN_HATXOLOTL_ARMOR_FACTORY = load(BrownHatxolotlArmorProvider.class);
    public static final GoldHatxolotlArmorProvider GOLD_HATXOLOTL_ARMOR_FACTORY = load(GoldHatxolotlArmorProvider.class);
    public static final BlueHatxolotlArmorProvider BLUE_HATXOLOTL_ARMOR_FACTORY = load(BlueHatxolotlArmorProvider.class);
    public static final CyanHatxolotlArmorProvider CYAN_HATXOLOTL_ARMOR_FACTORY = load(CyanHatxolotlArmorProvider.class);
    public static final DivingArmorProvider DIVING_ARMOR_FACTORY = load(DivingArmorProvider.class);
    public static final ReinforcedDivingArmorProvider REINFORCED_DIVING_ARMOR_FACTORY = load(ReinforcedDivingArmorProvider.class);
    public static final GlowingDivingArmorProvider GLOWING_DIVING_ARMOR_FACTORY = load(GlowingDivingArmorProvider.class);
    public static final SeashellArmorProvider SEASHELL_ARMOR_FACTORY = load(SeashellArmorProvider.class);
    public static final ManglerfishCosmeticProvider MANGLERFISH_COSMETIC_PROVIDER = load(ManglerfishCosmeticProvider.class);
    public static final MoonJellyfishArmorProvider MOON_JELLYFISH_ARMOR_FACTORY = load(MoonJellyfishArmorProvider.class);
    public static final TurtleArmorProvider TURTLE_ARMOR_FACTORY = load(TurtleArmorProvider.class);

    // This code is used to load a service for the current environment. Your implementation of the
    // service must be defined  manually by including a text file in META-INF/services named with
    // the fully qualified class  name of the service.  Inside the file you should write the fully
    // qualified class name of the implementation to load  for the platform. For  example our file
    // on Forge points to ForgePlatformHelper while Fabric points to  FabricPlatformHelper.
    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException(
                "Failed to load service for " + clazz.getName()));
        Constants.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
