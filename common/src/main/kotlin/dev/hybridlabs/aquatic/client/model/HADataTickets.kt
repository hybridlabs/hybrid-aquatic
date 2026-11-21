package dev.hybridlabs.aquatic.client.model

import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.constant.dataticket.DataTicket

/**
 * Model-local render state data that is only relevant to Hybrid Aquatic models. GeckoLib 5 models
 * only see a render state, so values an entity exposes for resource selection are captured in
 * [software.bernie.geckolib.model.GeoModel.addAdditionalStateData] and read back through these
 * tickets.
 */
object HADataTickets {
    /** The schooling fish count, for models that swap between one, two and three fish. */
    @JvmField
    val FISH_COUNT: DataTicket<Int> = DataTicket.create("fish_count", Int::class.java)

    /** The blowfish puff state, for models that grow with each puff. */
    @JvmField
    val PUFF_STATE: DataTicket<Int> = DataTicket.create("puff_state", Int::class.java)

    /** The decorator crab's remaining decoration timer. */
    @JvmField
    val CORAL_TIMER: DataTicket<Int> = DataTicket.create("coral_timer", Int::class.java)

    /** Whether an argonaut is currently glowing. */
    @JvmField
    val GLOWING: DataTicket<Boolean> = DataTicket.create("glowing", Boolean::class.java)

    /** The shell a hermit crab is carrying. */
    @JvmField
    val SHELL_ITEM: DataTicket<ItemStack> = DataTicket.create("shell_item", ItemStack::class.java)

    /** The lerped yaw of the entity controlling a mountable model's body. */
    @JvmField
    val BODY_YAW: DataTicket<Float> = DataTicket.create("body_yaw", Float::class.java)

    /** The lerped pitch of the entity controlling a mountable model's body. */
    @JvmField
    val BODY_TILT: DataTicket<Float> = DataTicket.create("body_tilt", Float::class.java)

    /** The overlay texture name an entity currently uses, or an empty string. */
    @JvmField
    val OVERLAY_NAME: DataTicket<String> = DataTicket.create("overlay_name", String::class.java)

    /** The tint color of an overlay texture layer, as an ARGB int. */
    @JvmField
    val OVERLAY_COLOR: DataTicket<Int> = DataTicket.create("overlay_color", Int::class.java)

    /** The carp pattern texture name, or an empty string. */
    @JvmField
    val PATTERN_NAME: DataTicket<String> = DataTicket.create("pattern_name", String::class.java)

    /** The orca eye spot texture name, or an empty string. */
    @JvmField
    val EYE_SPOT_NAME: DataTicket<String> = DataTicket.create("eye_spot_name", String::class.java)

    /** The orca saddle texture name, or an empty string. */
    @JvmField
    val SADDLE_NAME: DataTicket<String> = DataTicket.create("saddle_name", String::class.java)

    /** Whether a water animal is sitting, for models that only show an overlay while sitting. */
    @JvmField
    val SITTING: DataTicket<Boolean> = DataTicket.create("sitting", Boolean::class.java)

    /** The starfish base tint color, as an ARGB int. */
    @JvmField
    val STARFISH_COLOR: DataTicket<Int> = DataTicket.create("starfish_color", Int::class.java)

    /** The octopus' current body color, as an ARGB int. */
    @JvmField
    val CURRENT_COLOR: DataTicket<Int> = DataTicket.create("current_color", Int::class.java)

    /** The octopus' target body color, as an ARGB int. */
    @JvmField
    val TARGET_COLOR: DataTicket<Int> = DataTicket.create("target_color", Int::class.java)

    /** The serialized name of an argonaut's shell color. */
    @JvmField
    val SHELL_COLOR_NAME: DataTicket<String> = DataTicket.create("shell_color_name", String::class.java)

    /** The serialized name of an argonaut's sail color. */
    @JvmField
    val SAIL_COLOR_NAME: DataTicket<String> = DataTicket.create("sail_color_name", String::class.java)

    /** Whether the rendered entity is standing on the ground. */
    @JvmField
    val ON_GROUND: DataTicket<Boolean> = DataTicket.create("on_ground", Boolean::class.java)

    /** Whether an otter-like entity is in its floating action. */
    @JvmField
    val FLOATING: DataTicket<Boolean> = DataTicket.create("floating", Boolean::class.java)
}
