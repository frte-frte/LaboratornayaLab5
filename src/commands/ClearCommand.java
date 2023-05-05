package commands;

import application.CollectionManager;
import data.Flat;

import java.util.ArrayDeque;

/**
 * Class that clear collection
 */
public class ClearCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor of the ClearCommand class
     * @param collectionManager object of class CollectionManager
     */
    public ClearCommand (CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Function that clear collection
     */
    public void execute() {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println(ANSI_YELLOW + "Коллекция пуста\n" + ANSI_RESET);
        } else {
            collection.clear();
            System.out.println(ANSI_YELLOW + "Коллекция была отчищена!\n" + ANSI_RESET);
        }
    }
}
