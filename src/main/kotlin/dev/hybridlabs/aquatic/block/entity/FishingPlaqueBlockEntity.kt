package dev.hybridlabs.aquatic.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos
import kotlin.jvm.optionals.getOrNull

class FishingPlaqueBlockEntity(pos: BlockPos?, state: BlockState?) :
    BlockEntity(HybridAquaticBlockEntityTypes.FISHING_PLAQUE, pos, state) {

    private var storedEntity: Entity? = null

    override fun readNbt(nbt: NbtCompound) {
        val storedNBT : NbtCompound? = nbt.getCompound("storedEntity")
        storedEntity = EntityType.getEntityFromNbt(storedNBT, this.world).getOrNull()
        super.readNbt(nbt)
    }

    override fun writeNbt(nbt: NbtCompound) {
        var compound = NbtCompound()
        if (storedEntity != null) {
            compound = storedEntity!!.writeNbt(compound)
            nbt.put("storedEntity", compound)
        }
        super.writeNbt(nbt)
    }

    fun getStoredEntity(): Entity? {
        return storedEntity
    }

    fun setStoredEntity(entity: Entity?) {
        this.storedEntity = entity
    }

}