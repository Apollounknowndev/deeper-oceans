package dev.worldgen.deeper.oceans.neoforge;

import dev.worldgen.deeper.oceans.DeeperOceans;
import dev.worldgen.deeper.oceans.config.ConfigHandler;
import dev.worldgen.deeper.oceans.worldgen.DepthMultiplier;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(DeeperOceans.MOD_ID)
public class DeeperOceansNeoforge {
    public DeeperOceansNeoforge(IEventBus bus) {
        ConfigHandler.load(FMLPaths.CONFIGDIR.get());

        bus.addListener((RegisterEvent event) -> {
            event.register(Registries.DENSITY_FUNCTION_TYPE, helper -> {
                helper.register(DeeperOceans.id("depth_multiplier"), DepthMultiplier.DATA_CODEC);
            });
        });
    }
}
