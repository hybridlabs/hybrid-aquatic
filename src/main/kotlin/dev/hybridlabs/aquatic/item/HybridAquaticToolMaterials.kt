package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags


object HybridAquaticToolMaterials {
    val SEASHELL = ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0f, 1.0f, 22, HybridAquaticItemTags.SEASHELL_TOOL_MATERIALS)
    val CORAL = ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0f, 2.0f, 14, HybridAquaticItemTags.CORAL_TOOL_MATERIALS)
}
