import earth.terrarium.cloche.api.metadata.CommonMetadata.Environment
import earth.terrarium.cloche.tasks.GenerateForgeModsToml

plugins {
    id("earth.terrarium.cloche") version "0.18.15"
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.changelog") version "2.2.1"
}

group = "dev.hybridlabs"
version = "1.5.4"

repositories {
    cloche.librariesMinecraft()
    mavenCentral()
    cloche {
        main()
        mavenFabric()
        mavenForge()
        mavenParchment()
    }
    maven("https://maven.terraformersmc.com/releases/")
    maven("https://jitpack.io/")
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://maven.jamieswhiteshirt.com/libs-release/")
    maven("https://api.modrinth.com/maven/")
    maven("https://gitlab.com/api/v4/projects/21830712/packages/maven/")
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

java { toolchain.languageVersion.set(JavaLanguageVersion.of(17)) }

cloche {
    metadata {
        modId = "hybrid-aquatic"
        name = "Hybrid Aquatic"
        description = "A mod that enhances your underwater Minecraft experience! Explore improved rivers, swamps, oceans, and undersea caves with new creatures, blocks, and (coming soon) biomes."
        license = "ARR"
        icon = "assets/hybrid-aquatic/icon.png"
        url = "https://github.com/hybridlabs/hybrid-aquatic"
        sources = "https://github.com/hybridlabs/hybrid-aquatic"
        issues = "https://github.com/hybridlabs/hybrid-aquatic/issues"
        author("MysticKoko"); author("Aqua"); author("Andante")
        author("Eggo"); author("Ragno"); author("murphy slaw")
    }

    minecraftVersion = "1.20.1"

    common {
        accessWideners.from("src/common/main/resources/hybrid-aquatic.accesswidener")
        dependencies {
            compileOnly("org.jetbrains:annotations:25.0.0")
            compileOnly("io.github.llamalad7:mixinextras-common:0.5.0")
            // Mixin annotations (@Mixin, @Inject, etc.) are used by src/common/main/java mixin
            // classes, but the common target's compileClasspath doesn't get the Mixin jar that
            // fabric{}/forge{} pull in via their mixins.from(...) wiring. Without this, compileJava
            // fails with "class file for org.spongepowered.asm.mixin.injection.Constant not found".
            compileOnly("org.spongepowered:mixin:0.8.5")
        }
    }

    fabric {
        loaderVersion = "0.18.2"

        mappings { official(); parchment("2023.09.03") }

        accessWideners.from("src/common/main/resources/hybrid-aquatic.accesswidener")
        mixins.from("src/fabric/main/resources/hybrid-aquatic.fabric.mixins.json")

        metadata {
            environment = Environment.Both
            entrypoint("main")     { value = "dev.hybridlabs.aquatic.HybridAquatic";              adapter = "kotlin" }
            entrypoint("client")   { value = "dev.hybridlabs.aquatic.HybridAquaticClient";        adapter = "kotlin" }
            entrypoint("fabric-datagen") { value = "dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator"; adapter = "kotlin" }
            entrypoint("preLaunch") { value = "com.llamalad7.mixinextras.MixinExtrasBootstrap::init" }

            dependency {
                modId.set("minecraft")
                version { start.set("1.20.1"); end.set("1.21"); endExclusive.set(true) }
            }
            require("java", "17")
            require("fabric-language-kotlin", "0.0.0")
            require("fabric-api", "0.92.0")
            require("geckolib", "4.4.0")
            require("lithostitched", "1.4.11")
            custom("loom:injected_interfaces", mapOf(
                "net/minecraft/class_1657" to listOf("dev/hybridlabs/aquatic/access/CustomPlayerEntityData")
            ))
        }

        dependencies {
            fabricApi("0.92.6")
            modImplementation("net.fabricmc:fabric-language-kotlin:1.13.2+kotlin.2.1.20")
            modImplementation("software.bernie.geckolib:geckolib-fabric-1.20.1:4.8.2")
            modApi("com.terraformersmc:modmenu:7.2.2")
            modImplementation("maven.modrinth:lithostitched:1.4.11-fabric-1.20")
            // reach-entity-attributes: on the mod classpath AND jar-in-jar'd (old build used `include modImplementation(...)`)
            modImplementation("com.jamieswhiteshirt:reach-entity-attributes:2.4.0")
            include("com.jamieswhiteshirt:reach-entity-attributes:2.4.0")
            modRuntimeOnly("io.github.flemmli97:debugutils:1.20.1-1.0.5-fabric")
            modRuntimeOnly("maven.modrinth:carpet:1.4.112")
            modRuntimeOnly("maven.modrinth:cyanide:4.1.1-fabric")
            // MixinExtras runtime support (e.g. @ModifyExpressionValue) used by common/fabric mixins;
            // bundle it in the fabric jar since Fabric Loader doesn't provide it like NeoForge/Forge do.
            include("io.github.llamalad7:mixinextras-fabric:0.5.0")
        }

        // All client code lives in the main source set (no separate client sourceset).
        includedClient()

        runs { client(); server() }
    }

    forge {
        loaderVersion = "47.4.10"

        mappings { official(); parchment("2023.09.03") }

        accessWideners.from("src/common/main/resources/hybrid-aquatic.accesswidener")
        mixins.from("src/forge/main/resources/hybrid-aquatic.forge.mixins.json")

        metadata {
            modLoader = "kotlinforforge"
            loaderVersion("4")           // KotlinForForge language-loader range -> [4,)
            require("forge", "47.4.10")
            dependency {
                modId.set("minecraft")
                version { start.set("1.20.1"); end.set("1.22"); endExclusive.set(true) }
            }
            require("geckolib", "4.7.4")
            require("lithostitched", "1.4.11", environment = Environment.Server)
        }

        dependencies {
            modImplementation("software.bernie.geckolib:geckolib-forge-1.20.1:4.8.2")
            modImplementation("maven.modrinth:lithostitched:1.4.11-forge-1.20")
            implementation("thedarkcolour:kotlinforforge:4.11.0")
            compileOnly("io.github.llamalad7:mixinextras-common:0.5.0")
            include("io.github.llamalad7:mixinextras-forge:0.5.0")
        }

        runs { client(); server() }
    }
}

// Forge rejects hyphens in mod ids, and the Forge source declares
// @Mod(Constants.FORGE_MOD_ID) where FORGE_MOD_ID = "hybrid_aquatic". Cloche
// defaults the generated mods.toml modId to the root modId ("hybrid-aquatic")
// with no sanitization, so override it (this also keys the [[dependencies.<id>]]
// table). Root modId stays "hybrid-aquatic" for Fabric + the resource namespace.
tasks.withType<GenerateForgeModsToml>().configureEach {
    modId.set("hybrid_aquatic")
}

// Ship the committed datagen output in the jars. In the old build the Fabric
// datagen output (src/fabric/main/generated) was bundled into the Fabric jar AND
// reused by the Forge jar; Forge additionally shipped its own generated data
// (src/forge/main/generated). Replicate that exactly by adding these as resource
// source dirs. (Datagen *regeneration* via Cloche `data()` runs is a deferred
// follow-up; the committed output here matches the captured baseline.)
sourceSets {
    named("fabric") {
        resources.srcDir("src/fabric/main/generated")
    }
    named("forge") {
        resources.srcDir("src/fabric/main/generated")
        resources.srcDir("src/forge/main/generated")
    }
}

// common + fabric resource sets can contribute overlapping paths (e.g. shared
// namespaces); EXCLUDE duplicates rather than fail the build.
tasks.withType<org.gradle.language.jvm.tasks.ProcessResources>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

changelog {
    version.set(project.version.toString())
    path.set(file("CHANGELOG.md").canonicalPath)
    groups.set(listOf("Added", "Changed", "Deprecated", "Removed", "Fixed", "Security"))
    lineSeparator.set("\n")
}
