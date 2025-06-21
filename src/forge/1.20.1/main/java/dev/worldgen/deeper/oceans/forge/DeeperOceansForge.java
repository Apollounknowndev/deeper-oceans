package dev.worldgen.deeper.oceans.forge;

import dev.worldgen.deeper.oceans.DeeperOceans;
import dev.worldgen.deeper.oceans.config.ConfigHandler;
import dev.worldgen.deeper.oceans.worldgen.DepthMultiplier;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.RegisterEvent;

@Mod(DeeperOceans.MOD_ID)
public class DeeperOceansForge {
    public DeeperOceansForge() {
        ConfigHandler.load(FMLPaths.CONFIGDIR.get());
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener((RegisterEvent event) -> {
            event.register(Registries.DENSITY_FUNCTION_TYPE, helper -> {
                helper.register(DeeperOceans.id("depth_multiplier"), DepthMultiplier.DATA_CODEC.codec());
            });
        });
    }
}
