package dev.worldgen.deeper.oceans.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import com.mojang.serialization.JsonOps;
import dev.worldgen.deeper.oceans.DeeperOceans;
import net.minecraft.util.GsonHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;

public class ConfigHandler {
    private static ConfigState LOADED_STATE = new ConfigState(2.0, -20);
    private static Path PATH;

    public static ConfigState getState() {
        return LOADED_STATE;
    }

    public static void load(Path folder) {
        PATH = folder.resolve("deeper_oceans.json");

        if (!Files.isRegularFile(PATH)) {
            save();
        }

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            JsonElement json = JsonParser.parseReader(reader);
            Optional<ConfigState> result = ConfigState.CODEC.parse(JsonOps.INSTANCE, json).result();
            if (result.isPresent()) {
                LOADED_STATE = result.get();
            } else {
                throw new JsonParseException("Invalid codec");
            }
        } catch (JsonParseException e) {
            DeeperOceans.LOGGER.error("Couldn't parse config file, resetting to default config");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        save();
    }

    public static void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(PATH)) {
            JsonElement element = ConfigState.CODEC.encodeStart(JsonOps.INSTANCE, LOADED_STATE).result().get();
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setIndent("  ");
            GsonHelper.writeValue(jsonWriter, element, Comparator.naturalOrder());
            writer.write(stringWriter.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}