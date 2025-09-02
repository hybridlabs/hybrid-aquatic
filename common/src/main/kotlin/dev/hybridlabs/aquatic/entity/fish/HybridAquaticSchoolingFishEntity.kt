package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.goal.FishFollowGroupLeaderGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.tags.TagKey
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.stream.Stream

@Suppress("NAME_SHADOWING")
open class HybridAquaticSchoolingFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: Level,
    override val prey: List<TagKey<EntityType<*>>>,
    override val predator: List<TagKey<EntityType<*>>>,
    private var leader: HybridAquaticSchoolingFishEntity? = null,
    private var groupSize: Int = 1,
    protected val variants: Map<String, FishVariant> = hashMapOf(),
    override val assumeDefault: Boolean = false,
    override val collisionRules: List<VariantCollisionRules> = listOf()
) : HybridAquaticFishEntity(
    type,
    world,
    variants,
    listOf(HybridAquaticEntityTags.NONE),
    listOf(HybridAquaticEntityTags.NONE)
) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, FishFollowGroupLeaderGoal(this))
    }

    override fun getMaxSpawnClusterSize(): Int {
        return this.getMaxGroupSize()
    }

    open fun getMaxGroupSize(): Int {
        return super.getMaxSpawnClusterSize()
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
        if (this.hasOtherFishInGroup() && level().random.nextInt(200) == 1) {
            val list: List<HybridAquaticFishEntity?> =
                level().getEntitiesOfClass(this.javaClass, boundingBox.inflate(8.0, 8.0, 8.0))
            if (list.size <= 1) {
                this.groupSize = 1
            }
        }
    }

    fun hasOtherFishInGroup(): Boolean {
        return this.groupSize > 1
    }

    fun isCloseEnoughToLeader(): Boolean {
        return this.distanceToSqr(this.leader) <= 121.0
    }

    fun moveTowardLeader() {
        if (this.hasLeader()) {
            getNavigation().moveTo(this.leader, 1.0)
        }
    }

    fun pullInOtherFish(fish: Stream<out HybridAquaticSchoolingFishEntity?>) {
        fish.limit((this.getMaxGroupSize() - this.groupSize).toLong())
            .filter { fishx: HybridAquaticSchoolingFishEntity? -> fishx !== this }
            .forEach { fishx: HybridAquaticSchoolingFishEntity? ->
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
            joinGroupOf((entityData as FishData).leader)
        }
        return entityData
    }

    open class FishData(val leader: HybridAquaticSchoolingFishEntity) : SpawnGroupData
}
