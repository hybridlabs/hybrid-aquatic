package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.SeaMessage
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

        mapOf(
            "the_creepers_code" to "\"The creepers have a code...\"",
            "parrot_poison" to "\"Flint tried feeding parrots cookies earlier! They did not take. He coulda sworn they used to bite!\"\n*Steele",
            "lava_bathing" to "\"Steele went off and bathed in lava with my beautiful diamonds! That fiend! Least I've the time to myself to WRITE THIS COMPLAINT. STEELE.... STEALER! THIEF!\"\n@Flint",
            "fish_school" to "\"My underwater house flooded! Now it's just a school for fishies!\"\n~Oak",
            "loser" to "\"You opened the bottle!\nYou lose!\nVictim list - You!\"\n~Oak",
            "fart_bottle" to "\"Fart in a bottle! Gotcha!\nYou lose!\"\n@Flint",
            "tricked" to "\"I win! you fell for the classic ol' Message in a Bottle trick! I 'steele' the throne!\"\n*Steele",
            "marooned" to "\"marooned! beneath the night of a full moon (too)!\n(through the waves) which sound suggestions of lulling lays a gurgling\nit beckons me to take the plunge and swim (with him)\n(I) lay back down. tomorrow always comes.\n#Fischer #GlubGlub\"",
            "pumpkin_carving" to "\"Carve out a pumpkin and rely on your destiny!\"\n~Dean \"Ween\"",
        ).forEach { (id, translation) -> builder.add(SeaMessage(id).translationKey, translation) }

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
