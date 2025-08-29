package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import kotlin.random.Random

class TigerSharkEntity(entityType: EntityType<out TigerSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(
        entityType,
        world,
        listOf(
            HybridAquaticEntityTags.CEPHALOPOD,
            HybridAquaticEntityTags.CRUSTACEAN,
            HybridAquaticEntityTags.MEDIUM_PREY
        ),
        false,
        false
    ) {

    private var burpTimer = 0
    private var burpPending = false

    override fun tick() {
        super.tick()

        if (burpPending && burpTimer > 0) {
            burpTimer--
            if (burpTimer == 0) {
                dropBurpItem()
                burpPending = false
            }
        }
    }

    private fun dropBurpItem() {
        if (!level().isClientSide) {
            val itemsToDrop = listOf(
                ItemStack(Items.LEATHER_BOOTS),
                ItemStack(Items.GLASS_BOTTLE),
                ItemStack(Items.SCUTE),
                ItemStack(Items.NAUTILUS_SHELL),
                ItemStack(Items.SKELETON_SKULL),
                ItemStack(Items.BONE),
                ItemStack(Items.PRISMARINE_CRYSTALS),
                ItemStack(Items.PRISMARINE_SHARD),
                ItemStack(Items.BRUSH),
                ItemStack(Items.NAME_TAG),
                ItemStack(Items.COMPASS),
                ItemStack(Items.CLOCK),
                ItemStack(Items.SPYGLASS),
                ItemStack(Items.SADDLE),
                ItemStack(HybridAquaticItems.SHARK_TOOTH.get()),
                ItemStack(HybridAquaticItems.CUTTLEBONE.get()),
                ItemStack(HybridAquaticItems.GLOWING_HOOK.get()),
                ItemStack(HybridAquaticItems.BARBED_HOOK.get()),
            )

            val randomItem = itemsToDrop.random(Random)

            this.spawnAtLocation(randomItem)

            this.playSound(SoundEvents.PLAYER_BURP, 1.0f, 1.0f)
        }
    }

    override fun killedEntity(world: ServerLevel, killedEntity: LivingEntity): Boolean {
        val result = super.killedEntity(world, killedEntity)

        burpTimer = 60
        burpPending = true

        return result
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 54.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }

    override fun getMaxSize(): Int {
        return 2
    }

    override fun getMinSize(): Int {
        return -2
    }
}
