package dev.hybridlabs.aquatic.entity.shark

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.PassiveFeedingGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSharkEntity
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.tags.BiomeTags
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.animation.state.AnimationTest
import software.bernie.geckolib.animation.`object`.PlayState
import java.util.function.IntFunction

class WhaleSharkEntity(type: EntityType<out WhaleSharkEntity>, world: Level) :
    BaseSharkEntity(type, world), OverlayTextureFeature {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, PassiveFeedingGoal(this))
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.RAW_SHRIMP.get())
    }

    //#region Data
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(OverlayTexture, 0)
        super.defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        output.putInt("texture_overlay", this.overlayTexture.id)
        super.addAdditionalSaveData(output)
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        if (input.getInt("texture_overlay").isPresent) this.overlayTexture =
            OverlayTextures.byId(input.getIntOr("texture_overlay", this.overlayTexture.id))
        super.readAdditionalSaveData(input)
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllers)
        controllers.add(
            AnimationController<BaseSharkEntity>("Feeding",
                AnimationStateHandler { state: AnimationTest<BaseSharkEntity> ->
                    if (this.isFeeding())
                        return@AnimationStateHandler state.setAndContinue(FEED_ANIMATION)
                    PlayState.STOP
                }
            )
        )
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(WhaleSharkEntity::class.java, EntityDataSerializers.INT)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
            SPOTS(0, "spots"),
            SMALL_SPOTS(1, "small_spots");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<OverlayTextures> =
                    StringRepresentable.fromEnum { OverlayTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<OverlayTextures> = ByIdMap.continuous(
                    { overlayTex: OverlayTextures -> overlayTex.id },
                    OverlayTextures.entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.WRAP
                )

                fun byId(id: Int): OverlayTextures {
                    return BY_ID.apply(id)
                }
            }
        }
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        val overlayID = world.random.nextIntBetweenInclusive(0, OverlayTextures.entries.size - 1)
        overlayTexture = OverlayTextures.byId(overlayID)

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    private var overlayTexture
        get() = OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return OverlayTextures.byId(entityData.get(OverlayTexture)).serializedName
    }

    override fun getMaxSize(): Int {
        val level = this.level()
        val biome = level.getBiome(this.blockPosition())

        return if (biome.`is`(BiomeTags.IS_DEEP_OCEAN)) {
            10
        } else {
            5
        }
    }

    override fun getMinSize(): Int {
        return -5
    }
}
