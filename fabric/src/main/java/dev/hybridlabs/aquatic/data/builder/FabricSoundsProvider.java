package dev.hybridlabs.aquatic.data.builder;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class FabricSoundsProvider implements DataProvider {
	private static final Codec<Map<String, SoundTypeBuilderImpl.SoundType>> CODEC = Codec.unboundedMap(Codec.STRING, SoundTypeBuilderImpl.SoundType.CODEC);
	private final CompletableFuture<HolderLookup.Provider> registriesFuture;
	private final PackOutput output;
	
	public FabricSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		this.registriesFuture = registriesFuture;
		this.output = output;
	}
	
	@Override
	public CompletableFuture<?> run(CachedOutput output) {
		return registriesFuture.thenCompose(provider -> {
			final Map<String, Map<String, SoundTypeBuilderImpl.SoundType>> data = new LinkedHashMap<>();
			configure((id, builder) -> {
				if (data.computeIfAbsent(id.getNamespace(), n -> new LinkedHashMap<>()).put(id.getPath(), ((SoundTypeBuilderImpl) builder).build()) != null) {
					throw new IllegalStateException("Duplicate sound for event " + id);
				}
			});
			
			return CompletableFuture.allOf(data.entrySet().stream().map(file -> {
				Path outputPath = this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(file.getKey() + "/sounds.json");
				
				return saveStable(output, provider, CODEC, file.getValue(), outputPath);
			}).toArray(CompletableFuture[]::new));
		});
	}
	
	protected abstract void configure(SoundExporter exporter);
	
	@ApiStatus.NonExtendable
	@FunctionalInterface
	public interface SoundExporter {
		
		default void add(SoundEvent event, SoundTypeBuilder builder) {
			add(event.getLocation(), builder);
		}
		
		default void add(Holder<SoundEvent> event, SoundTypeBuilder builder) {
			add(event.unwrapKey().orElseThrow(() -> new IllegalArgumentException("Direct (non-registered) sound event cannot be added")).location(), builder);
		}
		
		void add(ResourceLocation id, SoundTypeBuilder builder);
	}
	
	static <T> CompletableFuture<?> saveStable(
			final CachedOutput cache,
			final HolderLookup.Provider registries,
			final Codec<T> codec,
			final T value,
			final Path path) {
		RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registries);
		return saveStable(cache, ops, codec, value, path);
	}
	
	static <T> CompletableFuture<?> saveStable(
			final CachedOutput cache,
			final Codec<T> codec,
			final T value,
			final Path path
	) {
		return saveStable(cache, JsonOps.INSTANCE, codec, value, path);
	}
	
	private static <T> CompletableFuture<?> saveStable(
			final CachedOutput cache,
			final DynamicOps<JsonElement> ops,
			final Codec<T> codec,
			final T value,
			final Path path
	) {
		JsonElement json = codec.encodeStart(ops, value).getOrThrow();
		return DataProvider.saveStable(cache, json, path);
	}
}
