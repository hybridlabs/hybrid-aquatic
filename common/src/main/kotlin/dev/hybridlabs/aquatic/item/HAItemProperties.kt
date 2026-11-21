package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item

/**
 * Item ids are no longer assigned by the registry, they have to be set on [Item.Properties] before
 * the item is constructed. Registration goes through [HAItems.register], which exposes the id being
 * registered here so item factories can build their properties without threading it through every
 * call site.
 */
object HAItemProperties {
    private val currentId = ThreadLocal<ResourceKey<Item>?>()

    fun <T> withId(id: String, factory: () -> T): T {
        val previous = currentId.get()
        currentId.set(ResourceKey.create(Registries.ITEM, CommonClass.locate(id)))
        try {
            return factory()
        } finally {
            currentId.set(previous)
        }
    }

    fun props(): Item.Properties {
        val id = checkNotNull(currentId.get()) { "Item properties must be created during item registration" }
        return Item.Properties().setId(id)
    }

    /**
     * Properties for a [net.minecraft.world.item.BlockItem]. Vanilla derives a block item's
     * translation key from the block (`block.<namespace>.<id>`) instead of the item, but that only
     * happens when [Item.Properties.useBlockDescriptionPrefix] is set.
     */
    fun blockProps(): Item.Properties = props().useBlockDescriptionPrefix()

    /**
     * Properties for a [net.minecraft.world.item.BlockItem] whose item id differs from the block id
     * (`clam` → `clams`, `hydrothermal_vent` → `hydrothermal_vent_shaft`). [blockProps] would build
     * `block.<namespace>.<item id>`, so use the block's own translation key instead.
     */
    fun blockProps(block: net.minecraft.world.level.block.Block): Item.Properties =
        props().overrideDescription(block.descriptionId)
}
