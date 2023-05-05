package commands;

import application.CollectionManager;
import data.Flat;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterStartsWithNameCommand extends Command{
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public String execute(String name) {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        StringBuilder filterStartsWithName = new StringBuilder();
        Pattern pattern = Pattern.compile("^" + name);
        for (Flat flat : collection) {
            Matcher matcher = pattern.matcher(flat.getName());
            if (matcher.find()) {
                filterStartsWithName.append(flat).append("\n");
            }
        }
        if (filterStartsWithName.isEmpty()) {
            return ANSI_RED + "Нет элементов, значение поля name, которых начинается с заданной подстроки: '" + name
                    + "'!\n" + ANSI_RESET;
        } else {
            return filterStartsWithName.toString();
        }
    }
}
