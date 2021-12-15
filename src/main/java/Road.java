import java.util.Objects;

/**
 * @author Andreas & Mads "150"
 * @version 2021-09-11
 */
public class Road implements Comparable<Road>{
    private City from, to;
    private int length;

    /**
     * Initializes a "Road" object
     * @param from the city the road starts in
     * @param to the city the road ends in
     * @param length the length of the road
     */
    Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * Returns the name of the cities at the to ends of the road and distance between them
     */
    public String toString() {
        return from + " -> " + to + " : " + length;
    }

    /**
     *
     * @return the from value of a city
     */
    public City getFrom() {
        return from;
    }

    /**
     *
     * @return the to value of a city
     */
    public City getTo() {
        return to;
    }

    /**
     *
     * @return the length to a city
     */
    public int getLength() {
        return length;
    }

    /**
     * Compares two roads first by starting point, then by end point, and then by length
     * @param o the road to compare
     */
    @Override
    public int compareTo(Road o) {
        if (!this.from.equals(o.from)) { return this.from.compareTo(o.from); }
        else if (!this.to.equals(o.to)) { return this.to.compareTo(o.to); }
        else { return this.length - o.length; }
    }

    /**
     * Compares two road, and only returns "true" if all variables are identical
     * @param o The road to compare against
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return length == road.length && Objects.equals(from, road.from) && Objects.equals(to, road.to);
    }

    /**
     * Returns a hashcode based on the roads variables
     */
    @Override
    public int hashCode() {
        return Objects.hash(from, to, length);
    }
}
