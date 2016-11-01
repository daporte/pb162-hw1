package cz.muni.fi.pb162.ships.impl;

import cz.muni.fi.pb162.ships.Ship;

/**
 * TODO : create javadoc
 *
 * @author David Portes
 */
public class BoardTile {

    private Ship ship;
    private int shipX;
    private int shipY;

    public BoardTile(Ship ship, int x, int y){
        this.ship = ship;
        this.shipX = x;
        this.shipY = y;
    }

    public Ship getShip() {
        return ship;
    }

    public int getShipX() {
        return shipX;
    }

    public int getShipY() {
        return shipY;
    }
}
