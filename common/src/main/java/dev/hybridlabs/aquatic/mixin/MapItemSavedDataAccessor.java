package dev.hybridlabs.aquatic.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import javax.annotation.Nullable;

@Mixin(MapItemSavedData.class)
public interface MapItemSavedDataAccessor {
    @Invoker
    void invokeAddDecoration(MapDecoration.Type type, @Nullable LevelAccessor level, String decorationName, double levelX, double levelZ, double rotation, @Nullable Component name);
}
