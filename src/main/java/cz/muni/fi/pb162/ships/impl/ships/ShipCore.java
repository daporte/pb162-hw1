package cz.muni.fi.pb162.ships.impl.ships;

import cz.muni.fi.pb162.ships.ArmorState;
import cz.muni.fi.pb162.ships.Direction;
import cz.muni.fi.pb162.ships.Ship;

import java.util.Arrays;

/**
 * TODO : create javadoc
 *
 * @author David Portes
 */
public abstract class ShipCore implements Ship{



    private int length;
    private int width;
    private ArmorState[][] shipHull;

    private int longitude;
    private int latitude;
    private Direction direction;

    private boolean isPlaced = false;


    public ShipCore(int length, int width, ArmorState armorState){
        this.length = length;
        this.width = width;
        shipHull = new ArmorState[length][width];
        initializeHull(armorState);
    }

    private void initializeHull(ArmorState armorState){
        for(ArmorState[] row: shipHull){
            Arrays.fill(row, armorState);
        }
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public void setBoardPlacement(int latitude, int longitude, Direction direction) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
        isPlaced = true;
    }

    public boolean isPlacedOnBoard() {
        return isPlaced;
    }

    public int getLongitude() {
        return latitude;
    }

    public int getLatitude() {
        return longitude;
    }

    public Direction getDirection() {
        return direction;
    }

    public ArmorState getArmor(int x, int y) {
        return shipHull[x][y];
    }

    public ArmorState hit(int x, int y) {
        ArmorState hitSection = shipHull[x][y];
        try {
            hitSection = ArmorState.values()[hitSection.ordinal() + 1];
        } catch (Exception e){

        }
        return hitSection;
    }

    public boolean isDestroyed() {
        for(int i=0;i<shipHull.length;i++){
            for(int j=0;j<shipHull[i].length;j++) {
                if(shipHull[i][j] != ArmorState.DESTROYED){
                    return false;
                }
            }
        }
        return true;
    }
}
