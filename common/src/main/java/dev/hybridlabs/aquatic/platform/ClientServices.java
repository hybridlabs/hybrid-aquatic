package dev.hybridlabs.aquatic.platform;

import dev.hybridlabs.aquatic.platform.services.ClientPlatformHelper;

import static dev.hybridlabs.aquatic.platform.Services.load;

public class ClientServices {
    public static final ClientPlatformHelper PLATFORM = load(ClientPlatformHelper.class);
}
