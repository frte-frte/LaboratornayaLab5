package commands;

import application.CollectionManager;
import data.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExecuteScriptCommand extends Command {
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public String execute(String filepath) {
        try (FileReader reader = new FileReader(filepath)) {
            StringBuilder data = new StringBuilder();
            while (reader.ready()) {
                int t = reader.read();
                data.append((char) t);
            }
            reader.close();
            if (!data.isEmpty()) {
                String[] commandList = data.toString().split("\n");
                    for (String commands : commandList){
                        String[] command = commands.replace("\r", "").split(" ");
                        switch (command[0]) {
                            case ("help"):
                                HelpCommand helpCommand = new HelpCommand(collectionManager);
                                System.out.println(helpCommand.execute());
                                break;
                            case ("info"):
                                InfoCommand infoCommand = new InfoCommand(collectionManager);
                                System.out.println(infoCommand.execute());
                                break;
                            case ("show"):
                                ShowCommand showCommand = new ShowCommand(collectionManager);
                                System.out.println(showCommand.execute());
                                break;
                            case ("add"):
                                if (command.length == 13) {
                                    try {
                                        long id = collectionManager.getMaxId() + 1;
                                        String name = command[1];
                                        long x = Long.parseLong(command[2]);
                                        int y = Integer.parseInt(command[3]);
                                        LocalDate creationDate = LocalDate.parse(command[4].replace("-", ""),
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
                                            System.out.println(ANSI_RED + "Неверные данные!\n" + ANSI_RESET);
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
                                if (command.length == 14) {
                                    ArrayDeque<Flat> collection = collectionManager.getCollection();
                                    for (Flat flat : collection) {
                                        if (flat.getId() == Long.parseLong(command[1])) {
                                            try {
                                                String name = command[2];
                                                long x = Long.parseLong(command[3]);
                                                int y = Integer.parseInt(command[4]);
                                                LocalDate creationDate = LocalDate.parse(command[5].replace("-", ""),
                                                        DateTimeFormatter.BASIC_ISO_DATE);
                                                double area = Double.parseDouble(command[6]);
                                                Long numberOfRooms = Long.parseLong(command[7]);
                                                Furnish furnish = Furnish.valueOf(command[8]);
                                                View view = View.valueOf(command[9]);
                                                Transport transport = Transport.valueOf(command[10]);
                                                String houseName = command[11];
                                                Integer year = Integer.parseInt(command[12]);
                                                long numberOfFlatsOnFloor = Long.parseLong(command[13]);
                                                Flat newFlat = new Flat(Long.parseLong(command[1]), name, new Coordinates(x, y),
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
                                if (command.length != 2) {
                                    System.out.println(ANSI_RED + "Неверный аргумент команды. Напишите [help] для проверки "
                                            + "синтаксиса команды!" + ANSI_RESET);
                                } else {
                                    new RemoveByIdCommand(collectionManager).execute(Long.parseLong(command[1]));
                                }
                                break;
                            case ("clear"):
                                new ClearCommand(collectionManager).execute();
                                break;
                            case ("save"):
                                new SaveCommand(collectionManager).execute();
                                break;
                            case ("execute_script"):
                                List<String> recList = new ArrayList<>();
                                if (command.length != 2) {
                                    System.out.println(ANSI_RED + "Неверный аргумент команды. Напишите [help] для проверки "
                                            + "синтаксиса команды!" + ANSI_RESET);
                                } else {
                                    recList.add(command[1]);
                                    for (String file : recList) {
                                        if (file == command[1]) {
                                            System.out.println(ANSI_RED + "Обнаружен рекурсивный вызов скрипта! Программа завершена!" + ANSI_RESET);
                                            System.exit(0);
                                        }
                                    }
                                    System.out.println(new ExecuteScriptCommand(collectionManager).execute(command[1]));
                                }
                                break;
                            case ("exit"):
                                new ExitCommand(collectionManager).execute();
                                break;
                            case ("remove_first"):
                                System.out.println(new RemoveFirstCommand(collectionManager).execute());
                                break;
                            case ("remove_head"):
                                System.out.println(new RemoveHeadCommand(collectionManager).execute());
                                break;
                            case ("add_if_min"):
                                if (command.length == 14) {
                                    ArrayDeque<Flat> collection = collectionManager.getCollection();
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
                                            LocalDate creationDate = LocalDate.parse(command[5].replace("-", ""),
                                                    DateTimeFormatter.BASIC_ISO_DATE);
                                            double area = Double.parseDouble(command[6]);
                                            Long numberOfRooms = Long.parseLong(command[7]);
                                            Furnish furnish = Furnish.valueOf(command[8]);
                                            View view = View.valueOf(command[9]);
                                            Transport transport = Transport.valueOf(command[10]);
                                            String houseName = command[11];
                                            Integer year = Integer.parseInt(command[12]);
                                            long numberOfFlatsOnFloor = Long.parseLong(command[13]);
                                            Flat newFlat = new Flat(Long.parseLong(command[1]), name, new Coordinates(x, y),
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
                            case ("filter_by_view"):
                                if (command.length != 2) {
                                    System.out.println(ANSI_RED + "Неверный аргумент команды. Напишите [help] для проверки "
                                            + "синтаксиса команды!" + ANSI_RESET);
                                } else {
                                    System.out.println(new FilterByViewCommand(collectionManager).execute((command[1])));
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
                }

            } catch(IOException exception){
                System.out.println(ANSI_RED + "Какие-то неполадки с файлом\n" + ANSI_RESET);
            }
            return ANSI_YELLOW + "Выполнение скрипта завершено" + ANSI_RESET;
        }
    }
