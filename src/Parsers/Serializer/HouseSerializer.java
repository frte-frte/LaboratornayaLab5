package Parsers.Serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.House;

import java.lang.reflect.Type;

public class HouseSerializer implements JsonSerializer <House> {
    @Override
    public JsonElement serialize(House src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty("name", src.getName());
        result.addProperty("year", src.getYear());
        result.addProperty("numberOfFlatsOnFloor", src.getNumberOfFlatsOnFloor());

        return result;
    }
}
