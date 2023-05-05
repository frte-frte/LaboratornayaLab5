package commands;

import application.CollectionManager;
import data.*;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Class that add element to collection
 */
public class AddCommand extends Command{
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor of the AddCommand class
     * @param collectionManager object of class CollectionManager
     */
    public AddCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    /**
     * Function that return identifier
     * @return identifier
     */
    protected long generateId() {
        return collectionManager.getMaxId() + 1;
    }

    /**
     * Function that setting name of the flat
     * @return name of the flat
     * @throws NoSuchElementException if no values have been entered
     */
    protected String receiveName() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите название квартиры" + ANSI_RESET + ": ");
                String name;
                try {
                    name = scanner.nextLine();
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверное название, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                if (name == null || name.trim().isEmpty()) {
                    System.out.println(ANSI_RED + "Неверное название, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                return name;
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return null;
        }
    }

    /**
     * Function that setting X coordinate of the flat
     * @return X coordinate of the flat
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected long receiveX() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите координату X" + ANSI_RESET + ": ");
                long x;
                try {
                    x = Long.parseLong(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверная координата X, попробуйте снова\n" +ANSI_RESET);
                    continue;
                }
                if (x <= -970) {
                    System.out.println(ANSI_RED + "Неверная координата X, попробуйте снова\n" +ANSI_RESET);
                    continue;
                }
                return x;
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return 0;
        }
    }

    /**
     * Function that setting Y coordinate of the flat
     * @return Y coordinate of the flat
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected int receiveY() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите координату Y" + ANSI_RESET + ": ");
                int y;
                try {
                    y = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверная координата Y, попробуйте снова\n" +ANSI_RESET);
                    continue;
                }
                if (y > 113) {
                    System.out.println(ANSI_RED + "Неверная координата Y, попробуйте снова\n" +ANSI_RESET);
                    continue;
                }
                return y;
            }

        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return 0;
        }
    }

    /**
     * Function that setting object of the Coordinates class
     * @return object of the Coordinates class
     */
    protected Coordinates receiveCoordinates() {
        return new Coordinates(receiveX(), receiveY());
    }

    /**
     * Function that setting creation date
     * @return creation date
     */
    protected LocalDate receiveCreationDate() {
        return LocalDate.now();
    }

    /**
     * Function that setting area of the flat
     * @return area of the flat
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected int receiveArea() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите площадь" + ANSI_RESET + ": ");
                int area;
                try {
                    area = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверная площадь, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                if (area > 904 || area <= 0) {
                    System.out.println(ANSI_RED + "Неверная площадь, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                return area;
            }

        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return 0;
        }
    }

    /**
     * Function that setting number of rooms of the flat
     * @return number of rooms of the flat
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected Long receiveNumberOfRooms() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите количество комнат" + ANSI_RESET + ": ");
                long numberOfRooms;
                try {
                    numberOfRooms = Long.parseLong(scanner.nextLine());
                } catch(NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверное количество комнат, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                if (numberOfRooms <= 0) {
                    System.out.println(ANSI_RED + "Неверное количество комнат, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                return numberOfRooms;
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return null;
        }
    }

    /**
     * Function that setting type of furniture in a flat
     * @return type of furniture in a flat
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected Furnish receiveFurnish() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите тип мебели. Выберите число из списка" + ANSI_RESET + ": ");
                System.out.println(ANSI_GREEN + "1" + ANSI_RESET + ". Designer" + "\n"
                        + ANSI_GREEN + "2" + ANSI_RESET + ". None" + "\n"
                        + ANSI_GREEN + "3" + ANSI_RESET + ". Fine" + "\n"
                        + ANSI_GREEN + "4" + ANSI_RESET + ". Bad" + "\n"
                        + ANSI_GREEN + "5" + ANSI_RESET + ". Little" + "\n"
                        + ANSI_GREEN + "6" + ANSI_RESET + ". Skip-->");

                int furnish;
                try{
                    furnish = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверные данные. Выберите число из списка" + ANSI_RESET + ": \n");
                    continue;
                }
                if (furnish <= 0 || furnish > 6) {
                    System.out.println(ANSI_RED + "Неверное число. Выберите из списка" + ANSI_RESET + ": \n");
                    continue;
                }
                switch (furnish) {
                    case (1) :
                        return Furnish.DESIGNER;
                    case (2) :
                        return Furnish.NONE;
                    case (3) :
                        return Furnish.FINE;
                    case (4) :
                        return Furnish.BAD;
                    case (5) :
                        return Furnish.LITTLE;
                    case (6) :
                        return null;
                }
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return null;
        }
    }

    /**
     * Function that setting type of view from a window in a flat
     * @return type of view from a window in a flat
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected View receiveView() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите вид. Выберите число из списка" + ANSI_RESET + ": ");
                System.out.println(ANSI_GREEN + "1" + ANSI_RESET + ". Street" + "\n"
                        + ANSI_GREEN + "2" + ANSI_RESET + ". Park" + "\n"
                        + ANSI_GREEN + "3" + ANSI_RESET + ". Normal" + "\n"
                        + ANSI_GREEN + "4" + ANSI_RESET + ". Good" + "\n"
                        + ANSI_GREEN + "5" + ANSI_RESET + ". Terrible" + "\n"
                        + ANSI_GREEN + "6" + ANSI_RESET + ". Skip-->");

                int view;
                try {
                    view = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверные данные. Выберите число из списка" + ANSI_RESET + ": \n");
                    continue;
                }
                if (view <= 0 || view > 6) {
                    System.out.println(ANSI_RED + "Неверное число. Выберите из списка" + ANSI_RESET + ": \n");
                    continue;
                }
                switch (view) {
                    case (1) :
                        return View.STREET;
                    case (2) :
                        return View.PARK;
                    case (3) :
                        return View.NORMAL;
                    case (4) :
                        return View.GOOD;
                    case (5) :
                        return View.TERRIBLE;
                    case (6) :
                        return null;
                }
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return null;
        }
    }

    /**
     * Function that setting type of transport accessibility in a flat
     * @return type of transport accessibility in a flat
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected Transport receiveTransport() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите транспортную доступность. Выберите число из списка" + ANSI_RESET + ": ");
                System.out.println(ANSI_GREEN + "1" + ANSI_RESET + ". Few" + "\n"
                        + ANSI_GREEN + "2" + ANSI_RESET + ". None" + "\n"
                        + ANSI_GREEN + "3" + ANSI_RESET + ". Little" + "\n"
                        + ANSI_GREEN + "4" + ANSI_RESET + ". Normal" + "\n"
                        + ANSI_GREEN + "5" + ANSI_RESET + ". Enough" + "\n"
                        + ANSI_GREEN + "6" + ANSI_RESET + ". Skip-->");

                int transport;
                try {
                    transport = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверные данные. Выберите число из списка" + ANSI_RESET + ": \n");
                    continue;
                }
                if (transport <= 0 || transport > 6) {
                    System.out.println(ANSI_RED + "Неверное число. Выберите из списка" + ANSI_RESET + ": \n");
                    continue;
                }
                switch (transport) {
                    case (1) :
                        return Transport.FEW;
                    case (2) :
                        return Transport.NONE;
                    case (3) :
                        return Transport.LITTLE;
                    case (4) :
                        return Transport.NORMAL;
                    case (5) :
                        return Transport.ENOUGH;
                    case (6) :
                        return null;
                }
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return null;
        }
    }

    /**
     * Function that setting name of the house
     * @return name of the house
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected String receiveHouseName() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите название дома" + ANSI_RESET + ": ");
                String HouseName;
                try {
                    HouseName = scanner.nextLine();
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверное название дома, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                if (HouseName == null || HouseName.trim().isEmpty()) {
                    System.out.println(ANSI_RED + "Неверное название дома, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                return HouseName;
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return null;
        }
    }

    /**
     * Function that setting age of the house
     * @return age of the house
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected Integer receiveYear() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите возраст дома" + ANSI_RESET + ": ");
                Integer year;
                try {
                    year = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверный возраст дома, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                if (year == null || year <= 0) {
                    System.out.println(ANSI_RED + "Неверный возраст дома, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                return year;
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return null;
        }
    }

    /**
     * Function that setting number of flats on floor in the house
     * @return number of flats on floor in the house
     * @throws NumberFormatException if values of fields id not correct
     * @throws NoSuchElementException if no values have been entered
     */
    protected Long receiveNumberOfFlatsOnFloor() {
        try {
            while (true) {
                System.out.println(ANSI_GREEN + "Введите количество квартир на этаже" + ANSI_RESET + ": ");
                Long numberOfFlatsOnFloor;
                try {
                    numberOfFlatsOnFloor = Long.parseLong(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println(ANSI_RED + "Неверное количество квартир на этаже, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                if (numberOfFlatsOnFloor == null || numberOfFlatsOnFloor <= 0) {
                    System.out.println(ANSI_RED + "Неверное количество квартир на этаже, попробуйте снова\n" + ANSI_RESET);
                    continue;
                }
                return numberOfFlatsOnFloor;
            }
        } catch (NoSuchElementException exception) {
            System.out.println(ANSI_YELLOW + "Программа завершена!" + ANSI_RESET);
            System.exit(0);
            return null;
        }
    }

    /**
     * Function that setting object of the House class
     * @return object of the House class
     */
    protected House receiveHouse() {
        return new House(receiveHouseName(), receiveYear(), receiveNumberOfFlatsOnFloor());
    }

    /**
     * Function that add new element in collection
     */
    public void execute() {
        ArrayDeque<Flat> collection = collectionManager.getCollection();
        collection.add(new Flat(generateId(),
                receiveName(),
                receiveCoordinates(),
                receiveCreationDate(),
                receiveArea(),
                receiveNumberOfRooms(),
                receiveFurnish(),
                receiveView(),
                receiveTransport(),
                receiveHouse()));
    }
}
