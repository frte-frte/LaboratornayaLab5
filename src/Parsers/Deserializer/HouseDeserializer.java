package Parsers.Deserializer;

import com.google.gson.*;
import data.House;

import java.lang.reflect.Type;

public class HouseDeserializer implements JsonDeserializer<House> {
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    @Override
    public House deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name;
        try {
            name = jsonObject.get("name").getAsString();
        } catch (NumberFormatException exception) {
            name = null;
        }
        if (name == null || name.equals("") || name.equals(" ")) {
            System.out.println(ANSI_RED + "Неверное название дома в файле" + ANSI_RESET);
            System.exit(0);
        }

        Integer year;
        try {
            year = jsonObject.get("year").getAsInt();
        } catch (NumberFormatException exception) {
            year = null;
        }
        if (year == null || year <= 0) {
            System.out.println(ANSI_RED + "Неверный возраст дома в файле" + ANSI_RESET);
            System.exit(0);
        }

        Long numberOfFlatsOnFloor;
        try {
            numberOfFlatsOnFloor = jsonObject.get("numberOfFlatsOnFloor").getAsLong();
        } catch (NumberFormatException exception) {
            numberOfFlatsOnFloor = null;
        }
        if (numberOfFlatsOnFloor == null || numberOfFlatsOnFloor <= 0) {
            System.out.println(ANSI_RED + "Неверное количество квартир на этаже в файле");
            System.exit(0);
        }
        return new House(name, year, numberOfFlatsOnFloor);
    }
}
