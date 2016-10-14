package cz.muni.fi.pb162.ships.impl.ships;

import cz.muni.fi.pb162.ships.Direction;
import cz.muni.fi.pb162.ships.Ship;
import cz.muni.fi.pb162.ships.ArmorState;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author jcechace
 */
public abstract class ShipTest {

    public static final int EXPECTED_LONGITUDE = 3;
    public static final int EXPECTED_LATITUDE = 5;
    public static final ArmorState[] EXPECTED_ARMOR = {ArmorState.SOUND, ArmorState.DESTROYED};
    public static final Direction EXPECTED_DIRECTION = Direction.EAST;

    private Ship ship;
    private int expectedLength;
    private int expectedWidth;
    private ArmorState[] expectedArmor;

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    abstract public Ship getShip();

    abstract public int getExpectedLength();

    abstract public int getExpectedWidth();

    public ArmorState[] getExpectedArmor() {
        return EXPECTED_ARMOR;
    }

    @FunctionalInterface
    public interface ShipPartAction {
        void execute(Ship ship, int x, int y);
    }

    @Before
    public void setUp() {
        this.ship = getShip();
        this.expectedLength = getExpectedLength();
        this.expectedWidth = getExpectedWidth();
        this.expectedArmor = getExpectedArmor();
    }

    @Test
    public void testFreshShipInstance() {
        softly.assertThat(ship.getLength())
                .as("Check the expectedLength")
                .isEqualTo(expectedLength);
        softly.assertThat(ship.getWidth())
                .as("Check the expectedWidth")
                .isEqualTo(expectedWidth);
        softly.assertThat(ship.getDirection())
                .as("Check the initial direction")
                .isEqualTo(null);
        softly.assertThat(ship.isDestroyed())
                .as("Check the ship is not destroyed ")
                .isEqualTo(false);
        softly.assertThat(ship.isPlacedOnBoard())
                .as("Check if the ship is placed on a playing board")
                .isEqualTo(false);
    }

    @Test
    public void testBoardPlacement() {
        ship.setBoardPlacement(EXPECTED_LATITUDE, EXPECTED_LONGITUDE, EXPECTED_DIRECTION);

        softly.assertThat(ship.getLongitude())
                .as("Check the ship is at correct longitude")
                .isEqualTo(EXPECTED_LONGITUDE);
        softly.assertThat(ship.getLatitude())
                .as("Check the ship is at correct latitude")
                .isEqualTo(EXPECTED_LATITUDE);
        softly.assertThat(ship.getDirection())
                .as("Check the ship is facing a correct direction")
                .isEqualTo(EXPECTED_DIRECTION);
    }

    @Test
    public void testShipArmor() {
        ship.setBoardPlacement(0, 0, Direction.NORTH);
        testShipParts(this::testConsecutiveArmorHits);
    }

    @Test
    public void testShipArmorWithDifferentBoardCoordinates() {
        ship.setBoardPlacement(1, 1, Direction.EAST);
        testShipParts(this::testConsecutiveArmorHits);
    }

    private void testShipParts(ShipPartAction action) {
        for (int x = 0; x < ship.getWidth(); x++) {
            for (int y = 0; y < ship.getLength(); y++) {
                action.execute(ship, x, y);
            }
        }
    }

    private void testConsecutiveArmorHits(Ship ship, int x, int y) {
        for (int hit = 0; hit < expectedArmor.length; hit++) {
            softly.assertThat(ship.getArmor(x, y))
                    .as("Check armor after hit #" + hit + " at coordinates [" + x + ", " + y + "]")
                    .isEqualTo(expectedArmor[hit]);
            ship.hit(x, y);
        }
    }



}
