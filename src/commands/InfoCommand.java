package commands;

import application.CollectionManager;

/**
 * Class that return info about collection
 */
public class InfoCommand extends Command {

    /**
     * Constructor of the InfoCommand class
     * @param collectionManager object of class CollectionManager
     */
    public InfoCommand (CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Function that return information about collection
     */
    public String execute() {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";

        return ANSI_GREEN + "Тип коллекции" + ANSI_RESET + ": " +
                collectionManager.getCollection().getClass() +
                "\n" +
                ANSI_GREEN + "Время инициализации" + ANSI_RESET + ": " +
                collectionManager.getInitializationTime() +
                "\n" +
                ANSI_GREEN + "Количество элементов" + ANSI_RESET + ": " + collectionManager.getCollection().size();
    }
}
