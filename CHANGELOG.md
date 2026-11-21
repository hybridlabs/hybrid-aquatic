# Changelog for [Hybrid Aquatic](https://github.com/hybridlabs/hybrid-aquatic)

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

- Updated to Minecraft 1.21.11
- Updated to Minecraft 26.1.2

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
- Trinkets support is compile-only for this release
- Added a compatibility mixin for Lithostitched 1.7.2 biome injectors and a guard for missing
  GeckoLib animation names

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
