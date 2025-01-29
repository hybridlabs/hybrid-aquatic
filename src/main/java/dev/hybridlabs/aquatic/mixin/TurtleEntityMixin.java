package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TurtleEntity.class)
public class TurtleEntityMixin extends MobEntity {

    protected TurtleEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "createTurtleAttributes", at = @At("RETURN"), cancellable = true)
    private static void injectTurtleAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        DefaultAttributeContainer.Builder builder = cir.getReturnValue();
        if (builder != null) {
            builder.add(EntityAttributes.ATTACK_DAMAGE, 3.0);
        }
        cir.setReturnValue(builder);
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    protected void registerGoals(CallbackInfo ci) {
        this.goalSelector.add(7, new AttackGoal(this));
        this.targetSelector.add(7, new ActiveTargetGoal<>(this, HybridAquaticJellyfishEntity.class, false));
    }
}
