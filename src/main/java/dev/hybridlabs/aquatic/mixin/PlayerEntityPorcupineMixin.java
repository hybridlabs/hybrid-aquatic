package dev.hybridlabs.aquatic.mixin;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityPorcupineMixin extends LivingEntity implements Porcupine {

    List<ItemStack> impaledStacks = new ArrayList<>();
    private static final String IMPALED_ITEM_KEY = "impaled_items";

    protected PlayerEntityPorcupineMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Get stacks currently impaled in player
     * @return immutable list of stacks within player
     */
    @Override
    public List<ItemStack> hybrid_aquatic$getImpaledStacks() {
        return Collections.unmodifiableList(impaledStacks);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        impaledStacks.clear();

        // Check if list exists, otherwise we're going to have issues.
        if (nbt.contains(IMPALED_ITEM_KEY, NbtElement.LIST_TYPE)) {
            var list = nbt.getList(IMPALED_ITEM_KEY, NbtElement.COMPOUND_TYPE);

            for (var item : list) if (item instanceof NbtCompound itemNBT) {
                var stack = ItemStack.fromNbt(itemNBT);
                impaledStacks.add(stack);
            }
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        if (!impaledStacks.isEmpty()) {
            var list = new NbtList();
            for (var stack : impaledStacks) {
                var writtenNBT = stack.writeNbt(new NbtCompound());
                list.add(writtenNBT);
            }
            nbt.put(IMPALED_ITEM_KEY, list);
        }
    }
}
