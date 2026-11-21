package dev.hybridlabs.aquatic.client.render.block.entity

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import com.geckolib.constant.dataticket.DataTicket
import com.geckolib.renderer.base.GeoRenderState

/**
 * Block entity render state that also carries GeckoLib data.
 *
 * `BlockEntityRenderState` is only made a [GeoRenderState] at runtime by GeckoLib's interface
 * injection, which is not on the compile classpath of every module, so aquatic's block entity
 * renderers use this subclass. The data accessors are overridden as well so reads and writes always
 * hit the same map, even when the injected implementation also provides one.
 */
class HABlockEntityRenderState : BlockEntityRenderState(), GeoRenderState {

    private val geckolibData = HashMap<DataTicket<*>, Any>()

    override fun getDataMap(): MutableMap<DataTicket<*>, Any> = geckolibData

    override fun <D : Any> addGeckolibData(dataTicket: DataTicket<D>, data: D) {
        geckolibData[dataTicket] = data as Any
    }

    override fun <D : Any> getGeckolibData(dataTicket: DataTicket<D>): D? {
        @Suppress("UNCHECKED_CAST")
        return geckolibData[dataTicket] as D?
    }

    override fun hasGeckolibData(dataTicket: DataTicket<*>): Boolean = geckolibData.containsKey(dataTicket)
}
