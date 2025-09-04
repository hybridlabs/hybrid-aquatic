package dev.hybridlabs.aquatic.mixin;

import com.google.common.collect.ImmutableList;
import dev.hybridlabs.aquatic.access.CustomPlayerEntityData;
import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects;
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import dev.hybridlabs.aquatic.item.HybridAquaticToolMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements CustomPlayerEntityData {

    @Shadow
    protected boolean isSubmergedInWater;

    @Shadow
    public abstract boolean isSwimming();

    @Unique
    private int haHurtTime = 0;

    @Unique
    private boolean isWearingDivingBoots;

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

    @Inject(method = "shouldSwimInFluids", at = @At("HEAD"), cancellable = true)
    private void overrideShouldSwimInFluids(CallbackInfoReturnable<Boolean> ci) {
        if (isWearingDivingBoots && !isSwimming() && isSubmergedInWater) {
            ci.setReturnValue(false);
        }
    }

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
        // Gives Water Breathing/Clarity if player has Diving Helmet equipped
        updateDivingHelmet();
        // Allows player to walk in the water without jumping
        updateDivingBoots();
        // Gives Resistance and Slowness if player has Turtle chestplate equipped
        updateTurtleChestplate();
        // Repairs coral tools in the water
        repairCoralTools();
    }

    @Unique
    private void updateDivingHelmet() {
        var player = (PlayerEntity) (Object) this;
        ItemStack itemStack = player.getEquippedStack(EquipmentSlot.HEAD);

        if (itemStack.isOf(HybridAquaticItems.INSTANCE.getDIVING_HELMET())) {
            if (!player.isSubmergedIn(FluidTags.WATER)) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 600, 0, false, false, false));
            } else {
                player.addStatusEffect(new StatusEffectInstance(HybridAquaticStatusEffects.INSTANCE.getCLARITY(), 600, 0, false, false, false));
            }
        }
    }

    @Unique
    private void updateDivingBoots() {
        var player = (PlayerEntity) (Object) this;
        ItemStack itemStack = player.getEquippedStack(EquipmentSlot.FEET);
        isWearingDivingBoots = itemStack.isOf(HybridAquaticItems.INSTANCE.getDIVING_BOOTS());
    }


    @Unique
    private void updateTurtleChestplate() {
        var player = (PlayerEntity) (Object) this;
        var itemStack = player.getEquippedStack(EquipmentSlot.CHEST);
        if (itemStack.isOf(HybridAquaticItems.INSTANCE.getTURTLE_CHESTPLATE())) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0, false, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0, false, false, true));
        }
    }

    @Unique
    int coralRepairTick = 0;

    @Unique
    private void repairCoralTools() {
        var player = (PlayerEntity) (Object) this;
        var inventory = player.getInventory();

        if (player.isSubmergedIn(FluidTags.WATER)) {
            if (coralRepairTick > 5) {
                List<DefaultedList<ItemStack>> combinedInventory = ImmutableList.of(inventory.main, inventory.offHand);
                List<ItemStack> coralItems = new ArrayList<>();
                for (List<ItemStack> list : combinedInventory) {
                    for (ItemStack itemStack : list) {
                        if (itemStack.getItem() instanceof ToolItem tool &&
                                tool.getMaterial() == HybridAquaticToolMaterials.CORAL &&
                                itemStack.isDamaged()) {
                            coralItems.add(itemStack);
                        }
                    }
                }

                if (!coralItems.isEmpty()) {
                    ItemStack item = coralItems.get(player.getRandom().nextInt(coralItems.size()));
                    item.setDamage(item.getDamage() - 1);
                    inventory.markDirty();
                }
                coralRepairTick = 0;
            }

            coralRepairTick++;
        }
    }
}
