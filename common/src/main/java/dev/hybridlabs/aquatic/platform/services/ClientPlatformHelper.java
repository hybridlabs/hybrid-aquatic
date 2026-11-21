package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public interface ClientPlatformHelper {
    <E extends Entity> void registerEntityRenderer(
            RegistryObject<EntityType<E>> entityType,
            EntityRendererProvider<E> entityRendererFactory);

    void registerBlockRenderers(ChunkSectionLayer layer, Block... blocks);
}
