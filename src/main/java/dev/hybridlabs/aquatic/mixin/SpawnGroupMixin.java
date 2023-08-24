package dev.hybridlabs.aquatic.mixin;

import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

import static dev.hybridlabs.aquatic.utils.HybridAquaticSpawnGroups.*;

@Mixin(SpawnGroup.class)
public class SpawnGroupMixin {
  SpawnGroupMixin(String enumname, int ordinal, String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
    throw new UnsupportedOperationException("Replaced by Mixin");
  }
  
  @Shadow @Mutable @Final
  private static SpawnGroup[] field_6301;
  
  @Unique
  @SuppressWarnings("SameParameterValue")
  private static SpawnGroup createHybridAquaticSpawnGroups(String enumname, int ordinal, String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
    return ((SpawnGroup)(Object) new SpawnGroupMixin(enumname, ordinal, name, spawnCap, peaceful, rare, immediateDespawnRange));
  }
  
  @Inject(method = "<clinit>",
    at = @At(
      value = "FIELD",
      target = "Lnet/minecraft/entity/SpawnGroup;field_6301:[Lnet/minecraft/entity/SpawnGroup;",
      shift = At.Shift.AFTER
    )
  )
  private static void injectEnum(CallbackInfo ci) {
    int ordinal = field_6301.length;
    field_6301 = Arrays.copyOf(field_6301, ordinal + 3);
    
    HA_FISH = field_6301[ordinal] = createHybridAquaticSpawnGroups("HA_FISH", ordinal, "ha_fish", 18, true, false, 64);
    HA_SHARK = field_6301[ordinal+1] = createHybridAquaticSpawnGroups("HA_SHARK", ordinal+1, "ha_shark", 3, false, false, 64);
    HA_CRITTER = field_6301[ordinal+2] = createHybridAquaticSpawnGroups("HA_CRITTER", ordinal+2, "ha_critter", 6, true, false, 64);
  }
}
