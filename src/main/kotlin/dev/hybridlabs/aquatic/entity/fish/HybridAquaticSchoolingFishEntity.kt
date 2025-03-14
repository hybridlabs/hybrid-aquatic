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

@Suppress("NAME_SHADOWING")
open class HybridAquaticSchoolingFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: World,
    override val prey: List<TagKey<EntityType<*>>>,
    override val predator: List<TagKey<EntityType<*>>>,
    private var leader: HybridAquaticSchoolingFishEntity? = null,
    private var groupSize: Int = 1,
    private val variants: Map<String, FishVariant> = hashMapOf(),
    override val assumeDefault: Boolean = false,
    override val collisionRules: List<VariantCollisionRules> = listOf()
) : HybridAquaticFishEntity(type, world, variants, listOf(HybridAquaticEntityTags.NONE), listOf(HybridAquaticEntityTags.NONE)) {

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishFollowGroupLeaderGoal(this))
    }

    override fun getLimitPerChunk(): Int {
        return this.getMaxGroupSize()
    }

    open fun getMaxGroupSize(): Int {
        return super.getLimitPerChunk()
    }

    override fun hasSelfControl(): Boolean {
        return !this.hasLeader()
    }

    fun hasLeader(): Boolean {
        return this.leader != null && leader!!.isAlive
    }

    private fun joinGroupOf(groupLeader: HybridAquaticSchoolingFishEntity): HybridAquaticSchoolingFishEntity {
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
        fish.limit((this.getMaxGroupSize() - this.groupSize).toLong())
            .filter { fishx: HybridAquaticSchoolingFishEntity? -> fishx !== this }
            .forEach { fishx: HybridAquaticSchoolingFishEntity? ->
                fishx!!.joinGroupOf(this)
            }
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        var entityData = entityData
        pitch = 0.0f
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
