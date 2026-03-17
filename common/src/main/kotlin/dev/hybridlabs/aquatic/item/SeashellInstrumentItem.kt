package dev.hybridlabs.aquatic.item

import net.minecraft.ChatFormatting
import net.minecraft.Util
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.tags.TagKey
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.*
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import java.util.*
import java.util.function.Supplier

class SeashellInstrumentItem(properties: Properties, private val instruments: TagKey<Instrument>) : Item(properties) {
    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltip: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        super.appendHoverText(stack, level, tooltip, isAdvanced)

        val optional = getInstrument(stack).flatMap { it.unwrapKey() }

        if (optional.isPresent) {
            val component = Component.translatable(
                Util.makeDescriptionId("instrument", optional.get().location())
            )
            tooltip.add(component.withStyle(ChatFormatting.GRAY))
        }
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack?> {
        val itemstack = player.getItemInHand(usedHand)
        val optional = this.getInstrument(itemstack)
        if (optional.isPresent) {
            val instrument = (optional.get() as Holder<*>).value() as Instrument
            player.startUsingItem(usedHand)
            play(level, player, instrument)
            player.cooldowns.addCooldown(this, instrument.useDuration())
            player.awardStat(Stats.ITEM_USED.get(this))
            return InteractionResultHolder.consume<ItemStack?>(itemstack)
        } else {
            return InteractionResultHolder.fail<ItemStack?>(itemstack)
        }
    }

    override fun getUseDuration(stack: ItemStack): Int {
        val optional = this.getInstrument(stack)
        return optional.map { instrument: Holder<Instrument?>? -> (instrument!!.value() as Instrument).useDuration() }
            .orElse(0) as Int
    }

    private fun getInstrument(stack: ItemStack): Optional<out Holder<Instrument?>?> {
        val compoundtag = stack.tag
        if (compoundtag != null && compoundtag.contains("instrument", 8)) {
            val resourcelocation = ResourceLocation.tryParse(compoundtag.getString("instrument"))
            if (resourcelocation != null) {
                return BuiltInRegistries.INSTRUMENT.getHolder(
                    ResourceKey.create(
                        Registries.INSTRUMENT,
                        resourcelocation
                    )
                )
            }
        }

        val iterator = BuiltInRegistries.INSTRUMENT.getTagOrEmpty(instruments).iterator()
        return if (iterator.hasNext()) Optional.of(iterator.next()) else Optional.empty()
    }

    override fun getUseAnimation(stack: ItemStack): UseAnim {
        return UseAnim.TOOT_HORN
    }

    companion object {
        private const val TAG_INSTRUMENT = "instrument"
        fun create(item: Item, instrument: Holder<Instrument?>): ItemStack {
            val itemstack = ItemStack(item)
            setSoundVariantId(itemstack, instrument)
            return itemstack
        }

        private fun setSoundVariantId(stack: ItemStack, soundVariantId: Holder<Instrument?>) {
            val compoundtag = stack.getOrCreateTag()
            compoundtag.putString(
                "instrument",
                (soundVariantId.unwrapKey()
                    .orElseThrow(Supplier { IllegalStateException("Invalid instrument") }) as ResourceKey<*>).location()
                    .toString()
            )
        }

        private fun play(level: Level, player: Player, instrument: Instrument) {
            val soundevent = instrument.soundEvent().value() as SoundEvent
            val f = instrument.range() / 16.0f
            level.playSound(player, player, soundevent, SoundSource.RECORDS, f, 1.0f)
            level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player))
        }
    }
}