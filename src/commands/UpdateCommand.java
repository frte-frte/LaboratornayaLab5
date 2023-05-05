package commands;

import application.CollectionManager;
import data.Flat;

import java.util.ArrayDeque;

public class UpdateCommand extends Command {
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    public UpdateCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public String execute(long id) {
        AddCommand addCommand = new AddCommand(collectionManager);
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        for (Flat flat : collection) {
            if (flat.getId() == id) {
                collection.remove(flat);
                addCommand.execute();
                return ANSI_YELLOW + "Элемент с ID = " + id + " обновлён!\n" + ANSI_RESET;
            }
        }
        return ANSI_RED + "Элемента с таким ID нет в коллекции. Попробуйте снова.\n"
                + ANSI_RESET;
    }
}

