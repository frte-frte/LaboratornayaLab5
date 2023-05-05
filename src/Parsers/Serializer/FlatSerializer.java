package Parsers.Serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Flat;

import java.lang.reflect.Type;

public class FlatSerializer implements JsonSerializer<Flat> {

    @Override
    public JsonElement serialize(Flat src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty("id", src.getId());
        result.addProperty("name", src.getName());
        result.add("coordinates", context.serialize(src.getCoordinates()));
        result.addProperty("creationDate", String.valueOf(src.getCreationDate()));
        result.addProperty("area", src.getArea());
        result.addProperty("numberOfRooms" , src.getNumberOfRooms());
        result.addProperty("furnish", String.valueOf(src.getFurnish()));
        result.addProperty("view", String.valueOf(src.getView()));
        result.addProperty("transport", String.valueOf(src.getTransport()));
        result.add("house", context.serialize(src.getHouse()));

        return result;
    }
}

