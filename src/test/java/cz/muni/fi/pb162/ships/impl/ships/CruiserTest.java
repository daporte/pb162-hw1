package cz.muni.fi.pb162.ships.impl.ships;

import cz.muni.fi.pb162.ships.Ship;
import cz.muni.fi.pb162.ships.ArmorState;

/**
 * @author jcechace
 */
public class CruiserTest extends ShipTest {

    public static final int EXPECTED_LENGTH = 5;
    public static final int EXPECTED_WIDTH = 2;
    public static final ArmorState[] EXPECTED_ARMOR = {ArmorState.REINFORCED, ArmorState.SOUND, ArmorState.DESTROYED };

    private Ship ship = new Cruiser();

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

    @Override
    public ArmorState[] getExpectedArmor() {
        return EXPECTED_ARMOR;
    }

}
