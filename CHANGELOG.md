# Changelog for [Hybrid Aquatic](https://github.com/hybridlabs/hybrid-aquatic)

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.7.5] 2026-11-27
- Fixed the config file not being created on NeoForge
- Fixed worldgen feature options not applying without rebuilding the mod
- Added config options for coral reef, tropical deep coral reef and all trench biomes
- Malformed or outdated config files are now backed up before being reset
- Fixed the deep warm ocean config option using an incorrect key
- Fixed a crash when wandering traders generated with unbound items
- Fixed fish heads and tails spinning
- Fixed missing blockstates and models for anemones, plushies, message in a bottle, buoy and bell buoy
- Fixed plushie models being recreated repeatedly, which caused EMF errors
- Fixed sharks not targeting bleeding entities
- Fixed sharks not inflicting bleeding on attack
- Fixed sharks not dropping teeth when blocked with a shield
- Added the missing brine translation
- Moved shared config handling to Hybrid API
- Changed jungle lilypads to break on contact with anything in the "boats" tag
- Added Diving Armor Upgrade Templates to crate loot

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
