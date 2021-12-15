import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    /**
     * Sets up the test fixture.
     * <p>
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        //Create new game
        game = new Game();

        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");

        //Set game for countries
        country1.setGame(game);
        country2.setGame(game);

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);
        country1.addCity(cityD);

        country2.addCity(cityE);
        country2.addCity(cityF);
        country2.addCity(cityG);

        // Create roads
        country1.addRoads(cityA, cityB, 4);
        country1.addRoads(cityA, cityC, 3);
        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        country2.addRoads(cityF, cityD, 3);
        country2.addRoads(cityF, cityG, 6);
    }

    @Test
    void constructorTest() {
        City testCity = new City("testCity", 40, country1);
        assertEquals(testCity.getName(), "testCity");
        assertEquals(testCity.getValue(), 40);
        assertEquals(testCity.getCountry(), country1);
    }

    @Test
    void arrive() {
        for(int seed = 0; seed < 1000; seed++) { // Try different seeds
            game.getRandom().setSeed(seed); // Set seed
            int bonus = country1.bonus(80); // Remember bonus
            int value = cityA.getValue(); // Remember value
            game.getRandom().setSeed(seed); // Reset seed
            assertEquals(bonus, cityA.arrive()); // Same bonus
            assertEquals(value-bonus, cityA.getValue());
            cityA.reset();
        }
        City test = new City("test", 0, country1);
        assertEquals(0, test.arrive());
        test.changeValue(-1);
        assertEquals(0, test.arrive());
    }

    @Test
    void changeValue() {
        cityA.changeValue(20);
        assertEquals(cityA.getValue(), cityA.getValue());
    }

    @Test
    void reset() {
        cityA.changeValue(40);
        cityA.reset();
        assertEquals(cityA.getValue(), cityA.getInitialValue());
    }

    @Test
    void testToString() {
        assertEquals("City A (80)", cityA.toString());
        assertFalse("City B (80)".equals(cityA.toString()));
    }
}