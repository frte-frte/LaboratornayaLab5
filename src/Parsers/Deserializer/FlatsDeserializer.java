package Parsers.Deserializer;

import com.google.gson.*;
import data.Coordinates;
import data.Flat;
import data.House;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayDeque;

public class FlatsDeserializer implements JsonDeserializer<ArrayDeque<Flat>> {
    @Override
    public ArrayDeque<Flat> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayDeque<Flat> collection = new ArrayDeque<>();
        JsonArray jsonArray = json.getAsJsonArray();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(House.class, new HouseDeserializer())
                .create();
        for (JsonElement jsonElement : jsonArray) {
            Flat flat = gson.fromJson(jsonElement, Flat.class);
            collection.add(flat);
        }
        return collection;
    }
}
