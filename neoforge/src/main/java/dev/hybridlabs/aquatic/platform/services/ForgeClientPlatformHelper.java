package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ForgeClientPlatformHelper implements ClientPlatformHelper {

    @Override
    public <E extends Entity> void registerEntityRenderer(RegistryObject<EntityType<E>> entityType,
                                                          EntityRendererProvider<E> entityRendererFactory) {

        var handler = new ForgeClientPlatformHelper.RendererRegistrationHandler<E>(entityType, entityRendererFactory);
        ForgePlatformHelper.getEventBus().addListener(handler::handleEvent);
    }

    private record RendererRegistrationHandler<T extends Entity>(RegistryObject<EntityType<T>> type,
                                                                 EntityRendererProvider<T> rendererProvider) {
        private void handleEvent(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(type.get(), rendererProvider);
        }
    }
}
