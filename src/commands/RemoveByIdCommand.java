package commands;

import application.CollectionManager;
import data.Flat;

import java.util.ArrayDeque;

public class RemoveByIdCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public String execute(long id) {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        for (Flat flat : collection) {
            if (flat.getId() == id) {
                collection.remove();
                return ANSI_YELLOW + "Элемент с ID = " + id + " был удалён!\n" + ANSI_RESET;
            } else {
                return ANSI_RED + "Элемента с таким ID нет в коллекции. Попробуйте снова.\n"
                        + ANSI_RESET;
            }
        }
        return null;
    }
}


