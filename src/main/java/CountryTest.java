import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @BeforeEach
    public void setUp() {
        // Create game object

        game = new Game(0);
        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");
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

        country2.addCity(cityE);
        country2.addCity(cityF);
        country2.addCity(cityG);

        // Create roads
        country1.addRoads(cityA, cityB, 4);
        country1.addRoads(cityA, cityC, 3);
        //country1.addRoads(cityA, cityD, 5);
        //country1.addRoads(cityB, cityD, 2);
        //country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        //country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        //country2.addRoads(cityF, cityD, 3);
        country2.addRoads(cityF, cityG, 6);
    }

    @Test
    void constructorTest() {
        Country testCountry = new Country("testName");
        assertEquals(testCountry.getName(), "testName");
    }

    @Test
    void addCity() {
        assertNull(country1.getCity(cityD.getName()));
        country1.addCity(cityD);
        assertEquals(country1.getCity(cityD.getName()), cityD);
    }

    @Test
    void addRoads() {
        country1.addCity(cityD);
        Set<Road> testSet = new TreeSet<Road>();
        assertEquals(testSet,country1.getRoads(cityD));

        country1.addRoads(cityA, cityD, -1);
        country1.addRoads(cityD, cityD, 10);
        assertEquals(testSet, country1.getRoads(cityD));

        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);

        testSet.add(new Road(cityD, cityA, 5));
        testSet.add(new Road(cityD, cityB, 2));
        testSet.add(new Road(cityD, cityC, 2));
        assertEquals(testSet,country1.getRoads(cityD));
    }

    @Test
    void position() {
        assertEquals(country1.position(cityA), new Position(cityA, cityA, 0));
    }

    @Test
    void readyToTravel() {
        assertEquals(country1.readyToTravel(cityA, cityA), new Position(cityA, cityA, 0));
        assertEquals(country1.readyToTravel(cityA, cityE), new Position(cityA, cityA, 0));
        assertEquals(country1.readyToTravel(cityA, cityB), new Position(cityA, cityB, 4));
    }

    @Test
    void bonus() {
            for(int seed = 0; seed < 100; seed++) {
                game.getRandom().setSeed(seed);
                int totalB = 0;
                for(int i = 0; i < 100000; i++) {
                    int bonus = country1.bonus(80);
                    totalB += bonus;
                }
                assertTrue(totalB/100000 <= 42 && totalB/100000 >= 38);

            }
            assertEquals(country1.bonus(1), 0);
            assertEquals(country1.bonus(0), 0);
    }

    @Test
    void getCities() {
        City testCityA = new City("City A", 80, country1);
        City testCityB = new City("City B", 60, country1);
        City testCityC = new City("City C", 40, country1);
        City testCityD = new City("City D", 100, country1);
        assertTrue(country1.getCities().contains(testCityA));
        assertTrue(country1.getCities().contains(testCityB));
        assertTrue(country1.getCities().contains(testCityC));
        assertFalse(country1.getCities().contains(testCityD));
    }

    @Test
    void getCity() {
        City testCity = new City("City A", 80, country1);
        assertEquals(country1.getCity("City A"), testCity);
        assertNull(country1.getCity("wrongName"));
    }

    @Test
    void getRoads() {
        Road testRoad1 = new Road(cityA, cityB, 4);
        Road testRoad2 = new Road(cityA, cityC, 3);
        assertTrue(country1.getRoads(country1.getCity("City A")).contains(testRoad1));
        assertTrue(country1.getRoads(country1.getCity("City A")).contains(testRoad2));
    }

    @Test
    void reset() {
        int v = country1.getCity("City A").getValue();
        country1.getCity("City A").changeValue(100);
        assertEquals(v + 100, country1.getCity("City A").getValue());
        country1.getCity("City A").reset();
        assertEquals(v, country1.getCity("City A").getValue());
    }

    @Test
    void testToString() {
        assertEquals("Country 1", country1.toString());
        assertFalse("Country 0".equals(country1.toString()));
    }
}