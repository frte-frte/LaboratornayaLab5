package Parsers.Serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesSerializer implements JsonSerializer <Coordinates> {

    @Override
    public JsonElement serialize(Coordinates src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty("x", src.getX());
        result.addProperty("y", src.getY());

        return result;
    }
}
