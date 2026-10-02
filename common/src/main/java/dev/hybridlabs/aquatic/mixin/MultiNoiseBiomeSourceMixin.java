package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.hybridlabs.aquatic.access.PlacedBiomeSource;
import dev.hybridlabs.aquatic.world.gen.biome.HABiomePlacement;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MultiNoiseBiomeSource.class)
public abstract class MultiNoiseBiomeSourceMixin implements PlacedBiomeSource {
    @Unique
    private HABiomePlacement.Placement hybridAquatic$biomePlacement;

    // Mods such as TerraBlender and Alex's Caves place their biomes by returning from the head of this method, which a
    // return injection never sees. Wrapping the whole method applies the placement to their result as well
    @WrapMethod(method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;")
    private Holder<Biome> placeBiomes(int x, int y, int z, Climate.Sampler sampler, Operation<Holder<Biome>> original) {
        Holder<Biome> biome = original.call(x, y, z, sampler);
        return hybridAquatic$biomePlacement == null ? biome : hybridAquatic$biomePlacement.place(biome, x, y, z, sampler);
    }

    @Override
    public void setBiomePlacement(HABiomePlacement.Placement value) {
        hybridAquatic$biomePlacement = value;
    }
}
