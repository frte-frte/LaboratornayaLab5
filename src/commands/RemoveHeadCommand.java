package commands;

import application.CollectionManager;
import data.Flat;

import java.util.ArrayDeque;

public class RemoveHeadCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RESET = "\u001B[0m";

    public RemoveHeadCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public String execute() {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            return ANSI_YELLOW + "Коллекция пуста!\n" + ANSI_RESET;
        } else {
            Flat firstFlat = collection.getFirst();
            collection.pop();
            return firstFlat + "\n" + ANSI_YELLOW + "Первый элемент коллекции был удалён!\n" + ANSI_RESET;
        }
    }
}
