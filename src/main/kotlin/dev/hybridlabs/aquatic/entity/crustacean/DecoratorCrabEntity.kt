package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation

class DecoratorCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
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

    override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/decorator_crab")
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

    override fun interactMob(player: PlayerEntity, hand: Hand): ActionResult {
        val itemStack = player.getStackInHand(hand)
        if (!itemStack.isEmpty && itemStack.isOf(Items.SHEARS) && coralTimer == 0) {
            if (!world.isClient) {
                this.coralTimer = 3600
                this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0f, 1.0f)
                this.emitGameEvent(GameEvent.SHEAR, player)
                itemStack.damage(1, player) { it.sendToolBreakStatus(hand) }
                dropStack(ItemStack(HybridAquaticItems.CORAL_CHUNK))
                return ActionResult.SUCCESS
            }
            return ActionResult.CONSUME
        }
        return super.interactMob(player, hand)
    }

    override fun tick() {
        super.tick()

        if (coralTimer > 0) coralTimer -= 1
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
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