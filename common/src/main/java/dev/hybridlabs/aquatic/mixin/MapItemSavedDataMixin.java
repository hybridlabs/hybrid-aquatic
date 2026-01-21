package dev.hybridlabs.aquatic.mixin;

import com.google.common.collect.Sets;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.hybridlabs.aquatic.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Set;

@Mixin(MapItemSavedData.class)
public class MapItemSavedDataMixin {
    @Unique
    private final Set<BlockPos> buoyMarkers = Sets.newHashSet();

    @Inject(method = "save", at = @At("TAIL"))
    private void onSave(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        ListTag buoysTag = new ListTag();
        buoyMarkers.forEach(pos -> buoysTag.add(BlockPos.CODEC.encodeStart(NbtOps.INSTANCE, pos).get().orThrow()));
        tag.put(Constants.MOD_ID + ":buoys", buoysTag);
    }

    @WrapMethod(method = "load")
    private static MapItemSavedData onLoad(CompoundTag tag, Operation<MapItemSavedData> original) {
        MapItemSavedData data = original.call(tag);
        MapItemSavedDataMixin mixinData = (MapItemSavedDataMixin) (Object) data;

        ListTag buoysTag = tag.getList(Constants.MOD_ID + ":buoys", Tag.TAG_INT_ARRAY);
        List<BlockPos> markers = buoysTag.stream()
                .map(posTag -> BlockPos.CODEC.decode(NbtOps.INSTANCE, posTag).result().get().getFirst())
                .toList();
        mixinData.buoyMarkers.addAll(markers);
        markers.forEach(pos -> {
            String name = Constants.MOD_ID + ":buoy" + pos.getX() + "," + pos.getY() + "," + pos.getZ();
            ((MapItemSavedDataAccessor) data).invokeAddDecoration(MapDecoration.Type.BANNER_CYAN, null, name, pos.getX(), pos.getZ(), 180.0, null);
        });

        return data;
    }
}
