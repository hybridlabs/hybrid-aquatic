package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.BiomeTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation

class DecoratorCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(
        entityType, world, false, variants = hashMapOf(
            "brain" to CrustaceanVariant.biomeVariant(
                "brain", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "fire" to CrustaceanVariant.biomeVariant(
                "fire", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "bubble" to CrustaceanVariant.biomeVariant(
                "bubble", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "horn" to CrustaceanVariant.biomeVariant(
                "horn", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "tube" to CrustaceanVariant.biomeVariant(
                "tube", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "sun" to CrustaceanVariant.biomeVariant(
                "sun", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "button" to CrustaceanVariant.biomeVariant(
                "button", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "lophelia" to CrustaceanVariant.biomeVariant(
                "lophelia", BiomeTags.IS_DEEP_OCEAN,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "thorn" to CrustaceanVariant.biomeVariant(
                "thorn", BiomeTags.IS_DEEP_OCEAN,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            )
        )
    ) {

    override fun getDefaultLootTable(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/decorator_crab")
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "With/Without", 0) { state ->
            val animation = when {
                coralTimer == 0 -> WITH_CORAL
                else -> WITHOUT_CORAL
            }
            state.setAndContinue(animation)
        })
        super.registerControllers(controllerRegistrar)
    }

    private var coralTimer = 0

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val itemStack = player.getItemInHand(hand)
        if (!itemStack.isEmpty && itemStack.`is`(Items.SHEARS) && coralTimer == 0) {
            if (!level().isClientSide) {
                this.coralTimer = 3600
                this.playSound(SoundEvents.SHEEP_SHEAR, 1.0f, 1.0f)
                this.gameEvent(GameEvent.SHEAR, player)
                itemStack.hurtAndBreak(1, player) { it.broadcastBreakEvent(hand) }
                spawnAtLocation(ItemStack(HybridAquaticItems.CORAL_CHUNK.get()))
                return InteractionResult.SUCCESS
            }
            return InteractionResult.CONSUME
        }
        return super.mobInteract(player, hand)
    }

    override fun tick() {
        super.tick()

        if (coralTimer > 0) coralTimer -= 1
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        val WITH_CORAL: RawAnimation = RawAnimation.begin().thenPlay("misc.with_coral")
        val WITHOUT_CORAL: RawAnimation = RawAnimation.begin().thenPlay("misc.without_coral")
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}