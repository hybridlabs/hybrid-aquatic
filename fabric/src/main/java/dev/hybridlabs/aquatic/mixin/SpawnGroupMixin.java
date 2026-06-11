package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.utils.HASpawnGroup;
import net.minecraft.world.entity.MobCategory;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@SuppressWarnings("unused")
@Mixin(MobCategory.class)
public class SpawnGroupMixin {
    SpawnGroupMixin(String enumname, int ordinal, String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        throw new AssertionError();
    }

    // Vanilla Spawn Groups array
    @Shadow
    @Mutable
    @Final
    private static MobCategory[] $VALUES;

    @Unique
    private static MobCategory createHybridAquaticSpawnGroups(String enumname, int ordinal, HASpawnGroup spawnGroup) {
        SpawnGroupMixin groups = new SpawnGroupMixin(
                spawnGroup.name(), ordinal, spawnGroup.location.toString(),
                spawnGroup.spawnCap, spawnGroup.peaceful, spawnGroup.rare, spawnGroup.immediateDespawnRange
        );

        return (MobCategory) (Object) groups;
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/MobCategory;" +
            "$VALUES:[Lnet/minecraft/world/entity/MobCategory;", shift = At.Shift.AFTER, opcode = Opcodes.PUTSTATIC))
    private static void injectEnum(CallbackInfo ci) {
        int vanillaSpawnGroupsLength = $VALUES.length;

        HASpawnGroup[] haSpawnGroups = HASpawnGroup.values();
        $VALUES = Arrays.copyOf($VALUES, vanillaSpawnGroupsLength + haSpawnGroups.length);

        for (int i = 0; i < haSpawnGroups.length; i++) {
            int pos = vanillaSpawnGroupsLength + i;
            HASpawnGroup haSpawnGroup = haSpawnGroups[i];
            haSpawnGroup.spawnGroup = $VALUES[pos] = createHybridAquaticSpawnGroups(haSpawnGroup.name(), pos, haSpawnGroup);
        }

        for (HASpawnGroup value : haSpawnGroups) {
            HASpawnGroup.BY_NAME.put(value.location.toString(), value.spawnGroup);
        }

        Arrays.stream($VALUES).forEach(value -> HybridAquaticSpawnGroup.BY_NAME.put(value.name(), value));
    }
}
