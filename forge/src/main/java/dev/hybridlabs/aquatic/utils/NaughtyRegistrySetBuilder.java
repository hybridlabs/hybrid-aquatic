package dev.hybridlabs.aquatic.utils;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/** This subclasses RegistrySetBuilder and disables validating missing holders. Naughty! */
public class NaughtyRegistrySetBuilder extends RegistrySetBuilder {

    @Override
    public HolderLookup.@NotNull Provider buildPatch(
            @NotNull RegistryAccess registries, HolderLookup.@NotNull Provider lookup) {
        RegistrySetBuilder.BuildState state = createState(registries);
        Map<ResourceKey<? extends Registry<?>>, RegistryContents<?>> map = new HashMap<>();
        state.collectReferencedRegistries()
                .forEach((p_272339_) -> map.put(p_272339_.key(), p_272339_));
        this.entries.stream()
                .map((RegistryStub<?> stub) -> stub.collectChanges(state))
                .forEach((contents) -> map.put(contents.key(), contents));
        Stream<HolderLookup.RegistryLookup<?>> stream =
                registries.registries().map((entry) -> entry.value().asLookup());
        HolderLookup.Provider holderlookup$provider =
                HolderLookup.Provider.create(
                        Stream.concat(
                                stream,
                                map.values().stream()
                                        .map(RegistrySetBuilder.RegistryContents::buildAsLookup)
                                        .peek(state::addOwner)));
        state.fillMissingHolders(lookup);
        // don't validate missing holder values
        state.throwOnError();
        return holderlookup$provider;
    }
}
