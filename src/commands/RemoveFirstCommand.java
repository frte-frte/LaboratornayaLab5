package commands;

import application.CollectionManager;
import data.Flat;

import java.util.ArrayDeque;

public class RemoveFirstCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RESET = "\u001B[0m";

    public RemoveFirstCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public String execute() {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            return ANSI_YELLOW + "Коллекция пуста!\n" + ANSI_RESET;
        } else {
            collection.removeFirst();
            return ANSI_YELLOW + "Первый элемент коллекции был удалён!\n" + ANSI_RESET;
        }
    }
}
