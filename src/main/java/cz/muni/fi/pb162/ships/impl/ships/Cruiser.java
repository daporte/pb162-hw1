package cz.muni.fi.pb162.ships.impl.ships;

import cz.muni.fi.pb162.ships.ArmorState;

/**
 * TODO : create javadoc
 *
 * @author David Portes
 */
public class Cruiser extends ShipCore {

    public static final int LENGTH = 5;
    public static final int WIDTH = 2;
    public static final ArmorState ARMOR_STATE = ArmorState.REINFORCED;


    public Cruiser(){
        super(LENGTH, WIDTH, ARMOR_STATE);
    }

}
