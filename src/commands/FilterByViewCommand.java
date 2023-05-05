package commands;

import application.CollectionManager;
import data.Flat;
import data.View;

import java.util.ArrayDeque;

/**
 * Class that filters collection by the value of field View
 */
public class FilterByViewCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor of FilterByViewCommand class
     * @param collectionManager object of class CollectionManager
     */
    public FilterByViewCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Function that filters collection by the value of field View
     */
    public String execute(String stringView) {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        StringBuilder filterByView = new StringBuilder();
        for (Flat flat : collection) {
            if (flat.getView() == View.valueOf(stringView)) {
                filterByView.append(flat).append("\n");
            }
        }
        if (filterByView.isEmpty()) {
            return ANSI_YELLOW + "Нет элементов с таким полем!" + ANSI_RESET;
        }
        return filterByView.toString();
        }
    }
