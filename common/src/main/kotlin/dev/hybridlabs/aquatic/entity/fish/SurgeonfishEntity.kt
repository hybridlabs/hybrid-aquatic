package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.VariantHolder
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import net.minecraft.util.function.ByIdMap
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class SurgeonfishEntity(entityType: EntityType<out SurgeonfishEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ),
    VariantHolder<SurgeonfishEntity.Companion.Type> {

    override fun getSpawnClusterSize(): Int {
        return 3
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        variant = Type.entries.random(Random)
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getLootTableId(): ResourceLocation {
        return when (variant) {
            Type.BLUE_TANG -> HybridAquaticLootTables.SURGEONFISH_BLUE_TANG
            Type.POWDER_BLUE_TANG -> HybridAquaticLootTables.SURGEONFISH_POWDER_BLUE_TANG
            Type.YELLOW_TANG -> HybridAquaticLootTables.SURGEONFISH_YELLOW_TANG
            Type.LINED -> HybridAquaticLootTables.SURGEONFISH_LINED
            Type.ORANGESHOULDER -> HybridAquaticLootTables.SURGEONFISH_ORANGESHOULDER
            Type.SOHAL -> HybridAquaticLootTables.SURGEONFISH_SOHAL
            Type.UNICORNFISH -> HybridAquaticLootTables.SURGEONFISH_UNICORNFISH
        }
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(SurgeonfishEntity::class.java, EntityDataSerializers.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            BLUE_TANG(0, "blue_tang"),
            POWDER_BLUE_TANG(1, "powder_blue_tang"),
            YELLOW_TANG(2, "yellow_tang"),
            LINED(3, "lined"),
            ORANGESHOULDER(4, "orangeshoulder"),
            SOHAL(5, "sohal"),
            UNICORNFISH(6, "unicornfish");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: StringRepresentable.EnumCodec<Type> = StringRepresentable.fromEnum { entries.toTypedArray() }
                private val BY_ID: IntFunction<Type> = ByIdMap.continuous(
                    { obj: Type -> obj.id },
                    entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.ZERO
                )

                fun byName(name: String?): Type {
                    return CODEC.byName(name, BLUE_TANG) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
    }

    override fun initSynchedEntityData() {
        entityData.define(TYPE, 0)
        super.initSynchedEntityData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.asString())
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        super.readAdditionalSaveData(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}