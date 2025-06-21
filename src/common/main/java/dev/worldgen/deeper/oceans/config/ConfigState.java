package dev.worldgen.deeper.oceans.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ConfigState {
    public static final Codec<ConfigState> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.DOUBLE.fieldOf("depth_multiplier").forGetter(state -> state.depthMultiplier),
        Codec.INT.fieldOf("monument_offset").forGetter(state -> state.monumentOffset)
    ).apply(instance, ConfigState::new));

    public double depthMultiplier;
    public int monumentOffset;

    public ConfigState(double depthMultiplier, int monumentOffset) {
        this.depthMultiplier = depthMultiplier;
        this.monumentOffset = monumentOffset;
    }
}
