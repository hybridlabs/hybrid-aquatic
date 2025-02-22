package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.HybridAquatic;
import dev.hybridlabs.aquatic.interfaces.Porcupine;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityPorcupineMixin extends LivingEntity implements Porcupine {
    @Unique private final List<ItemStack> impaledStacks = new ArrayList<>();
    @Unique private static final String IMPALED_ITEM_KEY = HybridAquatic.MOD_ID + ":impaled_items";

    private PlayerEntityPorcupineMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    /**
     * Get stacks currently impaled in player
     * @return immutable list of stacks within player
     */
    public @Unique List<ItemStack> hybrid_aquatic$getImpaledStacks() {
        return Collections.unmodifiableList(impaledStacks);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void onReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        impaledStacks.clear();

        // Check if list exists, otherwise we're going to have issues.
        if (nbt.contains(IMPALED_ITEM_KEY, NbtElement.LIST_TYPE)) {
            NbtList list = nbt.getList(IMPALED_ITEM_KEY, NbtElement.COMPOUND_TYPE);
            list.stream().map(NbtCompound.class::cast).forEach(stackNbt -> {
                ItemStack stack = ItemStack.fromNbt(stackNbt);
                impaledStacks.add(stack);
            });
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void onWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (!impaledStacks.isEmpty()) {
            NbtList list = new NbtList();
            impaledStacks.forEach(stack -> {
                NbtCompound stackNbt = stack.writeNbt(new NbtCompound());
                list.add(stackNbt);
            });
            nbt.put(IMPALED_ITEM_KEY, list);
        }
    }
}
