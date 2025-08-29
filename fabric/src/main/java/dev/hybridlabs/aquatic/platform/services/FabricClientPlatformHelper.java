package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class FabricClientPlatformHelper implements ClientPlatformHelper {

    @Override
    public <E extends Entity> void registerEntityRenderer(RegistryObject<EntityType<E>> entityType,
                                                          EntityRendererProvider<E> entityRendererFactory) {
        EntityRendererRegistry.register(entityType.get(), entityRendererFactory);
    }
}
