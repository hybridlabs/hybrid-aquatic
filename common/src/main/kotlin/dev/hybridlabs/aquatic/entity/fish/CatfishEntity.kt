package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.hapi.entity.base.VariantHolder
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class CatfishEntity(type: EntityType<out CatfishEntity>, world: Level) :
    BaseFishEntity(type, world),
    VariantHolder<CatfishEntity.Companion.Type> {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.LARGE_CREATURES,
        HAPIEntityTags.ALL_SHARKS
    )

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun getMinSize(): Int {
        return -3
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(Items.SPIDER_EYE)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        val spawnData = super.finalizeSpawn(world, difficulty, spawnReason, entityData)

        val variant = Type.entries.random(Random).id
        this.variant = Type.fromId(variant)

        this.refreshDimensions()

        return spawnData
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(CatfishEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            CHANNEL_CATFISH(0, "channel_catfish");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: StringRepresentable.EnumCodec<Type> = StringRepresentable.fromEnum { entries.toTypedArray() }
                private val BY_ID: IntFunction<Type> = ByIdMap.continuous(
                    { obj: Type -> obj.id },
                    entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.ZERO
                )

                fun byName(name: String): Type {
                    return CODEC.byName(name, CHANNEL_CATFISH) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(TYPE, 0)
        super.defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        output.putString("Type", this.variant.serializedName)
        super.addAdditionalSaveData(output)
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        this.variant = Type.byName(input.getStringOr("Type", this.variant.serializedName))
        super.readAdditionalSaveData(input)
    }

    override var variant: Type
        get() = Type.fromId((entityData.get(TYPE) as Int))
        set(value) {
            entityData.set(TYPE, value.id)
        }
}
