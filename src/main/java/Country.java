import java.util.*;

/**
 * @author Andreas & Mads "150"
 * @version 2021-11-15
 */
public class Country {
    private String name;
    private Map<City, Set<Road>> network;
    private Game game;

    /**
     * Initializes a "Country" object,
     * @param name The countrys name
     */
    public Country(String name) {
        this.name = name;
        network = new TreeMap<>();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * adds a city to the country
     * @param c the city to be added
     */
    public void addCity(City c) {
        network.put(c, new TreeSet<>());
    }

    /**
     * adds two roads between two cities, one in each direction
     * @param a city one
     * @param b city two
     * @param length distance between the cities
     */
    public void addRoads(City a, City b, int length) {
        if (length > 0 && !a.equals(b)) {
            if (a.getCountry().equals(this)) {
                network.get(a).add(new Road(a, b, length));
            }
            if (b.getCountry().equals(this)) {
                network.get(b).add(new Road(b, a, length));
            }
        }
    }

    /**
     *
     * @param city
     * @return returns the position of the city
     */
    public Position position(City city) {
        return new Position(city, city, 0);
    }

    /**
     * If there's a direct road between the two specified cities, it returns a "position" object with these and the distance between them. If the cities are the same, or there isn't a direct connection between them, it returns a position object with "from" as both parameters and distance 0
     * @param from city to travel from
     * @param to city to travel too
     * @return the position object
     */
    public Position readyToTravel(City from, City to) {
        if (from.equals(to)) { return new Position(from, from, 0); }
        for (Road r : network.get(from)) {
            if (r.getTo().equals(to)) { return new Position(from, to, r.getLength()); }
        }
        return new Position(from, from, 0);
    }

    /**
     *
     * @param value
     * @return returns a random number between 0 and the value or just returns 0 if the value is 0
     */
    public int bonus(int value) {
        if (value <= 0) { return 0; }
        return game.getRandom().nextInt(value + 1);
    }

    /**
     * @return returns all the cities in the countrys network
     */
    public Set<City> getCities() {
        return network.keySet();
    }

    /**
     * @param name, the name of the city to be returned
     * @return the city of the specified name, if it exists
     */
    public City getCity(String name) {
        for (Map.Entry<City, Set<Road>> c : network.entrySet()) {
            if (c.getKey().getName().equals(name)) {
                return c.getKey();
            }
        }
        return null;
    }

    /**
     *
     * @param c A city
     * @return returns the roads from a city or will return an empty map
     */
    public Set<Road> getRoads(City c) {
       return network.getOrDefault(c, new TreeSet<>());
    }

    public void reset() {
        for (Map.Entry<City, Set<Road>> c : network.entrySet()) {
            c.getKey().reset();
        }
    }

    /**
     *
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
