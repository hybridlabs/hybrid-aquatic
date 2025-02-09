package dev.hybridlabs.aquatic.entity.fish.carp

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import kotlin.random.Random


class CarpEntity(entityType: EntityType<out CarpEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "koi" to FishVariant.biomeVariant(
                "koi", listOf(HybridAquaticBiomeTags.CHERRY),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "common" to FishVariant.biomeVariant(
                "common", listOf(HybridAquaticBiomeTags.RIVERS),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
        ),
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "koi" -> Identifier("hybrid-aquatic", "gameplay/koi")
            "common" -> Identifier("hybrid-aquatic", "entity/carp")
            else -> super.getLootTableId()
        }
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {

        setBaseTexture()

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun initDataTracker() {
        dataTracker.startTracking(carpBaseColor, 0)
        super.initDataTracker()
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("BaseColor", this.getBaseColor())
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        dataTracker.set(carpBaseColor, nbt.getInt("BaseColor"))
    }

    private fun setBaseTexture() {
        val i = Random.nextInt(100)
        val baseColor = when {
            i <= 10 -> CarpBaseColor.WHITE
            i <= 20 -> CarpBaseColor.RED
            i <= 30 -> CarpBaseColor.BLACK
            else -> CarpBaseColor.DEFAULT
        }
        setBaseColor(baseColor)
    }

    private val carpBaseColor: TrackedData<Int> =
        DataTracker.registerData(CarpEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

    init {
        if (!world.isClient) {
            dataTracker.set(carpBaseColor, Random.nextInt(CarpBaseColor.entries.size))
        }
    }

    fun getCarpBaseColor(): CarpBaseColor {
        return CarpBaseColor.byId(this.getBaseColor() and 255)
    }

    private fun setBaseColor(baseColor: CarpBaseColor) {
        dataTracker.set(carpBaseColor, baseColor.id and 255)
    }

    private fun getBaseColor(): Int {
        return dataTracker.get(carpBaseColor)
    }
}