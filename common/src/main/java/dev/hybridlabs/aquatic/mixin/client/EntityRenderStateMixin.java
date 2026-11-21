package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.client.render.ArgonautPassengerRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityRenderState.class)
public abstract class EntityRenderStateMixin implements ArgonautPassengerRenderState {

    @Unique
    private float hybridAquatic$argonautPitch = Float.NaN;

    @Override
    public float getArgonautPitch() {
        return this.hybridAquatic$argonautPitch;
    }

    @Override
    public void setArgonautPitch(float pitch) {
        this.hybridAquatic$argonautPitch = pitch;
    }
}
