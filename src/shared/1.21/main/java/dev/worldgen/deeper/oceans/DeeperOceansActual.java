package dev.worldgen.deeper.oceans;

import net.minecraft.resources.ResourceLocation;
import net.msrandom.multiplatform.annotations.Actual;

public class DeeperOceansActual {
    @Actual
    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(DeeperOceans.MOD_ID, name);
    }
}
