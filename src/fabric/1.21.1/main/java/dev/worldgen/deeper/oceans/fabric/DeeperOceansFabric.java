package dev.worldgen.deeper.oceans.fabric;

import dev.worldgen.deeper.oceans.DeeperOceans;
import dev.worldgen.deeper.oceans.config.ConfigHandler;
import dev.worldgen.deeper.oceans.worldgen.DepthMultiplier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class DeeperOceansFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigHandler.load(FabricLoader.getInstance().getConfigDir());

        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, DeeperOceans.id("depth_multiplier"), DepthMultiplier.DATA_CODEC);
    }
}
