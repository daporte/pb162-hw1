package cz.muni.fi.pb162.ships.impl.ships;

import cz.muni.fi.pb162.ships.Ship;

/**
 * @author jcechace
 */
public class FrigateTest extends ShipTest {

    public static final int EXPECTED_LENGTH = 5;
    public static final int EXPECTED_WIDTH = 1;

    private Ship ship = new Frigate();

    @Override
    public Ship getShip() {
        return ship;
    }

    @Override
    public int getExpectedLength() {
        return EXPECTED_LENGTH;
    }

    @Override
    public int getExpectedWidth() {
        return EXPECTED_WIDTH;
    }

}
