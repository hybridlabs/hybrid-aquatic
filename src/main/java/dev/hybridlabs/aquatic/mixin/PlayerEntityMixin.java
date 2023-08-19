package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.HybridAquatic;
import dev.hybridlabs.aquatic.access.CustomPlayerEntityData;
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements CustomPlayerEntityData {
  
  @Unique
  private static final TrackedData<Integer> haHurtTime = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
  
  @Inject(method = "initDataTracker",
    at = @At("TAIL"))
  private void initDataTracker(CallbackInfo ci) {
    ((PlayerEntity) (Object) this).getDataTracker().startTracking(haHurtTime, 0);
  }
  
  @Override
  public void hybrid_aquatic$setHurtTime(int value) {
    ((PlayerEntity) (Object) this).getDataTracker().set(haHurtTime, value);
  }
  
  @Override
  public int hybrid_aquatic$getHurtTime() {
    return ((PlayerEntity) (Object) this).getDataTracker().get(haHurtTime);
  }
  
  @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
  private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
    hybrid_aquatic$setHurtTime(nbt.getInt("haHurtTime"));
  }
  
  @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
  private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
    nbt.putInt("haHurtTime", hybrid_aquatic$getHurtTime());
  }
  
  @Inject(method = "damage", at = @At(
    value = "INVOKE",
    target = "Lnet/minecraft/entity/player/PlayerEntity;getWorld()Lnet/minecraft/world/World;",
    ordinal = 0,
    shift = At.Shift.BEFORE))
  private void setCustomHurtTimeOnDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
    PlayerEntity object = ((PlayerEntity) (Object) this);
    
    if(object.isTouchingWater()) {
      LivingEntity foundEntity = object.getWorld().getClosestEntity(HybridAquaticSharkEntity.class, TargetPredicate.createNonAttackable().setBaseMaxDistance(16).setPredicate(Entity::isSubmergedInWater), object, object.getX(), object.getEyeY(), object.getZ(), object.getBoundingBox().expand(16));
      if(foundEntity != null) hybrid_aquatic$setHurtTime(200);
    }
  }
  
  @Inject(method = "tick", at = @At("TAIL"))
  private void tickDownCustomHurtTime(CallbackInfo ci) {
    int cHurtTime = hybrid_aquatic$getHurtTime();
    if (cHurtTime > 0) {
      hybrid_aquatic$setHurtTime(cHurtTime - 1);
    }
  }
}
