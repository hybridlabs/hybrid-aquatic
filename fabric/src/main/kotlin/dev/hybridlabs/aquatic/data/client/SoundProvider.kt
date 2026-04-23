package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.data.builder.FabricSoundsProvider
import dev.hybridlabs.aquatic.data.builder.SoundTypeBuilder
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

class SoundProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
): FabricSoundsProvider(output, registriesFuture) {

    override fun configure(exporter: SoundExporter) {
        addSound(exporter, "item.ominous_conch.blows",
            HASoundEvents.OMINOUS_CONCH_BLOWS,
            CommonClass.locate("item/ominous_conch_blows")
        )

        addEntity(exporter, "manatee",
            HASoundEvents.MANATEE_AMBIENT, CommonClass.locate("entity/sirenian_ambient"),
            HASoundEvents.MANATEE_DIE, CommonClass.locate("entity/sirenian_hurt"),
            HASoundEvents.MANATEE_HURT, CommonClass.locate("entity/sirenian_die"),
            HASoundEvents.MANATEE_SWIM, CommonClass.locate("entity/sirenian_swim"),
            HASoundEvents.MANATEE_SPLASH, CommonClass.locate("entity/sirenian_splash")
        )

        addEntity(exporter, "dugong",
            HASoundEvents.DUGONG_AMBIENT, CommonClass.locate("entity/sirenian_ambient"),
            HASoundEvents.DUGONG_DIE, CommonClass.locate("entity/sirenian_hurt"),
            HASoundEvents.DUGONG_HURT, CommonClass.locate("entity/sirenian_die"),
            HASoundEvents.DUGONG_SWIM, CommonClass.locate("entity/sirenian_swim"),
            HASoundEvents.DUGONG_SPLASH, CommonClass.locate("entity/sirenian_splash")
        )

        addEntity(exporter, "karkinos",
            HASoundEvents.KARKINOS_AMBIENT, CommonClass.locate("entity/karkinos_ambient"),
            HASoundEvents.KARKINOS_DIE, CommonClass.locate("entity/karkinos_die"),
            HASoundEvents.KARKINOS_HURT, CommonClass.locate("entity/karkinos_hurt"),
        )

        addEntity(exporter, "karcinoma",
            HASoundEvents.KARCINOMA_AMBIENT, SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.KARCINOMA_DIE, SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.KARCINOMA_HURT, SoundEvents.TURTLE_EGG_CRACK,
        )

        addEntity(exporter, "karcinogen",
            HASoundEvents.KARCINOGEN_AMBIENT, SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.KARCINOGEN_DIE, SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.KARCINOGEN_HURT, SoundEvents.TURTLE_EGG_CRACK,
        )

        addEntity(exporter, "shell_beast",
            HASoundEvents.SHELL_BEAST_AMBIENT, CommonClass.locate("entity/shell_beast_ambient"),
            HASoundEvents.SHELL_BEAST_DIE, CommonClass.locate("entity/shell_beast_die"),
            HASoundEvents.SHELL_BEAST_HURT, CommonClass.locate("entity/shell_beast_hurt"),
        )
        addEntitySound(exporter,
            "shell_beast", "shoot",
            HASoundEvents.SHELL_BEAST_SHOOT,
            CommonClass.locate("entity/shell_beast_shoot"))

        addEntity(exporter, "hypnautilus",
            HASoundEvents.HYPNAUTILUS_AMBIENT, SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.HYPNAUTILUS_DIE, SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.HYPNAUTILUS_HURT, SoundEvents.TURTLE_EGG_CRACK,
        )
    }

    fun addEntity(exporter: SoundExporter, id: String,
                  ambient: Supplier<SoundEvent>, ambientAudio: ResourceLocation? = null,
                  die: Supplier<SoundEvent>, dieAudio: ResourceLocation? = null,
                  hurt: Supplier<SoundEvent>, hurtAudio: ResourceLocation? = null,
                  swim: Supplier<SoundEvent>? = null, swimAudio: ResourceLocation? = null,
                  splash: Supplier<SoundEvent>? = null, splashAudio: ResourceLocation? = null
    ) {
        mapOf(
            ("ambient" to ambientAudio) to ambient,
            ("die" to dieAudio) to die,
            ("hurt" to hurtAudio) to hurt,
            ("swim" to swimAudio) to swim,
            ("splash" to splashAudio) to splash
        ).forEach { (path, sound) ->
            if(sound == null || path.second == null) return@forEach
            addEntitySound(exporter, id, path.first, sound, path.second!!)
        }
    }

    fun addEntity(exporter: SoundExporter, id: String,
                  ambient: Supplier<SoundEvent>, ambientAudio: SoundEvent? = null,
                  die: Supplier<SoundEvent>, dieAudio: SoundEvent? = null,
                  hurt: Supplier<SoundEvent>, hurtAudio: SoundEvent? = null,
                  swim: Supplier<SoundEvent>? = null, swimAudio: SoundEvent? = null,
                  splash: Supplier<SoundEvent>? = null, splashAudio: SoundEvent? = null
    ) {
        mapOf(
            ("ambient" to ambientAudio) to ambient,
            ("die" to dieAudio) to die,
            ("hurt" to hurtAudio) to hurt,
            ("swim" to swimAudio) to swim,
            ("splash" to splashAudio) to splash
        ).forEach { (path, sound) ->
            if(sound == null || path.second == null) return@forEach
            addEntitySound(exporter, id, path.first, sound, path.second!!)
        }
    }

    fun addEntitySound(
        exporter: SoundExporter,
        entityId: String,
        path: String,
        soundEvent: Supplier<SoundEvent>,
        soundLocation: SoundEvent
    ) {
        addSound(exporter, "entity.${entityId}.$path", soundEvent, soundLocation)
    }

    fun addEntitySound(
        exporter: SoundExporter,
        entityId: String,
        path: String,
        soundEvent: Supplier<SoundEvent>,
        soundLocation: ResourceLocation
    ) {
        addSound(exporter, "entity.${entityId}.$path", soundEvent, soundLocation)
    }

    fun addSound(
        exporter: SoundExporter,
        subtitle: String,
        soundEvent: Supplier<SoundEvent>,
        soundLocation: ResourceLocation
    ) {
        exporter.add(soundEvent.get(), SoundTypeBuilder.of(soundEvent.get())
            .subtitle("sounds.${Constants.MOD_ID}.$subtitle")
            .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(soundLocation))
        )
    }

    fun addSound(
        exporter: SoundExporter,
        subtitle: String,
        soundEvent: Supplier<SoundEvent>? = null,
        soundLocation: SoundEvent? = null
    ) {
        if (soundEvent != null) {

            exporter.add(soundEvent.get(), SoundTypeBuilder.of(soundEvent.get())
                .subtitle("sounds.${Constants.MOD_ID}.$subtitle")
                .sound(SoundTypeBuilder.RegistrationBuilder.ofEvent(soundLocation))
            )
        }
    }

    override fun getName(): String {
        return "Hybrid Aquatic sound events"
    }

}