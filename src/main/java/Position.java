import java.util.Objects;

/**
 * @author Andreas & Mads "150"
 * @version 2021-11-15
 */
public class Position {
    private City from;
    private City to;
    private int distance;
    private int total;

    /**
     * Initializes a "Position" object
     * @param from the city the player is moving from
     * @param to the city the player is moving to
     * @param distance the distance the player has to move initially
     */
    Position(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.total = distance;
        this.distance = distance;
    }

    /**
     * Subtracts one from move if greater than zero
     * @return returns true if operation was successful
     */
    public boolean move() {
        if (distance > 0) {
            distance--;
            return true;
        }
        return false;
    }

    /** Turns the player around, and sets the remaining distance to the distance already travelled
     */
    public void turnAround() {
        City tmp = from;
        from = to;
        to = tmp;
        distance = total - distance;
    }

    /**
     * Sets the distance to 0 when a player arrives to a city
     * @return returns true when the player has arrived
     */
    public boolean hasArrived() {
        return distance == 0;
    }

    /**
     *
     * @return the value of which city you came from
     */
    public City getFrom() {
        return from;
    }

    /**
     *
     * @return the value of which city you're going to
     */
    public City getTo() {
        return to;
    }

    /**
     *
     * @return the value of the distance to a city
     */
    public int getDistance() {
        return distance;
    }

    /**
     *
     * @return the value of total diistance between two cities
     */
    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return from + " -> " + to + " : " + distance + "/" + total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return distance == position.distance && total == position.total && Objects.equals(from, position.from) && Objects.equals(to, position.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, distance, total);
    }
}
