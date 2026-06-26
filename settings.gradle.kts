pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.msrandom.net/repository/cloche")
        maven("https://maven.msrandom.net/repository/root")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "hybrid-aquatic"
