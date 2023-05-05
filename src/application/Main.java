package application;

/**
 * Main class
 */
public class Main {
    /**
     * @param args path to file for save and load collection
     */
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager(args[0]);
        CommandReader commandReader = new CommandReader();
        commandReader.work(collectionManager);
    }
}