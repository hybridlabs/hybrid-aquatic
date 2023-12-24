package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.access.CustomPlayerEntityData;
import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects;
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements CustomPlayerEntityData {
    @Unique
    private int haHurtTime = 0;

    @Override
    public void hybrid_aquatic$setHurtTime(int value) {
        haHurtTime = value;
    }

    @Override
    public int hybrid_aquatic$getHurtTime() {
        return haHurtTime;
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        hybrid_aquatic$setHurtTime(nbt.getInt("haHurtTime"));
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("haHurtTime", hybrid_aquatic$getHurtTime());
    }

    // Sets haHurtTime to 200 if player got hurt near shark
    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;getWorld()Lnet/minecraft/world/World;",
                    ordinal = 0,
                    shift = At.Shift.BEFORE
            )
    )
    private void setCustomHurtTimeOnDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity object = ((PlayerEntity) (Object) this);

        if (object.isTouchingWater()) {
            LivingEntity foundEntity = object.getWorld().getClosestEntity(HybridAquaticSharkEntity.class, TargetPredicate.createNonAttackable().setBaseMaxDistance(32).setPredicate(Entity::isSubmergedInWater), object, object.getX(), object.getEyeY(), object.getZ(), object.getBoundingBox().expand(16));
            if (foundEntity != null) hybrid_aquatic$setHurtTime(200);
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickDownCustomHurtTime(CallbackInfo ci) {
        int cHurtTime = hybrid_aquatic$getHurtTime();
        if (cHurtTime > 0) {
            hybrid_aquatic$setHurtTime(cHurtTime - 1);
        }

        var player = (PlayerEntity)(Object)this;
        var world = player.getWorld();
        if(!world.isClient) {
            if (world.isRaining() &&
                    world.getBiome(player.getBlockPos()).isIn(BiomeTags.IS_OCEAN) &&
                    (player.getY() > world.getSeaLevel() && player.getY() < world.getSeaLevel() + 12)) {
                player.addStatusEffect(new StatusEffectInstance(HybridAquaticStatusEffects.INSTANCE.getTHALASSOPHOBIA(), 2, 1));
            }
        }
    }
}
