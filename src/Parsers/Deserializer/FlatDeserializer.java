package Parsers.Deserializer;

import com.google.gson.*;
import data.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlatDeserializer implements JsonDeserializer <Flat> {
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    @Override
    public Flat deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = new JsonObject();

        List<Integer> idList = new ArrayList<>();
        Integer id;
        try {
            id = jsonObject.get("id").getAsInt();
        } catch (NumberFormatException exception) {
            id = null;
        }
        for (int i = 0; i < idList.size(); i++) {
            if (id == null || id <= 0 || id == idList.get(i)) {
                System.out.println(ANSI_RED + "Неверный ID в файле" + ANSI_RESET);
                System.exit(0);
            }
        }
        idList.add(id);

        String name;
        try {
            name = jsonObject.get("name").getAsString();
        } catch (NumberFormatException exception) {
            name = null;
        }
        if (name == null || name.equals("") || name.equals(" ")) {
            System.out.println(ANSI_RED + "Неверное название квартиры в файле" +ANSI_RESET);
            System.exit(0);
        }

        Coordinates coordinates = context.deserialize(jsonObject.get("coordinates"), Coordinates.class);

        LocalDate creationDate = context.deserialize(jsonObject.get("creationDate"), LocalDate.class);

        Double area;
        try {
            area = jsonObject.get("area").getAsDouble();
        } catch (NumberFormatException exception) {
            area = null;
        }
        if (area == null || area <= 0 || area > 904) {
            System.out.println(ANSI_RED + "Неверная площадь в файле" + ANSI_RESET);
            System.exit(0);
        }

        Long numberOfRooms;
        try {
            numberOfRooms = jsonObject.get("numberOfRooms").getAsLong();
        } catch (NumberFormatException exception) {
            numberOfRooms = null;
        }
        if (numberOfRooms == null || numberOfRooms <= 0) {
            System.out.println(ANSI_RED + "Неверное количество комнат в файле" + ANSI_RESET);
            System.exit(0);
        }

        String stringFurnish = jsonObject.get("furnish").getAsString();
        if (!stringFurnish.equals("DESIGNER") && !stringFurnish.equals("NONE") && !stringFurnish.equals("FINE")
                && !stringFurnish.equals("BAD") && !stringFurnish.equals("LITTLE")) {
            System.out.println(ANSI_RED + "Неверный тип мебели в файле" + ANSI_RESET);
            System.exit(0);
        }
        Furnish furnish = Furnish.valueOf(stringFurnish);

        String stringView = jsonObject.get("view").getAsString();
        if (!stringView.equals("STREET") && !stringView.equals("PARK") && !stringView.equals("NORMAL")
                && !stringView.equals("GOOD") && !stringView.equals("TERRIBLE")) {
            System.out.println(ANSI_RED + "Неверный тип вида в файле" + ANSI_RESET);
            System.exit(0);
        }
        View view = View.valueOf(stringView);

        String stringTransport = jsonObject.get("transport").getAsString();
        if (!stringTransport.equals("FEW") && !stringTransport.equals("NONE") && !stringTransport.equals("LITTLE")
                && !stringTransport.equals("NORMAL") && !stringTransport.equals("ENOUGH")) {
            System.out.println(ANSI_RED + "Неверный тип транспорта в файле" + ANSI_RESET);
            System.exit(0);
        }
        Transport transport = Transport.valueOf(stringTransport);

        House house = context.deserialize(jsonObject.get("house"), House.class);

        return new Flat(id, name, coordinates, creationDate, area, numberOfRooms, furnish, view, transport, house);
    }
}
