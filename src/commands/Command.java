package commands;

import application.CollectionManager;

/**
 * Parent class of all classes from the Commands package
 */
public abstract class Command {
    protected CollectionManager collectionManager;

    public Command(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
