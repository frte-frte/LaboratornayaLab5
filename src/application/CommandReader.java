package application;

import commands.*;
import data.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Class that reads commands
 */
public class CommandReader {
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    /**
     * Function for reads commands
     * @param collectionManager object of class CollectionManager
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    public void work(CollectionManager collectionManager){
        Scanner scanner = new Scanner(System.in);
        try {
        while (true){
            System.out.println("Введите команду:");
            String[] command =  scanner.nextLine().replaceFirst("^\s+", "")
                    .replaceFirst("\s+", " ").split(" ");

            switch (command[0]) {
                case ("help"):
                    if (command.length == 1){
                        HelpCommand helpCommand = new HelpCommand(collectionManager);
                        System.out.println(helpCommand.execute());
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "У этой команды нет аргументов!\n" + ANSI_RESET);
                    break;
                case ("info"):
                    if (command.length == 1) {
                        InfoCommand infoCommand = new InfoCommand(collectionManager);
                        System.out.println(infoCommand.execute());
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "У этой команды нет аргументов!\n" + ANSI_RESET);
                    break;
                case ("show"):
                    if (command.length == 1) {
                        ShowCommand showCommand = new ShowCommand(collectionManager);
                        System.out.println(showCommand.execute());
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "У этой команды нет аргументов!\n" + ANSI_RESET);
                    break;
                case ("add"):
                    if (command.length == 1) {
                        new AddCommand(collectionManager).execute();
                        System.out.println(ANSI_YELLOW + "Новый элемент был добавлен!\n" + ANSI_RESET);
                        break;
                    } else if (command.length == 13) {
                        try {
                            Long id = collectionManager.getMaxId() + 1;
                            String name = command[1];
                            long x = Long.parseLong(command[2]);
                            int y = Integer.parseInt(command[3]);
                            LocalDate creationDate = LocalDate.parse(command[4].replace("-",""),
                                    DateTimeFormatter.BASIC_ISO_DATE);
                            double area = Double.parseDouble(command[5]);
                            Long numberOfRooms = Long.parseLong(command[6]);
                            Furnish furnish = Furnish.valueOf(command[7]);
                            View view = View.valueOf(command[8]);
                            Transport transport = Transport.valueOf(command[9]);
                            String houseName = command[10];
                            Integer year = Integer.parseInt(command[11]);
                            long numberOfFlatsOnFloor = Long.parseLong(command[12]);
                            Flat newFlat = new Flat(id, name, new Coordinates(x, y),
                                    creationDate, area, numberOfRooms, furnish, view, transport,
                                    new House(houseName, year, numberOfFlatsOnFloor));
                            if (collectionManager.checkFlat(newFlat)) {
                                collectionManager.getCollection().add(newFlat);
                                System.out.println(ANSI_YELLOW + "Новый элемент был добавлен!\n" + ANSI_RESET);
                            } else {
                                System.out.println(ANSI_RED + "Неверные данные!\n" +ANSI_RESET);
                            }
                            break;
                            } catch (NumberFormatException exception) {
                            System.out.println(ANSI_RED + "Неверные аргументы" + ANSI_RESET);
                        }
                    } else {
                        System.out.println(ANSI_YELLOW + "Неверное количество аргументов!\n" + ANSI_RESET);
                    }
                    break;
                case ("update"):
                    if (command.length == 2) {
                        System.out.println(new UpdateCommand(collectionManager).execute(Long.parseLong(command[1])));
                    } else if (command.length == 14) {
                        ArrayDeque<Flat> collection = collectionManager.getCollection();
                        for (Flat flat : collection) {
                            if (flat.getId() == Long.parseLong(command[1])) {
                                try {
                                    String name = command[2];
                                    long x = Long.parseLong(command[3]);
                                    int y = Integer.parseInt(command[4]);
                                    LocalDate creationDate = LocalDate.parse(command[5].replace("-",""),
                                            DateTimeFormatter.BASIC_ISO_DATE);
                                    double area = Double.parseDouble(command[6]);
                                    Long numberOfRooms = Long.parseLong(command[7]);
                                    Furnish furnish = Furnish.valueOf(command[8]);
                                    View view = View.valueOf(command[9]);
                                    Transport transport = Transport.valueOf(command[10]);
                                    String houseName = command[11];
                                    Integer year = Integer.parseInt(command[12]);
                                    long numberOfFlatsOnFloor = Long.parseLong(command[13]);
                                    Flat newFlat =  new Flat(Long.parseLong(command[1]), name, new Coordinates(x, y),
                                            creationDate, area, numberOfRooms, furnish, view, transport,
                                            new House(houseName, year, numberOfFlatsOnFloor));
                                        if (collectionManager.checkFlat(newFlat)) {
                                            collection.remove(flat);
                                            collection.add(newFlat);
                                            System.out.println(ANSI_YELLOW + "Элемент с ID = " + Long.parseLong(command[1])
                                                    + " был обновлён!\n" + ANSI_RESET);
                                            break;
                                        } else {
                                            System.out.println(ANSI_RED + "Неверные данные" + ANSI_RESET);
                                        }
                                } catch (NumberFormatException exception) {
                                    System.out.println(ANSI_RED + "Неверные аргументы" + ANSI_RESET);
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println(ANSI_RED + "Неверные аргументы" + ANSI_RESET);
                    }
                    break;
                case ("remove_by_id"):
                    if (command.length == 2) {
                        System.out.println(new RemoveByIdCommand(collectionManager).execute(Long.parseLong(command[1])));

                    } else {
                        System.out.println(ANSI_RED + "Неверный аргумент команды. Напишите [help] для проверки "
                                + "синтаксиса команды!" + ANSI_RESET);
                    }
                    break;
                case ("save") :
                    if (command.length == 1) {
                        new SaveCommand(collectionManager).execute();
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "У этой команды нет аргументов!\n" + ANSI_RESET);
                    break;
                case ("execute_script") :
                    if (command.length != 2) {
                        System.out.println(ANSI_RED + "Неверный аргумент команды. Напишите [help] для проверки "
                                + "синтаксиса команды!" + ANSI_RESET);
                    } else {
                        System.out.println(new ExecuteScriptCommand(collectionManager).execute(command[1]));
                    }
                    break;
                case ("clear"):
                    if (command.length == 1) {
                        new ClearCommand(collectionManager).execute();
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "У этой команды нет аргументов!\n" + ANSI_RESET);
                    break;

                case ("exit"):
                    if (command.length == 1) {
                        new ExitCommand(collectionManager).execute();
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "У этой команды нет аргументов!\n" + ANSI_RESET);
                    break;
                case ("remove_first"):
                    if (command.length == 1) {
                        System.out.println(new RemoveFirstCommand(collectionManager).execute());
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "У этой команды нет аргументов!\n" + ANSI_RESET);
                    break;
                case ("remove_head"):
                    if (command.length == 1) {
                        System.out.println(new RemoveHeadCommand(collectionManager).execute());
                        break;
                    }
                    System.out.println(ANSI_YELLOW + "У этой команды нет аргументов!\n" + ANSI_RESET);
                    break;
                case ("add_if_min"):
                    if (command.length == 2) {
                        new AddIfMinCommand(collectionManager).execute(Long.parseLong(command[1]));

                    } else if (command.length == 14) {
                        ArrayDeque<Flat> collection = collectionManager.getCollection();
                        AddCommand addCommand = new AddCommand(collectionManager);
                        long id = Long.parseLong(command[1]);
                        List<Long> idList = new ArrayList<>();
                        for (Flat flat : collection) {
                            idList.add(flat.getId());
                        }
                        Collections.sort(idList);
                        if (id < idList.get(0)) {
                            try {
                                String name = command[2];
                                long x = Long.parseLong(command[3]);
                                int y = Integer.parseInt(command[4]);
                                LocalDate creationDate = LocalDate.parse(command[5].replace("-",""),
                                        DateTimeFormatter.BASIC_ISO_DATE);
                                double area = Double.parseDouble(command[6]);
                                Long numberOfRooms = Long.parseLong(command[7]);
                                Furnish furnish = Furnish.valueOf(command[8]);
                                View view = View.valueOf(command[9]);
                                Transport transport = Transport.valueOf(command[10]);
                                String houseName = command[11];
                                Integer year = Integer.parseInt(command[12]);
                                long numberOfFlatsOnFloor = Long.parseLong(command[13]);
                                Flat newFlat =  new Flat(Long.parseLong(command[1]), name, new Coordinates(x, y),
                                        creationDate, area, numberOfRooms, furnish, view, transport,
                                        new House(houseName, year, numberOfFlatsOnFloor));
                                if (collectionManager.checkFlat(newFlat)) {
                                    collection.add(newFlat);
                                    System.out.println(ANSI_YELLOW + "Новый элемент был добавлен!\n" + ANSI_RESET);
                                    break;
                                } else {
                                    System.out.println(ANSI_RED + "Неверные данные!" + ANSI_RESET);
                                }
                            } catch (NumberFormatException exception) {
                                System.out.println(ANSI_RED + "Неверные аргументы" + ANSI_RESET);
                                break;
                            }
                        }
                        System.out.println(ANSI_YELLOW + "Не удалось добавить элемент, существуют значения ID меньше заданного!\n"
                                + ANSI_RESET);
                        break;
                    }
                    break;
                case ("filter_by_view"):
                    if (command.length == 2 && (command[1].equals("STREET")
                            || command[1].equals("PARK")
                            || command[1].equals("NORMAL")
                            || command[1].equals("GOOD")
                            || command[1].equals("TERRIBLE"))) {
                        System.out.println(new FilterByViewCommand(collectionManager).execute(command[1]));
                        break;
                    } else {
                        System.out.println(ANSI_RED + "Неверный аргумент команды. Напишите [help] для проверки "
                                + "синтаксиса команды!" + ANSI_RESET);
                    }
                    break;
                case ("filter_starts_with_name"):
                    if (command.length != 2) {
                        System.out.println(ANSI_RED + "Неверный аргумент команды. Напишите [help] для проверки "
                                + "синтаксиса команды!" + ANSI_RESET);
                    } else {
                        System.out.println(new FilterStartsWithNameCommand(collectionManager).execute(command[1]));
                    }
                    break;
                case ("print_field_descending_transport"):
                    System.out.println(new PrintFieldDescendingTransportCommand(collectionManager).execute());
                    break;
                default:
                    System.out.println(ANSI_YELLOW + "Неизвестная команда! Напишите [help] для вывода списка команд.\n" + ANSI_RESET);
                    break;
                }
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
        }
    }
}
