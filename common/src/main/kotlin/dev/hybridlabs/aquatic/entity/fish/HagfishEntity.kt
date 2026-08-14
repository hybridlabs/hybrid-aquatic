package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.tag.HABlockTags
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.WaterAnimalGrazeGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class HagfishEntity(type: EntityType<out HagfishEntity>, world: Level) :
    BaseFishEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.LARGE_CREATURES,
        HAPIEntityTags.ALL_SHARKS
    )

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(3, WaterAnimalGrazeGoal(this, HABlockTags.DETRITIVORE_EDIBLE))
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(Items.ROTTEN_FLESH)
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount)) {

            val attacker = source.directEntity
            if (attacker is LivingEntity) {
                attacker.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0))
                attacker.addEffect(MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1))
            }

            if (level().gameRules.getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                spawnAtLocation(HAItems.HAGSLIME.get())
            }

            return true
        }

        return false
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        fun canSpawn(
            type: EntityType<out HagfishEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel

            return pos.y in (seaLevel - 256)..(seaLevel - 24)
                    && world.isWaterAt(pos)
        }
    }
}