package data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The main class whose objects are stored in the collection
 */
public class Flat implements Comparable<Flat> {
    /**
     * A field of the Flat class, which is an identifier
     * The field value is greater than 0
     * The value of the field is unique
     * The field value is generated automatically
     */
    private long id;
    /**
     * A field of the Flat class, which is the name of the flat
     * The value of the field is not null
     * The value of the field is not an empty string
     */
    private String name;
    /**
     * A field of the Flat class, which is an object of the Coordinates class, contains the coordinates of the flat
     * The value of the field is not null
     * @see Coordinates#Coordinates(long, int) 
     */
    private Coordinates coordinates;
    /**
     * A field of the Flat class, which is the creation time of an object of this class
     * The value of the field is not null
     * The field value is generated automatically
     */
    private LocalDate creationDate;
    /**
     * A field of the Flat class, which is the area of the flat
     * The field value is greater than 0
     * The maximum value of the field is 904
     */
    private double area;
    /**
     * A field of the Flat class, which is the number of rooms in a flat
     * The field value is greater than 0
     */
    private Long numberOfRooms;
    /**
     * A field of the Flat class, which is a type of furniture in a flat
     * @see Furnish
     */
    private Furnish furnish;
    /**
     * A field of the Flat class, which is a type of view from a window in a flat
     * @see View
     */
    private View view;
    /**
     * A field of the Flat class, which is a type of transport accessibility in a flat
     * @see Transport
     */
    private Transport transport;
    /**
     * A field of the Flat class, which is an object of the House class, contains information about the house
     * The value of the field is not null
     */
    private House house;

    /**
     * Constructor of the Flat class
     * @param id identifier
     * @param name name of the flat
     * @param coordinates coordinates of the flat
     * @see Coordinates
     * @param creationDate creation time of an object of this class
     * @param area area of the flat
     * @param numberOfRooms number of rooms in a flat
     * @param furnish type of furniture in a flat
     * @see Furnish
     * @param view type of view from a window in a flat
     * @see View
     * @param transport type of transport accessibility in a flat
     * @see Transport
     * @param house information about the house
     * @see House
     */
    public Flat(long id, String name, Coordinates coordinates, LocalDate creationDate, double area, Long numberOfRooms,
                Furnish furnish, View view, Transport transport, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }

    /**
     * Function for getting the field value {@link Flat#id}
     * @return identifier
     */
    public long getId() {
        return id;
    }

    /**
     * Field value setting function {@link Flat#id}
     * @param id identifier
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Function for getting the field value {@link Flat#name}
     * @return name of the flat
     */
    public String getName() {
        return name;
    }

    /**
     * Field value setting function {@link Flat#name}
     * @param name name of the flat
     */
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Function for getting the field value {@link Flat#creationDate}
     * @return creation time of an object of this class
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Field value setting function {@link Flat#creationDate}
     * @param creationDate creation time of an object of this class
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Function for getting the field value {@link Flat#area}
     * @return area of the flat
     */
    public double getArea() {
        return area;
    }

    /**
     * Field value setting function {@link Flat#area}
     * @param area area of the flat
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Function for getting the field value {@link Flat#numberOfRooms}
     * @return number of rooms in a flat
     */
    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Field value setting function {@link Flat#numberOfRooms}
     * @param numberOfRooms number of rooms in a flat
     */
    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Function for getting the field value {@link Flat#furnish}
     * @return type of furniture in a flat
     */
    public Furnish getFurnish() {
        return furnish;
    }

    /**
     * Field value setting function {@link Flat#furnish}
     * @param furnish type of furniture in a flat
     */
    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    /**
     * Function for getting the field value {@link Flat#view}
     * @return type of view from a window in a flat
     */
    public View getView() {
        return view;
    }

    /**
     * Field value setting function {@link Flat#view}
     * @param view type of view from a window in a flat
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Function for getting the field value {@link Flat#transport}
     * @return type of transport accessibility in a flat
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     * Field value setting function {@link Flat#transport}
     * @param transport type of transport accessibility in a flat
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    /**
     * Function for getting the field value {@link Flat#house}
     * @return information about the house
     */
    public House getHouse() {
        return house;
    }

    /**
     * Field value setting function {@link Flat#house}
     * @param house information about the house
     */
    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id = " + id +
                ", name = '" + name + "'" +
                ", coordinates = " + coordinates +
                ", creationDate = " + creationDate +
                ", area = " + area +
                ", numberOfRooms = " + numberOfRooms +
                ", furnish = " + furnish +
                ", view = " + view +
                ", transport = " + transport +
                ", house = " + house +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flat flat)) return false;
        return id == flat.id && area == flat.area && numberOfRooms == flat.numberOfRooms && name.equals(flat.name)
                && coordinates.equals(flat.coordinates) && creationDate.equals(flat.creationDate)
                && furnish == flat.furnish && view == flat.view && transport == flat.transport
                && house.equals(flat.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area, numberOfRooms, furnish, view, transport, house);
    }

    @Override
    public int compareTo(Flat o) {
        return Long.compare(this.getId(), o.getId());
    }
}
