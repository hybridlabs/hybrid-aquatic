package dev.hybridlabs.aquatic

import com.llamalad7.mixinextras.MixinExtrasBootstrap
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint

object HybridAquaticPreLaunch: PreLaunchEntrypoint {
    override fun onPreLaunch() {
        // Initialize Mixin Extras
        MixinExtrasBootstrap.init();
    }
}