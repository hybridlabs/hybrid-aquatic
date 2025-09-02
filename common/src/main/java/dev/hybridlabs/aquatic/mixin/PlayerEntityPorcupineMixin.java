package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.HybridAquatic;
import dev.hybridlabs.aquatic.interfaces.Porcupine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(Player.class)
public abstract class PlayerEntityPorcupineMixin extends LivingEntity implements Porcupine {
    @Unique
    private final List<ItemStack> impaledStacks = new ArrayList<>();
    @Unique
    private static final String IMPALED_ITEM_KEY = HybridAquatic.MOD_ID + ":impaled_items";

    private PlayerEntityPorcupineMixin(EntityType<? extends LivingEntity> type, Level world) {
        super(type, world);
    }

    /**
     * Get stacks currently impaled in player
     *
     * @return immutable list of stacks within player
     */
    public @Unique List<ItemStack> hybrid_aquatic$getImpaledStacks() {
        return Collections.unmodifiableList(impaledStacks);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void onReadCustomDataFromNbt(CompoundTag nbt, CallbackInfo ci) {
        impaledStacks.clear();

        // Check if list exists, otherwise we're going to have issues.
        if (nbt.contains(IMPALED_ITEM_KEY, Tag.TAG_LIST)) {
            ListTag list = nbt.getList(IMPALED_ITEM_KEY, Tag.TAG_COMPOUND);

            list.stream().map(CompoundTag.class::cast).forEach(stackNbt -> {
                ItemStack stack = ItemStack.of(stackNbt);
                impaledStacks.add(stack);
            });
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void onWriteCustomDataToNbt(CompoundTag nbt, CallbackInfo ci) {
        if (!impaledStacks.isEmpty()) {
            ListTag list = new ListTag();
            impaledStacks.forEach(stack -> {
                CompoundTag stackNbt = stack.save(new CompoundTag());
                list.add(stackNbt);
            });
            nbt.put(IMPALED_ITEM_KEY, list);
        }
    }
}
