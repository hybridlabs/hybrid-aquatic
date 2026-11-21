package dev.hybridlabs.aquatic.mixin.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.geckolib.animation.state.AnimationTimeline;

import java.util.List;

/**
 * GeckoLib 5.4.5 assumes that a controller with a non-zero transition time always resolves at least
 * one animation stage, and calls {@link List#getLast()} on an empty list otherwise (a missing
 * animation name), throwing {@link java.util.NoSuchElementException}. Skip the trailing transition
 * stage when there is nothing to blend from.
 */
@Mixin(value = AnimationTimeline.class, remap = false)
public class GeckoLibAnimationTimelineMixin {

    @Redirect(
            method = "create",
            at = @At(value = "INVOKE", target = "Ljava/util/List;getLast()Ljava/lang/Object;"),
            remap = false)
    private static Object hybridAquatic$safeGetLast(List<Object> stages) {
        return stages.isEmpty()
                ? new AnimationTimeline.Stage(0.0, 0.0, true, null, null)
                : stages.getLast();
    }

    @Redirect(
            method = "create",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 2),
            remap = false)
    private static boolean hybridAquatic$skipDanglingTransition(List<Object> stages, Object stage) {
        if (stage instanceof AnimationTimeline.Stage animationStage && animationStage.animation() == null) {
            return false;
        }

        return stages.add(stage);
    }
}
