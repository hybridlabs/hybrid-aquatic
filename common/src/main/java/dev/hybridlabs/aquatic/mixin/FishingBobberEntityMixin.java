package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public abstract class FishingBobberEntityMixin extends Entity implements CustomFishingBobberEntityData {


    // Gets fishing rod item and player that used it for Injects in "use()" function below
    @Unique
    ItemStack usedItem;
    @Unique
    Player usedPlayer;
    @Shadow
    private int timeUntilLured;
    @Unique
    private ItemStack lureItemStack = Items.AIR.getDefaultInstance();

    private FishingBobberEntityMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readCustomDataFromNbt(CompoundTag nbt, CallbackInfo ci) {
        hybrid_aquatic$setLureItem(ItemStack.(nbt.getCompound("lureItem")));
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeCustomDataToNbt(CompoundTag nbt, CallbackInfo ci) {
        CompoundTag itemStack = new CompoundTag();
        hybrid_aquatic$getLureItem().save(itemStack);
        nbt.put("lureItem", itemStack);
    }

    public ItemStack hybrid_aquatic$getLureItem() {
        return lureItemStack;
    }

    public void hybrid_aquatic$setLureItem(ItemStack item) {
        lureItemStack = (item == null ? Items.AIR.getDefaultInstance() : item);
    }

    // Reduces wait time faster if you have hooks on the fishing rod
    @Inject(method = "catchingFish", at = @At(value = "INVOKE", target =
            "Lnet/minecraft/util/Mth;nextInt" + "(Lnet" + "/minecraft/util/RandomSource;II)I", ordinal = 2, shift =
            At.Shift.AFTER))
    private void reduceCooldownTime(BlockPos pos, CallbackInfo ci) {
        Item lureItem = this.lureItemStack.getItem();
        if (lureItem.equals(HybridAquaticItems.INSTANCE.getBARBED_HOOK().get()) && this.level().isDay()) {
            timeUntilLured -= 75;
        } else if (lureItem.equals(HybridAquaticItems.INSTANCE.getGLOWING_HOOK().get()) && this.level().isNight()) {
            timeUntilLured -= 75;
        }
    }

    @Inject(method = "retrieve", at = @At(value = "INVOKE", target =
            "Lnet/minecraft/world/level/Level;getServer()" + "Lnet/minecraft/server/MinecraftServer;", ordinal = 0))
    private void objectGetter(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, @Local Player playerEntity) {
        this.usedItem = usedItem;
        this.usedPlayer = playerEntity;
    }

    // Increases chance of getting treasure item with magnetic hook
    @WrapOperation(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player" +
            "/Player;getLuck()F"))
    private float increaseLuck(Player player, Operation<Float> original) {
        if (lureItemStack.getItem().equals(HybridAquaticItems.INSTANCE.getMAGNETIC_HOOK().get()))
            return player.getLuck() + 27;
        return original.call(player);
    }

    // Whenever we may want to replace entities we use this. This will make sure not to spawn any
    // unwanted entities when we reel in the hook.
    @ModifyReceiver(method = "retrieve", slice = @Slice(from = @At(value = "NEW", target = "Lnet/minecraft/world" +
            "/level/storage/loot/LootParams$Builder;")), at = @At(value = "INVOKE", target =
            "Lnet/minecraft/world" + "/level/storage/loot/LootTable;getRandomItems" + "(Lnet/minecraft/world/level" + "/storage/loot/LootParams;)" + "Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"))
    private LootTable onHookReelEntity(LootTable instance, LootParams parameters) {
        if (!lureItemStack.isEmpty()) {
            if (lureItemStack.is(HybridAquaticItems.INSTANCE.getOMINOUS_HOOK().get())) {
                var karkinosType = HybridAquaticEntityTypes.INSTANCE.getKARKINOS().get();
                createAndLaunchEntityAtPlayer(karkinosType);

                instance = LootTable.EMPTY;
            } else if (lureItemStack.is(HybridAquaticItems.INSTANCE.getCREEPERMAGNET_HOOK().get())) {
                var creeperType = EntityType.CREEPER;
                createAndLaunchEntityAtPlayer(creeperType);

                instance = LootTable.EMPTY;
            }

            // Damage lure AFTER we catch anything with it
            lureItemStack.hurtAndBreak(1, usedPlayer, (player) -> this.level().playSound(null, this,
                    SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f));
        }

        return instance;
    }

    @Unique
    private void createAndLaunchEntityAtPlayer(EntityType<?> entityType) {
        if (this.level() instanceof ServerLevel serverWorld) {
            Entity entity = entityType.spawn(serverWorld, this.blockPosition(), MobSpawnType.MOB_SUMMONED);
            if (entity == null)
                return;

            double modifier = 0.15;
            Vec3 vecBetween = usedPlayer.position().subtract(this.position());
            Vec3 vecBetweenMod = vecBetween.scale(modifier);
            var yOffset =
                    Math.sqrt(Math.sqrt(Math.pow(vecBetween.x, 2) + Math.pow(vecBetween.y, 2) + Math.pow(vecBetween.z
                            , 2))) * 0.08;
            entity.setDeltaMovement(vecBetweenMod.x, vecBetweenMod.y + yOffset, vecBetweenMod.z);

        }

    }

    // Returns lure back on a successful fishing attempt
    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile" +
            "/FishingHook;discard()V"))
    private void retrieveLureOnSuccess(ItemStack usedItem, CallbackInfoReturnable<Integer> cir) {
        retrieveLure(usedPlayer);
    }

    // Returns lure back if player removes fishing rod
    @Inject(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile"
            + "/FishingHook;discard()V"))
    private void retrieveLureIfInvalid(Player player, CallbackInfoReturnable<Boolean> cir) {
        retrieveLure(player);
    }

    // TODO: Lures can disappear if you reload the world while fishing (or maybe even when you rejoin the server,
    //  haven't checked that yet)
    //       Really not sure how to fix that
    @Unique
    private void retrieveLure(Player player) {
        if (!lureItemStack.isEmpty()) {
            Vec3 pos;
            if (player == null || player.isRemoved() || !player.isAlive()) {
                pos = this.position();
            } else {
                if (player.getInventory().add(lureItemStack))
                    return;

                pos = player.position();
            }

            ItemEntity itemEntity = new ItemEntity(this.level(), pos.x, pos.y, pos.z, lureItemStack);
            itemEntity.setDeltaMovement(Vec3.ZERO);
            this.level().addFreshEntity(itemEntity);
        }
    }
}
