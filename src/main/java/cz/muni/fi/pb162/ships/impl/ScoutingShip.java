package cz.muni.fi.pb162.ships.impl;

import cz.muni.fi.pb162.ships.ArmorState;

/**
 * TODO : create javadoc
 *
 * @author David Portes
 */
public class ScoutingShip extends ShipCore {

    public static final int LENGTH = 3;
    public static final int WIDTH = 1;
    public static final ArmorState ARMOR_STATE = ArmorState.SOUND;


    public ScoutingShip(){
        super(LENGTH, WIDTH, ARMOR_STATE);
    }

}
