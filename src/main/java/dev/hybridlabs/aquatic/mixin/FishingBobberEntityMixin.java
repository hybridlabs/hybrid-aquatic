package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments;
import dev.hybridlabs.aquatic.enchantment.LiveCatchEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
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

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin extends ProjectileEntity implements CustomFishingBobberEntityData {
  public FishingBobberEntityMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
    super(entityType, world);
  }
  
  @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
  private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
    hybrid_aquatic$setLureItem(ItemStack.fromNbt(nbt.getCompound("lureItem")));
  }
  
  @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
  private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
    NbtCompound itemStack = new NbtCompound();
    hybrid_aquatic$getLureItem().writeNbt(itemStack);
    nbt.put("lureItem", itemStack);
  }
  
  @Unique
  private ItemStack lureItem = Items.AIR.getDefaultStack();

  public ItemStack hybrid_aquatic$getLureItem() {
    return lureItem;
  }
  
  public void hybrid_aquatic$setLureItem(ItemStack item) {
    lureItem = (item == null ? Items.AIR.getDefaultStack() : item);
  }
  
  // Gets objects for functions below
  @Unique
  ItemStack usedItem;
  @Unique
  PlayerEntity usedPlayer;
  @Inject(method = "use", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(
    value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;getWorld()Lnet/minecraft/world/World;", ordinal = 0
  ))
  private void objectGetter(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, PlayerEntity playerEntity) {
    this.usedItem = usedItem;
    this.usedPlayer = playerEntity;
  }
  
  // Damages lure
  @Inject(method = "use", at = @At(
    value = "INVOKE", target = "Lnet/minecraft/loot/context/LootContextParameterSet$Builder;build(Lnet/minecraft/loot/context/LootContextType;)Lnet/minecraft/loot/context/LootContextParameterSet;"
  ))
  private void lureDamage(ItemStack usedItem, CallbackInfoReturnable<Integer> cir) {
    lureItem.damage(1, usedPlayer, (test) -> this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0f, 1.0f));
  }
  
  // Gets objects for changeSpawnEntity function
  @Unique
  ItemStack generatedItem;
  @Inject(method = "use", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(
    value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;getWorld()Lnet/minecraft/world/World;", ordinal = 4
  ))
  private void localsGetter(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, PlayerEntity playerEntity, int returnValue, LootContextParameterSet lootContextParameterSet, LootTable lootTable, List<ItemStack> generatedLootList, Iterator<ItemStack> forLoopIterator, ItemStack itemInIterator) {
    this.generatedItem = itemInIterator;
  }
  
  // Replaces item that spawns when you fish a fish with a fish entity
  @Redirect(method = "use", at = @At(
    value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z", ordinal = 0
  ))
  private boolean changeSpawnEntity(World instance, Entity entity) {
    HashMap<Item, EntityType<? extends WaterCreatureEntity>> ITEM_TO_ENTITY = LiveCatchEnchantment.Companion.getITEM_TO_ENTITYTYPE();
    Enchantment liveCatch = HybridAquaticEnchantments.INSTANCE.getLIVECATCH();
    
    if (EnchantmentHelper.getLevel(liveCatch, usedItem) > 0 &&
        ITEM_TO_ENTITY.containsKey(generatedItem.getItem()) &&
        this.getWorld() instanceof ServerWorld serverWorld) {
      var entityType = ITEM_TO_ENTITY.get(generatedItem.getItem());
      var liveFish = entityType.spawn(serverWorld, this.getBlockPos(), SpawnReason.SPAWN_EGG);
      liveFish.setPosition(this.getPos());
      
      // makes spawned fish whoosh towards you
      double modifier = 0.15;
      Vec3d vecBetween = usedPlayer.getPos().subtract(this.getPos());
      Vec3d vecBetweenMod = vecBetween.multiply(modifier);
      liveFish.setVelocity(
        vecBetweenMod.x,
        vecBetweenMod.y + Math.sqrt(Math.sqrt(Math.pow(vecBetween.x, 2) + Math.pow(vecBetween.y, 2) + Math.pow(vecBetween.z, 2))) * 0.08,
        vecBetweenMod.z
      );
      
      return true;
    }
    
    return instance.spawnEntity(entity);
  }
  
  // Returns lure back on successful fishing attempt
  @Inject(
    method = "use", at = @At(
      value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;discard()V"
  ))
  private void retrieveLureOnSuccess(ItemStack usedItem, CallbackInfoReturnable<Integer> cir) {
    retrieveLure(usedPlayer);
  }
  
  // Returns lure back if player removes fishing rod
  @Inject(
    method = "removeIfInvalid", at = @At(
      value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;discard()V"
  ))
  private void retrieveLureIfInvalid(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
    retrieveLure(player);
  }
  
  @Unique
  private void retrieveLure(PlayerEntity player) {
    ItemStack lure = hybrid_aquatic$getLureItem();
    if(!lure.isEmpty()) {
      if(!player.getInventory().insertStack(lure)) {
        ItemEntity itemEntity = new ItemEntity(this.getWorld(), player.getX(), player.getY(), player.getZ(), lure);
        itemEntity.setVelocity(Vec3d.ZERO);
        this.getWorld().spawnEntity(itemEntity);
      }
    }
  }
}
