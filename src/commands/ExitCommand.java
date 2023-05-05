package commands;

import application.CollectionManager;

/**
 * Class that exit the program
 */
public class ExitCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor of ExitCommand class
      * @param collectionManager object of class CollectionManager
     */
    public ExitCommand (CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Function that exit the program
     */
    public void execute() {
        System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
        System.exit(0);
    }
}
