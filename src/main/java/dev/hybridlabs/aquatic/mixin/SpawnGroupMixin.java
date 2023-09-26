package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.utils.HybridAquaticSpawnGroup;
import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@SuppressWarnings("unused")
@Mixin(SpawnGroup.class)
public class SpawnGroupMixin {
    SpawnGroupMixin(String enumname, int ordinal, String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        throw new AssertionError();
    }

    // Vanilla Spawn Groups array
    @Shadow @Mutable @Final
    private static SpawnGroup[] field_6301;

    @Unique
    private static SpawnGroup createHybridAquaticSpawnGroups(String enumname, int ordinal, HybridAquaticSpawnGroup spawnGroup) {
        return ((SpawnGroup)(Object) new SpawnGroupMixin(spawnGroup.name, ordinal, spawnGroup.name, spawnGroup.spawnCap, spawnGroup.peaceful, spawnGroup.rare, spawnGroup.immediateDespawnRange));
    }

    @Inject(method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/SpawnGroup;field_6301:[Lnet/minecraft/entity/SpawnGroup;",
                    shift = At.Shift.AFTER
            )
    )
    private static void injectEnum(CallbackInfo ci) {
        int vanillaSpawnGroupsLength = field_6301.length;
        HybridAquaticSpawnGroup[] haSpawnGroups = HybridAquaticSpawnGroup.values();
        field_6301 = Arrays.copyOf(field_6301, vanillaSpawnGroupsLength + haSpawnGroups.length);

        for (int i = 0; i < haSpawnGroups.length; i++) {
            int pos = vanillaSpawnGroupsLength + i;
            HybridAquaticSpawnGroup haSpawnGroup = haSpawnGroups[i];
            haSpawnGroup.spawnGroup = field_6301[pos] = createHybridAquaticSpawnGroups(haSpawnGroup.name(), pos, haSpawnGroup);
        }
    }
}
