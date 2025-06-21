package dev.worldgen.deeper.oceans.worldgen;

import com.mojang.serialization.MapCodec;
import dev.worldgen.deeper.oceans.config.ConfigHandler;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;

import java.util.Arrays;

public record DepthMultiplier(double value) implements DensityFunction {
    public static MapCodec<DepthMultiplier> DATA_CODEC = MapCodec.unit(() -> new DepthMultiplier(
        ConfigHandler.getState().depthMultiplier
    ));

    public static KeyDispatchDataCodec<DepthMultiplier> CODEC_HOLDER = KeyDispatchDataCodec.of(DATA_CODEC);

    @Override
    public double compute(FunctionContext context) {
        return value;
    }

    @Override
    public void fillArray(double[] doubles, ContextProvider contextProvider) {
        Arrays.fill(doubles, value);
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(this);
    }

    @Override
    public double minValue() {
        return value;
    }

    @Override
    public double maxValue() {
        return value;
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC_HOLDER;
    }
}
