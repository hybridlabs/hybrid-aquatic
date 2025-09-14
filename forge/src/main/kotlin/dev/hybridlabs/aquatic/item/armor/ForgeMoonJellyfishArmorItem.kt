package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.client.render.armor.MoonJellyfishArmorRenderer
import dev.hybridlabs.aquatic.item.MoonJellyfishArmorItem
import net.minecraft.client.model.HumanoidModel
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraftforge.client.extensions.common.IClientItemExtensions
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.renderer.GeoArmorRenderer
import java.util.function.Consumer

class ForgeMoonJellyfishArmorItem(type: Type, settings: Properties) :
    MoonJellyfishArmorItem(type, settings), GeoItem {


    override fun initializeClient(consumer: Consumer<IClientItemExtensions?>) {
        consumer.accept(object : IClientItemExtensions {
            private var renderer: GeoArmorRenderer<*>? = null

            override fun getHumanoidArmorModel(
                livingEntity: LivingEntity?,
                itemStack: ItemStack?,
                equipmentSlot: EquipmentSlot?,
                original: HumanoidModel<*>?
            ): HumanoidModel<*> {
                if (this.renderer == null) this.renderer = MoonJellyfishArmorRenderer()

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer!!.prepForRender(livingEntity, itemStack, equipmentSlot, original)

                return this.renderer!!
            }
        })
    }
}
