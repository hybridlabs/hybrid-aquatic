package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.utils.HybridAquaticSpawnGroup;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@SuppressWarnings("unused")
@Mixin(MobCategory.class)
public class SpawnGroupMixin {
    SpawnGroupMixin(String enumname, int ordinal, String name, int spawnCap, boolean peaceful, boolean rare,
                    int immediateDespawnRange) {
        throw new AssertionError();
    }

    // Vanilla Spawn Groups array
    @Shadow
    @Mutable
    @Final
    private static MobCategory[] $VALUES;

    @Unique
    private static MobCategory createHybridAquaticSpawnGroups(String enumname, int ordinal,
                                                              HybridAquaticSpawnGroup spawnGroup) {
        return ((MobCategory) (Object) new SpawnGroupMixin(spawnGroup.name, ordinal, spawnGroup.name,
                spawnGroup.spawnCap, spawnGroup.peaceful, spawnGroup.rare, spawnGroup.immediateDespawnRange));
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/MobCategory;" +
            "$VALUES:[Lnet/minecraft/world/entity/MobCategory;", shift = At.Shift.AFTER))
    private static void injectEnum(CallbackInfo ci) {
        int vanillaSpawnGroupsLength = $VALUES.length;
        for (int i = 0; i < vanillaSpawnGroupsLength; i++) {
            MobCategory category = $VALUES[i];
            HybridAquaticSpawnGroup.BY_NAME.put(category.name(), category);
        }
        HybridAquaticSpawnGroup[] haSpawnGroups = HybridAquaticSpawnGroup.values();
        $VALUES = Arrays.copyOf($VALUES, vanillaSpawnGroupsLength + haSpawnGroups.length);

        for (int i = 0; i < haSpawnGroups.length; i++) {
            int pos = vanillaSpawnGroupsLength + i;
            HybridAquaticSpawnGroup haSpawnGroup = haSpawnGroups[i];
            haSpawnGroup.spawnGroup = $VALUES[pos] = createHybridAquaticSpawnGroups(haSpawnGroup.name(), pos,
                    haSpawnGroup);

            HybridAquaticSpawnGroup.BY_NAME.put(haSpawnGroup.name().toUpperCase(), haSpawnGroup.spawnGroup);
        }
    }
}
