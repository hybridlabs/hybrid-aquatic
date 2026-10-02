package dev.hybridlabs.aquatic.world.gen.biome

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher
import dev.hybridlabs.aquatic.world.gen.biome.HABiomePlacement.Criterion

/**
 * Registers the mod's biome placement with Biolith. Only loaded when Biolith is installed.
 */
object BiolithPlacement {
    fun register(builder: HABiomePlacement.Builder) {
        for ((target, requests) in builder.replacements) {
            for (request in requests) {
                BiomePlacement.replaceOverworld(target, request.biome, request.rate)
            }
        }

        for ((target, requests) in builder.subBiomes) {
            for (request in requests) {
                val criteria = request.criteria.map(::criterion).toTypedArray()
                BiomePlacement.addSubOverworld(target, request.biome, SubBiomeMatcher.of(*criteria))
            }
        }
    }

    private fun criterion(criterion: Criterion): SubBiomeMatcher.Criterion = when (criterion) {
        is Criterion.Neighbor -> if (criterion.biome != null) {
            SubBiomeMatcher.Criterion.ofBiome(SubBiomeMatcher.CriterionTargets.NEIGHBOR, criterion.biome, false)
        } else {
            SubBiomeMatcher.Criterion.ofBiome(SubBiomeMatcher.CriterionTargets.NEIGHBOR, criterion.tag, false)
        }

        is Criterion.Edge -> SubBiomeMatcher.Criterion.ofRange(
            SubBiomeMatcher.CriterionTargets.EDGE,
            SubBiomeMatcher.CriterionTypes.RATIO,
            criterion.min,
            criterion.max,
            false
        )

        is Criterion.Continentalness -> SubBiomeMatcher.Criterion.ofRange(
            SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
            SubBiomeMatcher.CriterionTypes.VALUE,
            criterion.min,
            criterion.max,
            false
        )

        is Criterion.Depth -> SubBiomeMatcher.Criterion.ofRange(
            SubBiomeMatcher.CriterionTargets.DEPTH,
            SubBiomeMatcher.CriterionTypes.VALUE,
            criterion.min,
            criterion.max,
            false
        )
    }
}
