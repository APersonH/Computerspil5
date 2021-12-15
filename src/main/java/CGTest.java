import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class provides a test fixture to use for the 'Computer Game' project, dIntProg.
 * It creates a simple network of two countries and seven cities, which can be used for automated testing using JUnit.
 * <p>
 * Alleviates the problem of creating a Map<City, List<Road>> yourself.
 * <p>
 * Simply drag this file into your BlueJ-project.
 * You can then right-click on this class and choose 'Test Fixture to Object Bench' to create the necessary test objects.
 *
 * @author Nikolaj Ignatieff Schwartzbach
 * @version April 2017
 */
public class CGTest {
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
        country2 = new MafiaCountry("Country 2");
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
    public void bonus() {
        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            int noRobs = 0;
            int totalB = 0;
            Set<Integer> values = new HashSet<>();
            for(int i = 0; i<50000; i++) {
                int bonus = country2.bonus(80);
                if(bonus < 0) { // Robbery
                    robs++;
                    assertTrue(bonus * -1 >= 10 && bonus * -1 <= 50); //Lose between 10 and 50 only
                    loss -= bonus;
                    values.add(-bonus);
                }
                else { // No Robbery
                    noRobs++;
                    totalB += bonus;

                }
            }
            assertTrue(totalB/50000 <= 34 && totalB/50000 >= 30); //Bonus is calculated right
            assertTrue(robs >= 50000/100*18 && robs <= 50000/100*22); //Robs around 20% with 2% fail margin
            assertTrue(loss/robs <= 31 && loss/robs >= 29); //Loss is around 30%
            assertEquals(41, values.size()); //All numbers between 10 and 50
        }
    }
}

