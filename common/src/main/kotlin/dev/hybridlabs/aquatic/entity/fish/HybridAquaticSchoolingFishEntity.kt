package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishFollowGroupLeaderGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.VariantHolder
import net.minecraft.nbt.CompoundTag
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import java.util.stream.Stream

@Suppress("NAME_SHADOWING")
open class HybridAquaticSchoolingFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: Level,
    override val prey: List<TagKey<EntityType<*>>>,
    override val predator: List<TagKey<EntityType<*>>>,
    private var leader: HybridAquaticSchoolingFishEntity? = null,
    private var groupSize: Int = 1,
) : HybridAquaticFishEntity(type, world, listOf(HybridAquaticEntityTags.NONE), listOf(HybridAquaticEntityTags.NONE)) {

    open fun getVariant(): Any? {
        return if (this is VariantHolder<*>) {
            (this as VariantHolder<*>).variant
        } else null
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, FishFollowGroupLeaderGoal(this))
    }

    override fun getSpawnClusterSize(): Int {
        return this.getMaxGroupSize()
    }

    open fun getMaxGroupSize(): Int {
        return super.getSpawnClusterSize()
    }

    override fun hasSelfControl(): Boolean {
        return !this.hasLeader()
    }

    fun hasLeader(): Boolean {
        return this.leader != null && leader!!.isAlive
    }

    private fun joinGroupOf(groupLeader: HybridAquaticSchoolingFishEntity): HybridAquaticSchoolingFishEntity {
        if (this.getVariant() != groupLeader.getVariant()) return this
        this.leader = groupLeader
        groupLeader.increaseGroupSize()
        return groupLeader
    }


    fun leaveGroup() {
        leader!!.decreaseGroupSize()
        this.leader = null
    }

    private fun increaseGroupSize() {
        ++this.groupSize
    }

    private fun decreaseGroupSize() {
        --this.groupSize
    }

    fun canHaveMoreFishInGroup(): Boolean {
        return this.hasOtherFishInGroup() && this.groupSize < this.getMaxGroupSize()
    }

    override fun tick() {
        super.tick()
        if (this.hasOtherFishInGroup() && world.random.nextInt(200) == 1) {
            val list: List<HybridAquaticFishEntity?> =
                world.getNonSpectatingEntities(this.javaClass, boundingBox.expand(8.0, 8.0, 8.0))
            if (list.size <= 1) {
                this.groupSize = 1
            }
        }
    }

    fun hasOtherFishInGroup(): Boolean {
        return this.groupSize > 1
    }

    fun isCloseEnoughToLeader(): Boolean {
        return this.squaredDistanceTo(this.leader) <= 121.0
    }

    fun moveTowardLeader() {
        if (this.hasLeader()) {
            getNavigation().startMovingTo(this.leader, 1.0)
        }
    }

    fun pullInOtherFish(fish: Stream<out HybridAquaticSchoolingFishEntity?>) {
        val selfVariant = this.getVariant()
        fish
            .filter { fishx ->
                fishx != null &&
                        fishx !== this &&
                        fishx.getVariant() == selfVariant
            }
            .limit((this.getMaxGroupSize() - this.groupSize).toLong())
            .forEach { fishx ->
                fishx!!.joinGroupOf(this)
            }
    }


    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        var entityData = entityData
        xRot = 0.0f
        super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
        if (entityData == null) {
            entityData = FishData(this)
        } else {
            val leader = (entityData as FishData).leader
            if (this.getVariant() == leader.getVariant()) {
                joinGroupOf(leader)
            }
        }
        return entityData
    }

    open class FishData(val leader: HybridAquaticSchoolingFishEntity) : EntityData
}
