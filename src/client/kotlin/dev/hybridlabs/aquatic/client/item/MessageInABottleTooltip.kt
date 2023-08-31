package dev.hybridlabs.aquatic.client.item

import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextContent
import net.minecraft.util.Formatting

class MessageInABottleTooltip : ItemTooltipCallback {
    override fun getTooltip(stack: ItemStack, context: TooltipContext, lines: MutableList<Text>) {
        stack.getSubNbt(BlockItem.BLOCK_ENTITY_TAG_KEY)?.let { nbt ->
            val messageString = nbt.getString(MessageInABottleBlockEntity.MESSAGE_KEY)
            var lastText = Text.empty()
            val lastString = StringBuilder()
            var lastStyle = Style.EMPTY

            val text = Text.Serializer.fromJson(messageString)?.formatted(Formatting.GRAY, Formatting.ITALIC) ?: return

            if (text.content == TextContent.EMPTY && text.siblings.isEmpty()) {
                return
            }

            text.asOrderedText().accept { _, style, codePoint ->
                val char = codePoint.toChar()
                if (char == '\n') {
                    // append last text if newline
                    lastText.append(Text.literal(lastString.toString()).setStyle(lastStyle))
                    lines.add(lastText)
                    lastString.clear()
                    lastText = Text.empty()
                } else {
                    val string = char.toString()
                    if (style == lastStyle) {
                        // if same style, add to current string
                        lastString.append(string)
                    } else {
                        // if different style, append text
                        lastText.append(Text.literal(lastString.toString()).setStyle(lastStyle))

                        // new string
                        lastString.clear()
                        lastString.append(string)

                        // new style
                        lastStyle = style
                    }
                }

                true
            }

            lastText.append(Text.literal(lastString.toString()).setStyle(lastStyle))
            lines.add(lastText)
        }
    }
}
