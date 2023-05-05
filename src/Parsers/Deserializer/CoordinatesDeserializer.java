package Parsers.Deserializer;


import com.google.gson.*;
import data.Coordinates;

import java.lang.reflect.Type;


public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    @Override
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Long x;
        try {
            x = jsonObject.get("x").getAsLong();
        } catch (NumberFormatException exception) {
            x = null;
        }
        if (x == null || x <= -970) {
            System.out.println(ANSI_RED + "Неверный формат X в файле!" + ANSI_RESET);
            System.exit(0);
        }
        Integer y;
        try {
            y = jsonObject.get("y").getAsInt();
        } catch (NumberFormatException exception) {
            y = null;
        }
        if (y == null || y > 113) {
            System.out.println(ANSI_RED + "Неверный формат Y в файле!" + ANSI_RESET);
            System.exit(0);
        }
        return new Coordinates(x, y);
    }
}
