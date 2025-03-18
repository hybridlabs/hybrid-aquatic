package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.tag.BiomeTags
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class SeaUrchinEntity(entityType: EntityType<out SeaUrchinEntity>, world: World) :
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

    public override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/sea_urchin")
    }

    private var timeUntilNextBreak = 0
    private var spawnUrchinOnNextBreak = false

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
        }
    }

    override fun onPlayerCollision(player: PlayerEntity) {
        super.onPlayerCollision(player)

        if (player is ServerPlayerEntity) {
            player.damage(DamageSource.thorns(this), 0.5f)
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(WALK_ANIMATION)
        }
        return PlayState.CONTINUE
    }

    override fun damage(source: DamageSource?, amount: Float): Boolean {
        if (super.damage(source, amount)) {

            val attacker = source?.attacker
            if (attacker is LivingEntity && attacker.mainHandStack.isEmpty) {
                attacker.damage(this.damageSources.thorns(this), 2.0f)
            }

            return true
        }

        return false
    }

    override fun tick() {
        super.tick()

        if (world.isClient) {
            return
        }

        if (timeUntilNextBreak > 0) {
            timeUntilNextBreak--
            return
        }

        if (world.random.nextInt(6000) < 300) {
            breakKelpUnderneath()
            timeUntilNextBreak = 2400 + world.random.nextInt(1200)
        }
    }

    private fun breakKelpUnderneath() {
        val posUnderneath = BlockPos(this.x.toInt(), (this.y + 1).toInt(), this.z.toInt())
        if (world.getBlockState(posUnderneath).isOf(Blocks.KELP_PLANT)) {
            world.setBlockState(posUnderneath, Blocks.AIR.defaultState)
            if (spawnUrchinOnNextBreak) {
                val newUrchin = HybridAquaticEntityTypes.SEA_URCHIN.create(world)
                newUrchin?.refreshPositionAndAngles(this.x, this.y, this.z, this.yaw, 0.0f)
                world.spawnEntity(newUrchin)
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
