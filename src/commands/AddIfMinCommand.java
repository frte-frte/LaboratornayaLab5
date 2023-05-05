package commands;

import application.CollectionManager;
import data.Flat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that add element to collection if the entered value is less than the smallest ID value
 */
public class AddIfMinCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor of the AddIfMinCommand class
     * @param collectionManager object of class CollectionManager
     */
    public AddIfMinCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Function that add new element in collection if the entered value is less than the smallest ID value
     * @param id entered value
     *
     */
    public void execute(long id) {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        AddCommand addCommand = new AddCommand(collectionManager);
        List<Long> idList = new ArrayList<>();
        for (Flat flat : collection) {
            idList.add(flat.getId());
        }
        Collections.sort(idList);
        if (id < idList.get(0)) {
            collection.add(new Flat(id,
                    addCommand.receiveName(),
                    addCommand.receiveCoordinates(),
                    addCommand.receiveCreationDate(),
                    addCommand.receiveArea(),
                    addCommand.receiveNumberOfRooms(),
                    addCommand.receiveFurnish(),
                    addCommand.receiveView(),
                    addCommand.receiveTransport(),
                    addCommand.receiveHouse()));
            System.out.println(ANSI_YELLOW + "Новый элемент был добавлен!\n" + ANSI_RESET);
        } else {
            System.out.println(ANSI_YELLOW + "Не удалось добавить элемент, существуют значения ID меньше заданного!\n"
                    + ANSI_RESET);
        }
    }
}
