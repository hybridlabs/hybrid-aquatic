package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments;
import dev.hybridlabs.aquatic.enchantment.LiveCatchEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin extends ProjectileEntity {
  public FishingBobberEntityMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
    super(entityType, world);
  }
  
  @Inject(method = "use", cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT,
    at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;getWorld()Lnet/minecraft/world/World;", ordinal = 4)
  )
  private void injectEnchantment(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, PlayerEntity playerEntity, int i, LootContextParameterSet lootContextParameterSet, LootTable lootTable, List list, Iterator var7, ItemStack itemStack) {
    HashMap<Item, EntityType<? extends WaterCreatureEntity>> ITEM_TO_ENTITY = LiveCatchEnchantment.Companion.getITEM_TO_ENTITYTYPE();
    
    if(EnchantmentHelper.getLevel(HybridAquaticEnchantments.INSTANCE.getLIVECATCH(), usedItem) > 0 && ITEM_TO_ENTITY.containsKey(itemStack.getItem())) {
      WaterCreatureEntity entity = ITEM_TO_ENTITY.get(itemStack.getItem()).create(this.getWorld());
      entity.setPosition(this.getPos());
      
      double modifier = 0.1;
      Vec3d vecBetween = playerEntity.getPos().subtract(this.getPos());
      Vec3d vecBetweenMod = vecBetween.multiply(modifier);
      entity.setVelocity(
        vecBetweenMod.x,
        vecBetweenMod.y + Math.sqrt(Math.sqrt(Math.pow(vecBetween.x, 2) + Math.pow(vecBetween.y, 2) + Math.pow(vecBetween.z, 2))) * 0.08,
        vecBetweenMod.z
      );
      
      this.getWorld().spawnEntity(entity);
      
      if (itemStack.isIn(ItemTags.FISHES)) {
        playerEntity.increaseStat(Stats.FISH_CAUGHT, 1);
      }
      
      int returnValue = 1;
      if (this.isOnGround()) {
        returnValue = 2;
      }
      
      this.discard();
      cir.setReturnValue(returnValue);
    }
  }
}
