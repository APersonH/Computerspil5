/**
 * @author Andreas & Mads, "150"
 * @version 2021-11-09
 */

import java.util.Collection;
import java.util.Objects;

public class City implements Comparable<City>{
    private String name;
    private int initialValue;
    private int value;
    private Country country;

    /**
     * Initializes a "City" object,
     * @param name The city's name
     * @param value The initial value of the city object
     */
    City(String name, int value, Country country) {
        this.name = name;
        this.initialValue = value;
        this.value = initialValue;
        this.country = country;
    }

    /**
     * Subtracts the value calculated by bonus, and returns the bonus to be awarded
     * @return
     */
    public int arrive() {
        int bonus = country.bonus(value);
        if (bonus > 0) { value -= bonus; }
        return bonus;
    }

    public int arrive(Player p) { return arrive(); }

    /**
     * Changes the objects value by adding amount to it.
     * @param amount
     */
    public void changeValue(int amount) {
        value += amount;
    }

    /**
     * Resets the objects value to the initial one.
     */
    public void reset() {
        value = initialValue;
    }

    /**
     *
     * @return The city's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the value of a city
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @return the country the city is in
     */
    public Country getCountry() {
        return country;
    }

    /**
     *
     * @return The initial value of a city
     */
    public int getInitialValue() {
        return initialValue;
    }

    /**
     * Returns the city's name and value in a neat String
     */
    @Override
    public String toString() {
        return name + " (" + value + ")";
    }

    /**
     * Compares two cities names.
     * @param o The city to compare against.
     */
    @Override
    public int compareTo(City o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Compares two cities, and only returns "true" if the names are identical
     * @param o The City to compare against
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    /**
     * Returns a hashcode based on the city name
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
