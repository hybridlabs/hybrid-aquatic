package dev.hybridlabs.aquatic.utils;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.*;
import net.minecraft.resources.ResourceKey;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** This subclasses RegistrySetBuilder and disables validating missing holders. Naughty! */
public class NaughtyRegistrySetBuilder extends RegistrySetBuilder {

    @Override
    public RegistrySetBuilder.PatchedRegistries buildPatch(RegistryAccess registryAccess, HolderLookup.Provider lookupProvider, Cloner.Factory clonerFactory) {
        RegistrySetBuilder.BuildState registrysetbuilder$buildstate = this.createState(registryAccess);
        Map<ResourceKey<? extends Registry<?>>, RegistrySetBuilder.RegistryContents<?>> map = new HashMap<>();
        this.entries
                .stream()
                .map(registryStub -> registryStub.collectRegisteredValues(registrysetbuilder$buildstate))
                .forEach(registryContents -> map.put(registryContents.key(), registryContents));
        Set<ResourceKey<? extends Registry<?>>> set = registryAccess.listRegistries().collect(Collectors.toUnmodifiableSet());
        lookupProvider.listRegistries()
                .filter(resourceKey -> !set.contains(resourceKey))
                .forEach(
                        resourceKey -> map.putIfAbsent(resourceKey,
                                new RegistrySetBuilder.RegistryContents<>(resourceKey, Lifecycle.stable(), Map.of())
                        )
                );
        Stream<HolderLookup.RegistryLookup<?>> stream =
                map.values().stream().map(registryContents -> registryContents.buildAsLookup(registrysetbuilder$buildstate.owner()));
        HolderLookup.Provider holderlookup$provider = buildProviderWithContext(registrysetbuilder$buildstate.owner(),
                registryAccess, stream);
        registrysetbuilder$buildstate.reportUnclaimedRegisteredValues();
        registrysetbuilder$buildstate.throwOnError();
        HolderLookup.Provider holderlookup$provider1 = this.createLazyFullPatchedRegistries(registryAccess, lookupProvider, clonerFactory, map, holderlookup$provider);
        return new RegistrySetBuilder.PatchedRegistries(holderlookup$provider1, holderlookup$provider);
    }
}
