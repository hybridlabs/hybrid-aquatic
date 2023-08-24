package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.utils.HybridAquaticSpawnGroups;
import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

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
    int vanillaSGLength = field_6301.length;
    HybridAquaticSpawnGroups[] haGroups = HybridAquaticSpawnGroups.values();
    int haSGLength = haGroups.length;
    field_6301 = Arrays.copyOf(field_6301, vanillaSGLength + haSGLength);
    
    for (int i = 0; i < haSGLength; i++) {
      int pos = vanillaSGLength + i;
      HybridAquaticSpawnGroups haSpawnGroup = haGroups[i];
      haSpawnGroup.spawnGroup = field_6301[pos] = createHybridAquaticSpawnGroups(haSpawnGroup.name(), pos, haSpawnGroup.name, haSpawnGroup.spawnCap, haSpawnGroup.peaceful, haSpawnGroup.rare, haSpawnGroup.immediateDespawnRange);
    }
  }
}
