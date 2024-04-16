package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishFollowGroupLeaderGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import java.util.stream.Stream

open class HybridAquaticSchoolingFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: World,
    override val prey: TagKey<EntityType<*>>,
    override val predator: TagKey<EntityType<*>>,
) : HybridAquaticFishEntity(type, world, 1, HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {
    private var leader: HybridAquaticSchoolingFishEntity? = null
    private var groupSize = 1

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishFollowGroupLeaderGoal(this))
    }

    override fun getLimitPerChunk(): Int {
        return getMaxGroupSize()
    }

    private fun getMaxGroupSize(): Int {
        return super.getLimitPerChunk()
    }

    override fun hasSelfControl(): Boolean {
        return !hasLeader()
    }

    fun hasLeader(): Boolean {
        return leader != null && leader!!.isAlive
    }

    private fun joinGroupOf(groupLeader: HybridAquaticSchoolingFishEntity): HybridAquaticSchoolingFishEntity {
        leader = groupLeader
        groupLeader.increaseGroupSize()
        return groupLeader
    }

    fun leaveGroup() {
        leader!!.decreaseGroupSize()
        leader = null
    }

    private fun increaseGroupSize() {
        ++groupSize
    }

    private fun decreaseGroupSize() {
        --groupSize
    }

    fun canHaveMoreFishInGroup(): Boolean {
        return hasOtherFishInGroup() && groupSize < getMaxGroupSize()
    }

    override fun tick() {
        super.tick()
        if (hasOtherFishInGroup() && world.random.nextInt(200) == 1) {
            val list: List<HybridAquaticFishEntity?> =
                world.getNonSpectatingEntities(this.javaClass, boundingBox.expand(8.0, 8.0, 8.0))
            if (list.size <= 1) {
                groupSize = 1
            }
        }
    }

    fun hasOtherFishInGroup(): Boolean {
        return groupSize > 1
    }

    fun isCloseEnoughToLeader(): Boolean {
        return this.squaredDistanceTo(leader) <= 121.0
    }

    fun moveTowardLeader() {
        if (hasLeader()) {
            getNavigation().startMovingTo(leader, 1.0)
        }
    }

    fun pullInOtherFish(fish: Stream<out HybridAquaticSchoolingFishEntity>) {
        fish.limit((getMaxGroupSize() - groupSize).toLong())
            .filter { fishx: HybridAquaticSchoolingFishEntity -> fishx !== this }
            .forEach { fishx: HybridAquaticSchoolingFishEntity -> fishx.joinGroupOf(this) }
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        var entityData = entityData
        super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
        if (entityData == null) {
            entityData = FishData(this)
        } else {
            joinGroupOf((entityData as FishData).leader)
        }
        return entityData
    }


    open class FishData(val leader: HybridAquaticSchoolingFishEntity) : EntityData
}
