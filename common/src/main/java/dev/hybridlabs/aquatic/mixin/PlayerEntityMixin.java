package dev.hybridlabs.aquatic.mixin;

import com.google.common.collect.ImmutableList;
import dev.hybridlabs.aquatic.access.CustomPlayerEntityData;
import dev.hybridlabs.aquatic.effect.HAMobEffects;

import dev.hybridlabs.aquatic.item.HAItems;
import dev.hybridlabs.aquatic.item.HAToolMaterials;
import dev.hybridlabs.hapi.entity.water.base.BaseSharkEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends Entity implements CustomPlayerEntityData {

    public PlayerEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract boolean isSwimming();

    @Unique
    private int haHurtTime = 0;

    @Unique
    private boolean isHoldingDivingWeight;

    @Override
    public void setHybridHurtTime(int value) {
        haHurtTime = value;
    }

    @Override
    public int getHybridHurtTime() {
        return haHurtTime;
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readCustomDataFromNbt(CompoundTag nbt, CallbackInfo ci) {
        setHybridHurtTime(nbt.getInt("haHurtTime"));
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeCustomDataToNbt(CompoundTag nbt, CallbackInfo ci) {
        nbt.putInt("haHurtTime", getHybridHurtTime());
    }

    @Inject(method = "isAffectedByFluids", at = @At("HEAD"), cancellable = true)
    private void overrideShouldSwimInFluids(CallbackInfoReturnable<Boolean> ci) {
        if ((isHoldingDivingWeight) && !isSwimming() && isUnderWater()) {
            ci.setReturnValue(false);
        }
    }

    @Inject(
            method = "hurt",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/entity/player/Player;level()Lnet/minecraft/world/level/Level;",
                            ordinal = 0,
                            shift = At.Shift.BEFORE))
    private void setCustomHurtTimeOnDamage(
            DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Player object = (Player) (Object) this;

        if (object.isInWater()) {
            LivingEntity foundEntity =
                    object.level()
                            .getNearestEntity(
                                    BaseSharkEntity.class,
                                    TargetingConditions.forNonCombat()
                                            .range(32)
                                            .selector(Entity::isUnderWater),
                                    object,
                                    object.getX(),
                                    object.getEyeY(),
                                    object.getZ(),
                                    object.getBoundingBox().inflate(16));
            if (foundEntity != null) setHybridHurtTime(200);
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickDownCustomHurtTime(CallbackInfo ci) {
        int cHurtTime = getHybridHurtTime();
        if (cHurtTime > 0) {
            setHybridHurtTime(cHurtTime - 1);
        }
        // Gives Water Breathing/Clarity if player has Diving Helmet equipped
        updateDivingHelmet();
        // Allows player to walk in the water without jumping
        updateDivingWeight();
        // Gives Resistance and Slowness if player has Turtle chestplate equipped
        updateTurtleChestplate();
        // Repairs coral tools in the water
        repairCoralTools();
    }@Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void hybridaquatic$boostSeashellUnderwaterSpeed(
            BlockState state, CallbackInfoReturnable<Float> cir
    ) {
        Player player = (Player)(Object)this;

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof TieredItem tieredItem)) return;

        if (tieredItem.getTier() != HAToolMaterials.SEASHELL) return;

        if (player.isEyeInFluid(FluidTags.WATER)) {
            var aquaAffinity = player.level()
                    .registryAccess()
                    .lookupOrThrow(Registries.ENCHANTMENT)
                    .getOrThrow(Enchantments.AQUA_AFFINITY);

            boolean hasAquaAffinity =
                    EnchantmentHelper.getEnchantmentLevel(aquaAffinity, player) > 0;

            float speed = cir.getReturnValue();

            if (!hasAquaAffinity) {
                speed *= 5.0F;
            }

            cir.setReturnValue(speed);
        }
    }

    @Unique
    private void updateDivingHelmet() {
        var player = (Player) (Object) this;
        ItemStack itemStack = player.getItemBySlot(EquipmentSlot.HEAD);

        if (itemStack.is(HAItems.INSTANCE.getDIVING_HELMET().get())) {
            if (!player.isEyeInFluid(FluidTags.WATER)) {
                player.addEffect(
                        new MobEffectInstance(
                                MobEffects.WATER_BREATHING, 600, 0, false, false, false));
            } else {
                player.addEffect(
                        new MobEffectInstance(
                                HAMobEffects.INSTANCE.getCLARITY().asHolder(),
                                600,
                                0,
                                false,
                                false,
                                false));
            }
        }

        if (itemStack.is(HAItems.INSTANCE.getREINFORCED_DIVING_HELMET().get())) {
            if (!player.isEyeInFluid(FluidTags.WATER)) {
                player.addEffect(
                        new MobEffectInstance(
                                MobEffects.WATER_BREATHING, 1800, 0, false, false, false));
            } else {
                player.addEffect(
                        new MobEffectInstance(
                                HAMobEffects.INSTANCE.getCLARITY().asHolder(),
                                1800,
                                0,
                                false,
                                false,
                                false));
            }
        }

        if (itemStack.is(HAItems.INSTANCE.getGLOWING_DIVING_HELMET().get())) {
            if (!player.isEyeInFluid(FluidTags.WATER)) {
                player.addEffect(
                        new MobEffectInstance(
                                MobEffects.WATER_BREATHING, 1800, 0, false, false, false));
            } else {
                player.addEffect(
                        new MobEffectInstance(
                                HAMobEffects.INSTANCE.getCLARITY().asHolder(),
                                1800,
                                0,
                                false,
                                false,
                                false));
            }
        }
    }

    @Unique
    private void updateDivingWeight() {
        var player = (Player) (Object) this;

        isHoldingDivingWeight =
                player.getMainHandItem().is(HAItems.INSTANCE.getDIVING_WEIGHT().get()) ||
                        player.getOffhandItem().is(HAItems.INSTANCE.getDIVING_WEIGHT().get());
    }

    @Unique
    private void updateTurtleChestplate() {
        var player = (Player) (Object) this;
        var itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (itemStack.is(HAItems.INSTANCE.getTURTLE_CHESTPLATE().get())) {
            player.addEffect(
                    new MobEffectInstance(
                            MobEffects.DAMAGE_RESISTANCE, 200, 0, false, false, true));
            player.addEffect(
                    new MobEffectInstance(
                            MobEffects.MOVEMENT_SLOWDOWN, 200, 0, false, false, true));
        }
    }

    @Unique
    int coralRepairTick = 0;

    @Unique
    private void repairCoralTools() {
        var player = (Player) (Object) this;
        var inventory = player.getInventory();

        if (player.isEyeInFluid(FluidTags.WATER)) {
            if (coralRepairTick > 5) {
                List<NonNullList<ItemStack>> combinedInventory =
                        ImmutableList.of(inventory.items, inventory.offhand);
                List<ItemStack> coralItems = new ArrayList<>();
                for (List<ItemStack> list : combinedInventory) {
                    for (ItemStack itemStack : list) {
                        if (itemStack.getItem() instanceof TieredItem tool
                                && tool.getTier() == HAToolMaterials.CORAL
                                && itemStack.isDamaged()) {
                            coralItems.add(itemStack);
                        }
                    }
                }

                if (!coralItems.isEmpty()) {
                    ItemStack item = coralItems.get(player.getRandom().nextInt(coralItems.size()));
                    item.setDamageValue(item.getDamageValue() - 1);
                    inventory.setChanged();
                }
                coralRepairTick = 0;
            }

            coralRepairTick++;
        }
    }
}
