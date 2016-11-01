package cz.muni.fi.pb162.ships.impl;

import cz.muni.fi.pb162.ships.ArmorState;

/**
 * TODO : create javadoc
 *
 * @author David Portes
 */
public class Frigate extends ShipCore {

    public static final int LENGTH = 5;
    public static final int WIDTH = 1;
    public static final ArmorState ARMOR_STATE = ArmorState.SOUND;


    public Frigate(){
        super(LENGTH, WIDTH, ARMOR_STATE);
    }

}
