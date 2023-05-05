package commands;

import application.CollectionManager;
import data.Flat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowCommand extends Command{
    public ShowCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public String execute() {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        StringBuilder showCommand = new StringBuilder();
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        List<Flat> sortCollection = new ArrayList<>();
        if (collection.isEmpty()) {
            return ANSI_YELLOW + "Коллекция пуста!\n" + ANSI_RESET;
        } else {
            sortCollection.addAll(collection);
            Collections.sort(sortCollection);
            for (Flat sortFlat : sortCollection) {
                showCommand.append(sortFlat.toString() + "\n");
            }
        }
        return showCommand.toString();
    }
}
