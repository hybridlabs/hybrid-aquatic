package dev.hybridlabs.aquatic.client.render

/**
 * Render state extension for entities riding an [dev.hybridlabs.aquatic.entity.misc.ArgonautEntity],
 * so the renderer can tilt the model with the vehicle's view pitch.
 */
interface ArgonautPassengerRenderState {
    /** The Argonaut's view pitch, or [Float.NaN] when the entity is not riding one. */
    var argonautPitch: Float
}
