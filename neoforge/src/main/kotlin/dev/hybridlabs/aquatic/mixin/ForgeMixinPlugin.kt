package dev.hybridlabs.aquatic.mixin

import net.neoforged.fml.loading.LoadingModList
import net.neoforged.neoforgespi.language.IModFileInfo
import org.objectweb.asm.tree.ClassNode
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
import org.spongepowered.asm.mixin.extensibility.IMixinInfo

class ForgeMixinPlugin: IMixinConfigPlugin {

    private val hasModernFix = isModInstalled("modernfix")

    override fun onLoad(mixinPackage: String) {
    }

    override fun getRefMapperConfig(): String? {
        return null
    }

    override fun shouldApplyMixin(targetClassName: String, mixinClassName: String): Boolean {
        if (hasModernFix && mixinClassName.contains("client.WeatherDisplayMixin")) return false
        return true
    }

    override fun acceptTargets(
        myTargets: Set<String>,
        otherTargets: Set<String>
    ) {
    }

    override fun getMixins(): List<String>? {
        return null
    }

    override fun preApply(
        targetClassName: String,
        targetClass: ClassNode,
        mixinClassName: String,
        mixinInfo: IMixinInfo?
    ) {
    }

    override fun postApply(
        targetClassName: String,
        targetClass: ClassNode,
        mixinClassName: String,
        mixinInfo: IMixinInfo
    ) {
    }

    fun isModInstalled(modId: String): Boolean {
        val info: IModFileInfo? = LoadingModList.get().getModFileById(modId)
        return info != null && !info.mods.isEmpty()
    }
}