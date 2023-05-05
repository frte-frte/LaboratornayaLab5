package commands;

import application.CollectionManager;
import data.Flat;
import data.Transport;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintFieldDescendingTransportCommand extends Command{
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    public PrintFieldDescendingTransportCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public String execute() {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            return ANSI_RED + "Колекция пуста\n" + ANSI_RESET;
        } else {
            List<Transport> transportsType = new ArrayList<>();
            for (Flat flat : collection) {
                transportsType.add(flat.getTransport());
            }
            Collections.sort(transportsType);
            StringBuilder stringBuilder = new StringBuilder();
            for (Transport transport : transportsType) {
                stringBuilder.append(transport).append("\n");
            }
            return stringBuilder.toString();
        }
    }
}
