package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.data.SeaMessage
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.Registries

class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add(Registries.ITEM_GROUP.getKey(HybridAquaticItemGroups.ALL).orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic")

        builder.add(HybridAquaticBlocks.ANEMONE, "Anemone")

        HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.translationKey.let { key ->
            builder.add(key, "Message in a Bottle")
            builder.add("$key.jar", "Message in a Jar")
            builder.add("$key.longneck", "Message in a Longneck Bottle")
        }

        SeaMessage.entries.forEach { message -> builder.add(message.translationKey, message.englishTranslation) }

        builder.add(HybridAquaticBlocks.BASKING_SHARK_BLAHAJ_PLUSHIE, "Basking Shark Blahaj Plushie")
        builder.add(HybridAquaticBlocks.BULL_SHARK_BLAHAJ_PLUSHIE, "Bull Shark Blahaj Plushie")
        builder.add(HybridAquaticBlocks.FRILLED_SHARK_BLAHAJ_PLUSHIE, "Frilled Shark Blahaj Plushie")
        builder.add(HybridAquaticBlocks.GREAT_WHITE_SHARK_BLAHAJ_PLUSHIE, "Great White Shark Blahaj Plushie")
        builder.add(HybridAquaticBlocks.HAMMERHEAD_SHARK_BLAHAJ_PLUSHIE, "Hammerhead Shark Blahaj Plushie")
        builder.add(HybridAquaticBlocks.THRESHER_SHARK_BLAHAJ_PLUSHIE, "Thresher Shark Blahaj Plushie")
        builder.add(HybridAquaticBlocks.TIGER_SHARK_BLAHAJ_PLUSHIE, "Tiger Shark Blahaj Plushie")
        builder.add(HybridAquaticBlocks.WHALE_SHARK_BLAHAJ_PLUSHIE, "Whale Shark Blahaj Plushie")
    }
}
