# Changelog for [Hybrid Aquatic](https://github.com/hybridlabs/hybrid-aquatic)

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

- Updated to Minecraft 1.21.11
- Updated to Minecraft 26.1.2
- Updated to Minecraft 26.2
- Updated to Minecraft 26.3
- Added diving armor upgrade templates to crate loot
- Jungle lily pads now break for modded boats as well

<details>
<summary>Developer notes</summary>

- GeckoLib 5.5 (`com.geckolib`) with the new item render-state API for held items and shells
- Villager and wandering trader trades moved to the data-driven trade registries
- Particle providers are registered through `ParticleResources` on Fabric, since vanilla no longer
  exposes a registry and Fabric removed its particle API
- Fluid appearance comes from `FluidModel` registrations on NeoForge and Fabric's
  `FluidRenderingRegistry`
- Block render layers are derived from model sprite transparency, so the explicit layer
  registrations were removed
- Carpet compatibility mixins were dropped: the 26.1 Carpet build ships an intermediary-namespace
  access widener that the no-remap toolchain rejects
- Migrated to GeckoLib 5: models, renderers and layers are driven by render states, and bone
  adjustments happen in the renderer instead of the model
- GeckoLib assets moved to `assets/<namespace>/geckolib/{models,animations}` and resource ids use
  the stripped form
- Coral and seashell gear now use the 1.21.11 tool and armor item API
- Custom bobber textures, painting variants and marine snow weather visuals are temporarily
  disabled while their vanilla renderers are reworked
- Trinkets support was compile-only in 1.21.11; there is no Trinkets release for 26.2
- Added a compatibility mixin for Lithostitched 1.7.2 biome injectors and a guard for missing
  GeckoLib animation names
- Features now carry their configuration directly; `ConfiguredFeature`, `FeatureConfiguration` and
  the separate configured feature registry were removed
- Biome surfaces use the new `MaterialRules` registry, and biome water fog moved from
  `BiomeSpecialEffects` to the environment attribute system
- Density function JSON uses `left`/`right`, `gradient` with an axis, and explicit interpolation
  cell sizes. Wrapper markers are now matched as direct density functions as well as registry
  holders
- Stripping and brewing recipes are data-driven: Fabric uses `BlockTransformerHelper`, NeoForge
  generates the `transformables` data map, and brewing recipes are emitted by datagen
- Swing state lives in `LivingEntity`; goals call `swingForAttack`, and custom swing durations use
  a `SwingAnimation`
- `PoseStack.mulPose` only accepts matrices and transformations
- Removed the Lithostitched 1.7.2 `ParameterMap` mixin; 2.0 fixed the issue upstream
- `debugutils` has no 26.3 build, so it is not loaded in Fabric dev runs

</details>

## [1.7.4] 2026-10-04

- Changed how some fish pathfind to the surface
- Fixed otters despawning even after being fed (hopefully)
- Fixed whale falls not generating properly
- Fixed trench incompatibility with Tectonic and Terralith
- Removed dependency on Biolith
- Made Trinkets/Curios optional

## [1.7.3] 2026-09-23

- Fixed small fish, such as the flying fish, becoming invisible at close range
- Fixed seashell and coral tools dealing no bonus attack damage and swinging at hand speed
- Fixed the armor and cosmetic headwear having no durability

## [1.7.2] 2026-09-15

- Fixed mineable tag issues
