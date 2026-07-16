package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.tag.HAPaintingTags;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Painting.class)
public abstract class PaintingMixin extends HangingEntity {
	protected PaintingMixin(EntityType<? extends HangingEntity> entityType, Level level) {
		super(entityType, level);
	}
	
	@Shadow
	public abstract Holder<PaintingVariant> getVariant();
	
	// Sadly Painting's dropItem uses spawnAtLocation(ItemLike) instead of spawnAtLocation(ItemStack).
	// That's why I'm using Inject with ci.cancel()
	@Inject(method = "dropItem", cancellable = true, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/Painting;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"))
	private void test(Entity brokenEntity, CallbackInfo ci) {
		if (getVariant().is(HAPaintingTags.INSTANCE.getKEEPS_PAINTING_VARIANT())) {
			RegistryOps<Tag> registryOps = this.registryAccess().createSerializationContext(NbtOps.INSTANCE);
			CustomData customData = CustomData.EMPTY
					                        .update(registryOps, Painting.VARIANT_MAP_CODEC, getVariant())
					                        .getOrThrow()
					                        .update(compoundTag -> compoundTag.putString("id", "minecraft:painting"));
			var painting = Items.PAINTING.getDefaultInstance();
			painting.set(DataComponents.ENTITY_DATA, customData);
			
			this.spawnAtLocation(painting);
			
			ci.cancel();
		}
	}
}
