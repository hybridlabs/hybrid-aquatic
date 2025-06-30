package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.GhostCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.GhostCrabEntity
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GhostCrabEntityRenderer(context: Context) : HybridAquaticCrustaceanEntityRenderer<GhostCrabEntity>(context, GhostCrabEntityModel(), true, false)