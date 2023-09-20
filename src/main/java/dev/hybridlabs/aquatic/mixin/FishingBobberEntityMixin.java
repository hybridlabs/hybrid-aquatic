package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments;
import dev.hybridlabs.aquatic.enchantment.LiveCatchEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
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
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
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
    hybrid_aquatic$setLureItem(Registries.ITEM.get(new Identifier(nbt.getString("lureItem"))));
  }
  
  @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
  private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
    nbt.putString("lureItem", Registries.ITEM.getId(hybrid_aquatic$getLureItem()).toString());
  }
  
  @Unique
  private Item lureItem = Items.AIR;

  public Item hybrid_aquatic$getLureItem() {
    return lureItem;
  }
  
  public void hybrid_aquatic$setLureItem(Item item) {
    lureItem = (item == null ? Items.AIR : item);
  }
  
  // Gets objects for changeSpawnEntity function
  @Unique
  ItemStack usedItem;
  @Unique
  PlayerEntity playerEntity;
  @Unique
  ItemStack generatedItem;
  @Inject(method = "use", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(
    value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;getWorld()Lnet/minecraft/world/World;", ordinal = 4
  ))
  private void localsGetter(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, PlayerEntity playerEntity, int returnValue, LootContextParameterSet lootContextParameterSet, LootTable lootTable, List<ItemStack> generatedLootList, Iterator<ItemStack> forLoopIterator, ItemStack itemInIterator) {
    this.usedItem = usedItem;
    this.playerEntity = playerEntity;
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
      Vec3d vecBetween = playerEntity.getPos().subtract(this.getPos());
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
}
