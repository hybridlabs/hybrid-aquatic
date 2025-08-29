package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments;
import dev.hybridlabs.aquatic.enchantment.LiveCatchEnchantment;
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
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Mixin(FishingHook.class)
public abstract class FishingBobberEntityMixin extends Projectile implements CustomFishingBobberEntityData {
    @Shadow
    private int timeUntilLured;

    public FishingBobberEntityMixin(EntityType<? extends Projectile> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readCustomDataFromNbt(CompoundTag nbt, CallbackInfo ci) {
        hybrid_aquatic$setLureItem(ItemStack.of(nbt.getCompound("lureItem")));
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeCustomDataToNbt(CompoundTag nbt, CallbackInfo ci) {
        CompoundTag itemStack = new CompoundTag();
        hybrid_aquatic$getLureItem().save(itemStack);
        nbt.put("lureItem", itemStack);
    }

    @Unique
    private ItemStack lureItemStack = Items.AIR.getDefaultInstance();

    public ItemStack hybrid_aquatic$getLureItem() {
        return lureItemStack;
    }

    public void hybrid_aquatic$setLureItem(ItemStack item) {
        lureItemStack = (item == null ? Items.AIR.getDefaultInstance() : item);
    }

    // Reduces wait time faster if you have hooks on the fishing rod
    @Inject(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;" + "nextInt" +
            "(Lnet/minecraft/util/RandomSource;II)I", ordinal = 2, shift = At.Shift.AFTER))
    private void reduceCooldownTime(BlockPos pos, CallbackInfo ci) {
        Item lureItem = this.lureItemStack.getItem();
        if (lureItem.equals(HybridAquaticItems.INSTANCE.getBARBED_HOOK()) && this.level().isDay()) {
            timeUntilLured -= 75;
        } else if (lureItem.equals(HybridAquaticItems.INSTANCE.getGLOWING_HOOK()) && this.level().isNight()) {
            timeUntilLured -= 75;
        }
    }

    // Gets objects for the functions below
    @Unique
    ItemStack usedItem;
    @Unique
    Player usedPlayer;

    @Inject(method = "retrieve", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", target =
            "Lnet" + "/minecraft/world/entity/projectile/FishingHook;level()Lnet/minecraft/world/level/Level;",
            ordinal = 0))
    private void objectGetter(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, Player playerEntity) {
        this.usedItem = usedItem;
        this.usedPlayer = playerEntity;
    }

