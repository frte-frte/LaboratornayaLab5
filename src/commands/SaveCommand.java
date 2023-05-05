package commands;

import Parsers.Serializer.CoordinatesSerializer;
import Parsers.Serializer.FlatSerializer;
import Parsers.Serializer.HouseSerializer;
import application.CollectionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Coordinates;
import data.Flat;
import data.House;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

public class SaveCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    public SaveCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public void execute() {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Flat.class, new FlatSerializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerializer())
                .registerTypeAdapter(House.class, new HouseSerializer())
                .create();

        String path = collectionManager.getFilePath();
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                String json = gson.toJson(collection);
                writer.write(json);
            System.out.println(ANSI_YELLOW + "Коллекция сохранена!" + ANSI_RESET);
        } catch (IOException exception){
            System.out.println(ANSI_RED + "Что-то не так с файлом..." + ANSI_RESET);
        }
    }
}
