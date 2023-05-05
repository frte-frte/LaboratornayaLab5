package application;

import Parsers.Deserializer.CoordinatesDeserializer;
import Parsers.Deserializer.FlatsDeserializer;
import Parsers.Deserializer.HouseDeserializer;
import Parsers.Deserializer.LocalDateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import data.Coordinates;
import data.Flat;
import data.House;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;

/**
 * Class that manages a collection of objects
 */
public class CollectionManager {
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    /**
     * A field of the CollectionManager class, which is collection of object
     */
    private static ArrayDeque<Flat> collection = new ArrayDeque<>();
    /**
     * A field of the CollectionManager class, which is collection initialization time
     */
    private final LocalDateTime initializationTime;
    /**
     * A field of the CollectionManager class, which is path to file for save and load collection
     */
    private String filePath;
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(House.class, new HouseDeserializer())
            .registerTypeAdapter(ArrayDeque.class, new FlatsDeserializer())
            .create();

    /**
     * Constructor of the collectionManager class
     * @param filePath path to file for save and load collection
     */
    public CollectionManager(String filePath) {
        this.filePath = filePath;
        initializationTime = LocalDateTime.now();
        collection = load();
    }

    /**
     * Function for parsing json file to collection
     * @return collection of object
     * @throws JsonParseException if an error occurred while parsing the file
     * @throws IOException if an error occurred while reading the file
     */
    public ArrayDeque load() {
        if (filePath.equals("")) {
            return new ArrayDeque<Flat>();
        }
        if (checkPermissionForRead()) {
            try (FileReader reader = new FileReader(filePath)) {
                StringBuilder data = new StringBuilder();
                while (reader.ready()) {
                    int t = reader.read();
                    data.append((char) t);
                }
                if (data.isEmpty()) {
                    System.out.println(ANSI_YELLOW + "Файл пуст!" + ANSI_RESET);
                    return new ArrayDeque<Flat>();
                } else {
                    ArrayDeque<Flat> result = gson.fromJson(data.toString(), ArrayDeque.class);
                    System.out.println(ANSI_YELLOW + "Файл " + filePath + " успешно загружен!" + ANSI_RESET);
                    for (Flat flat : result) {
                        if (!checkFlat(flat)){
                            System.out.println(ANSI_RED + "Неверные значения!\n" + ANSI_RESET);
                            return new ArrayDeque<>();
                        }
                    }
                    System.out.println(ANSI_YELLOW + "В коллекции находится "
                            + result.size()
                            + " объект(-а/-ов)" + ANSI_RESET);
                    return result;
                }
            } catch (JsonParseException exception) {
                System.out.println(ANSI_RED + "Проблемы с парсингом файла!" + ANSI_RESET);
                return new ArrayDeque<Flat>();
            } catch (IOException exception){
                return new ArrayDeque<Flat>();
            }
        }
        return new ArrayDeque<Flat>();
    }

    /**
     * Function for checking fields of objects of the Flat class
     * @param flat object
     * @return True False
     */
    public boolean checkFlat(Flat flat) {
        return (flat.getId() > 0 &&
                !flat.getName().trim().isEmpty() &&
                flat.getCoordinates().getX() > -970 &&
                flat.getCoordinates().getY() <= 113 &&
                flat.getCreationDate() != null &&
                flat.getArea() > 0 &&
                flat.getArea() <=904 &&
                flat.getNumberOfRooms() > 0 &&
                flat.getHouse() != null);
    }

    /**
     * Function for checking permission for read file
     * @return True False
     */
    public boolean checkPermissionForRead() {
        File file = new File(filePath);
        return file.exists() && file.canRead();
    }

    /**
     * Function that return the maximum value of the ID field of an object of the Flat class
     * @return Maximum value of the ID field of an object of the Flat class
     */
    public long getMaxId() {
        long counter = Integer.MIN_VALUE;
        for (Flat flat : collection) {
            if (flat.getId() > counter) {
                counter = flat.getId();
            }
        }
        if (counter == Integer.MIN_VALUE) {
            return 0;
        }
        return counter;
    }

    /**
     * Function for getting the field value {@link CollectionManager#initializationTime}
     * @return collection initialization time
     */
    public LocalDateTime getInitializationTime(){
        return initializationTime;
    }

    /**
     * Function for getting the field value {@link CollectionManager#collection}
     * @return collection of object
     */
    public ArrayDeque<Flat> getCollection() {
        return collection;
    }

    /**
     * Field value setting function {@link CollectionManager#collection}
     * @param collection collection of object
     */
    public void setCollection(ArrayDeque<Flat> collection) {
        this.collection = collection;
    }

    /**
     * Function for getting the field value {@link CollectionManager#filePath}
     * @return path to file for save and load collection
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Field value setting function {@link CollectionManager#filePath}
     * @param filePath path to file for save and load collection
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
