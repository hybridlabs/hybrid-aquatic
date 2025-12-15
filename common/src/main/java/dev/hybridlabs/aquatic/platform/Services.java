package dev.hybridlabs.aquatic.platform;

import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.platform.services.DivingArmorProvider;
import dev.hybridlabs.aquatic.platform.services.EelArmorProvider;
import dev.hybridlabs.aquatic.platform.services.ManglerfishArmorProvider;
import dev.hybridlabs.aquatic.platform.services.MoonJellyfishArmorProvider;
import dev.hybridlabs.aquatic.platform.services.PlatformHelper;
import dev.hybridlabs.aquatic.platform.services.SeashellArmorProvider;
import dev.hybridlabs.aquatic.platform.services.TurtleArmorProvider;

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
    public static final DivingArmorProvider DIVING_ARMOR_FACTORY = load(DivingArmorProvider.class);
    public static final SeashellArmorProvider SEASHELL_ARMOR_FACTORY = load(SeashellArmorProvider.class);
    public static final ManglerfishArmorProvider MANGLERFISH_ARMOR_FACTORY = load(ManglerfishArmorProvider.class);
    public static final MoonJellyfishArmorProvider MOON_JELLYFISH_ARMOR_FACTORY =
            load(MoonJellyfishArmorProvider.class);
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
