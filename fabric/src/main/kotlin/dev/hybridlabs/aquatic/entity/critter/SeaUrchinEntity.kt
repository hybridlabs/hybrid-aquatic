package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.tags.BiomeTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class SeaUrchinEntity(entityType: EntityType<out SeaUrchinEntity>, world: Level) :
    HybridAquaticCritterEntity(
        entityType, world, variants = hashMapOf(
            "black" to CritterVariant.biomeVariant(
                "black", listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "blue" to CritterVariant.biomeVariant(
                "blue", listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "purple" to CritterVariant.biomeVariant(
                "purple", listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "red" to CritterVariant.biomeVariant(
                "red", listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "long_black" to CritterVariant.biomeVariant(
                "long_black", listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "long_blue" to CritterVariant.biomeVariant(
                "long_blue", listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "long_purple" to CritterVariant.biomeVariant(
                "long_purple", listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "long_red" to CritterVariant.biomeVariant(
                "long_red", listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
        )
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/sea_urchin")
    }

    private var timeUntilNextBreak = 0
    private var spawnUrchinOnNextBreak = false

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }
    }

    override fun playerTouch(player: Player) {
        super.playerTouch(player)

        if (player is ServerPlayer) {
            player.hurt(this.damageSources().mobAttack(this), 0.5f)
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isUnderWater) {
            event.controller.setAnimation(WALK_ANIMATION)
        }
        return PlayState.CONTINUE
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount)) {

            val attacker = source.directEntity
            if (attacker is LivingEntity && attacker.mainHandItem.isEmpty) {
                attacker.hurt(this.damageSources().thorns(this), 2.0f)
            }

            return true
        }

        return false
    }

    override fun tick() {
        super.tick()

        if (level().isClientSide) {
            return
        }

        if (timeUntilNextBreak > 0) {
            timeUntilNextBreak--
            return
        }

        if (level().random.nextInt(6000) < 300) {
            breakKelpUnderneath()
            timeUntilNextBreak = 2400 + level().random.nextInt(1200)
        }
    }

    private fun breakKelpUnderneath() {
        val posUnderneath = BlockPos(this.x.toInt(), (this.y + 1).toInt(), this.z.toInt())
        if (level().getBlockState(posUnderneath).`is`(Blocks.KELP_PLANT)) {
            level().setBlockAndUpdate(posUnderneath, Blocks.AIR.defaultBlockState())
            if (spawnUrchinOnNextBreak) {
                val newUrchin = HybridAquaticEntityTypes.SEA_URCHIN.get().create(level())
                newUrchin?.moveTo(this.x, this.y, this.z, this.yRot, 0.0f)
                level().addFreshEntity(newUrchin)
                spawnUrchinOnNextBreak = false
            } else {
                spawnUrchinOnNextBreak = true
            }
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}
