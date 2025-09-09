package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.platform.registration.RegistryObject;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;

public class FabricClientPlatformHelper implements ClientPlatformHelper {

    @Override
    public <E extends Entity> void registerEntityRenderer(RegistryObject<EntityType<E>> entityType,
                                                          EntityRendererProvider<E> entityRendererFactory) {
        EntityRendererRegistry.register(entityType.get(), entityRendererFactory);
    }

    @Override
    public void registerBlockRenderers(RenderType renderType, Block... blocks) {
        BlockRenderLayerMap registry = BlockRenderLayerMap.INSTANCE;
        Arrays.stream(blocks).forEach(block -> registry.putBlocks(renderType, blocks));
    }
}
