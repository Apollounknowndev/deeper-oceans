package dev.worldgen.deeper.oceans;

import net.minecraft.resources.ResourceLocation;
import net.msrandom.multiplatform.annotations.Expect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeeperOceans {
    public static final String MOD_ID = "deeper_oceans";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /**
     * Creates a {@code ResourceLocation} with the {@code MOD_ID} namespace.
     */
    @Expect
    public static ResourceLocation id(String name);
}
