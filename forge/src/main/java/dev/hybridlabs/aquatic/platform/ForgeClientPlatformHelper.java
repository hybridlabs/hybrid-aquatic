package dev.hybridlabs.aquatic.platform;


import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import dev.hybridlabs.aquatic. platform.services.ClientPlatformHelper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ForgeClientPlatformHelper implements ClientPlatformHelper {

    @Override
    @OnlyIn(Dist.CLIENT)
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