    // Damages lure
    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot" +
            "/LootParams$Builder;create(Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;)" +
            "Lnet/minecraft/world/level/storage/loot/LootParams;"))
    private void lureDamage(ItemStack usedItem, CallbackInfoReturnable<Integer> cir) {
        lureItemStack.hurtAndBreak(1, usedPlayer, (test) -> this.level().playSound(null, this, SoundEvents.ITEM_BREAK
                , SoundSource.PLAYERS, 1.0f, 1.0f));
    }

    // Increases chance of getting treasure item with magnetic hook
    @Redirect(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot" +
            "/LootParams$Builder;withLuck(F)Lnet/minecraft/world/level/storage/loot/LootParams$Builder;"))
    private LootParams.Builder increaseLuck(LootParams.Builder instance, float luck) {
        if (lureItemStack.getItem().equals(HybridAquaticItems.INSTANCE.getMAGNETIC_HOOK()))
            luck += 27;
        return instance.withLuck(luck);
    }

    // Gets objects for changeSpawnEntity function
    @Unique
    ItemStack generatedItem;

    @Inject(method = "retrieve", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", target =
            "Lnet" + "/minecraft/world/entity/projectile/FishingHook;level()Lnet/minecraft/world/level/Level;",
            ordinal = 4))
    private void objectGetter(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, Player playerEntity,
                              int returnValue, LootParams lootContextParameterSet, LootTable lootTable,
                              List<ItemStack> generatedLootList, Iterator<ItemStack> forLoopIterator,
                              ItemStack itemInIterator) {
        this.generatedItem = itemInIterator;
    }

    // Replaces item that spawns when you fish a fish with a fish entity
    @Redirect(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;" +
            "addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z", ordinal = 0))
    private boolean changeSpawnEntity(Level instance, Entity entity) {
        if (this.level() instanceof ServerLevel serverWorld) {
            HashMap<Item, EntityType<? extends WaterAnimal>> ITEM_TO_ENTITY =
                    LiveCatchEnchantment.Companion.getITEM_TO_ENTITYTYPE();
            var entityType = ITEM_TO_ENTITY.get(generatedItem.getItem());
            Enchantment liveCatch = HybridAquaticEnchantments.INSTANCE.getLIVECATCH().get();
            if (entityType != null && EnchantmentHelper.getItemEnchantmentLevel(liveCatch, usedItem) > 0) {
                var liveFish = entityType.spawn(serverWorld, this.blockPosition(), MobSpawnType.SPAWN_EGG.SPAWN_EGG);
                if (liveFish == null) {
                    return false;
                }

                liveFish.setPos(this.position());

                // makes spawned fish whoosh towards you
                double modifier = 0.15;
                Vec3 vecBetween = usedPlayer.position().subtract(this.position());
                Vec3 vecBetweenMod = vecBetween.scale(modifier);
                var yOffset =
                        Math.sqrt(Math.sqrt(Math.pow(vecBetween.x, 2) + Math.pow(vecBetween.y, 2) + Math.pow(vecBetween.z, 2))) * 0.08;
                liveFish.setDeltaMovement(vecBetweenMod.x, vecBetweenMod.y + yOffset, vecBetweenMod.z);

                return true;
            }
        }

        return instance.addFreshEntity(entity);
    }

    // Whenever we may want to replace entities we use this. This will make sure not to spawn any unwanted entities
    // when we reel in the hook.
    @Inject(method = "retrieve", cancellable = true, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity"
            + "/projectile/FishingHook;level()Lnet/minecraft/world/level/Level;", ordinal = 2))
    private void onHookReelEntity(ItemStack usedItem, CallbackInfoReturnable<Integer> cir) {
        if (this.level() instanceof ServerLevel serverWorld) {
            if (!lureItemStack.isEmpty() && lureItemStack.is(HybridAquaticItems.INSTANCE.getOMINOUS_HOOK().get())) {
                try {
                    var karkinosType = HybridAquaticEntityTypes.INSTANCE.getKARKINOS().get();
                    var karkinos = karkinosType.spawn(serverWorld, blockPosition().offset(0, -1, 0),
                            MobSpawnType.MOB_SUMMONED);
                    if (karkinos == null)
                        return;

                    double modifier = 0.15;
                    Vec3 vecBetween = usedPlayer.position().subtract(this.position());
                    Vec3 vecBetweenMod = vecBetween.scale(modifier);
                    var yOffset =
                            Math.sqrt(Math.sqrt(Math.pow(vecBetween.x, 2) + Math.pow(vecBetween.y, 2) + Math.pow(vecBetween.z, 2))) * 0.08;
                    karkinos.setDeltaMovement(vecBetweenMod.x, vecBetweenMod.y + yOffset, vecBetweenMod.z);

                } finally {
                    this.discard();
                    cir.setReturnValue(1);
                }
            }
            if (!lureItemStack.isEmpty() && lureItemStack.is(HybridAquaticItems.INSTANCE.getCREEPERMAGNET_HOOK().get())) {
                try {
                    var creeperType = EntityType.CREEPER;
                    var creeper = creeperType.spawn(serverWorld, blockPosition().offset(0, -1, 0),
                            MobSpawnType.MOB_SUMMONED);
                    if (creeper == null)
                        return;

                    double modifier = 0.15;
                    Vec3 vecBetween = usedPlayer.position().subtract(this.position());
                    Vec3 vecBetweenMod = vecBetween.scale(modifier);
                    var yOffset =
                            Math.sqrt(Math.sqrt(Math.pow(vecBetween.x, 2) + Math.pow(vecBetween.y, 2) + Math.pow(vecBetween.z, 2))) * 0.08;
                    creeper.setDeltaMovement(vecBetweenMod.x, vecBetweenMod.y + yOffset, vecBetweenMod.z);

                } finally {
                    this.discard();
                    cir.setReturnValue(1);
                }
            }
        }
    }

    // Returns lure back on a successful fishing attempt
    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile" +
            "/FishingHook;discard()V"))
    private void retrieveLureOnSuccess(ItemStack usedItem, CallbackInfoReturnable<Integer> cir) {
        retrieveLure(usedPlayer);
    }

    // Returns lure back if player removes fishing rod
    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile" +
            "/FishingHook;discard()V"))
    private void retrieveLureIfInvalid(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        retrieveLure(usedPlayer);
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
