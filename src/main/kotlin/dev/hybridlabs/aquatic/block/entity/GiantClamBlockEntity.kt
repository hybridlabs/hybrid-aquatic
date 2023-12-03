package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.GiantClamBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtils

class GiantClamBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.GIANT_CLAM, pos, state), GeoAnimatable {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    var pearlCooldown: Int = 0

    private fun <E> predicate(event: AnimationState<E>): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (world != null) {
            if(cachedState.get(GiantClamBlock.CLAM_HAS_PEARL)) event.controller.setAnimation(OPEN_ANIMATION)
            else event.controller.setAnimation(CLOSED_ANIMATION)

            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "controller", 0, ::predicate))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun getTick(o: Any): Double {
        return RenderUtils.getCurrentTick()
    }

    override fun readNbt(nbt: NbtCompound) {
        pearlCooldown = nbt.getInt(PEARL_COOLDOWN_NBT_KEY)
        super.readNbt(nbt)
    }

    override fun writeNbt(nbt: NbtCompound) {
        nbt.putInt(PEARL_COOLDOWN_NBT_KEY, pearlCooldown)
        super.writeNbt(nbt)
    }

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }

    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    companion object {
        const val PEARL_COOLDOWN_NBT_KEY: String = "pearl_cooldown"
        val OPEN_ANIMATION: RawAnimation = RawAnimation.begin().then("open", Animation.LoopType.LOOP)
        val CLOSED_ANIMATION: RawAnimation = RawAnimation.begin().then("closed", Animation.LoopType.LOOP)

        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: GiantClamBlockEntity) {
            if(blockEntity.pearlCooldown > 0) blockEntity.pearlCooldown--
            world.setBlockState(pos, state.with(GiantClamBlock.CLAM_HAS_PEARL, blockEntity.pearlCooldown == 0))
        }
    }
}
