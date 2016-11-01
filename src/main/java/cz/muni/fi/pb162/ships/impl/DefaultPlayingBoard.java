package cz.muni.fi.pb162.ships.impl;

import cz.muni.fi.pb162.ships.Direction;
import cz.muni.fi.pb162.ships.PlayingBoard;
import cz.muni.fi.pb162.ships.Ship;

import java.util.ArrayList;

/**
 * TODO : create javadoc
 *
 * @author David Portes
 */
public class DefaultPlayingBoard implements PlayingBoard {

    private int height;
    private int width;

    private BoardTile[][] board;

    public DefaultPlayingBoard(int height, int width){
        this.height = height;
        this.width = width;
        board = new BoardTile[height][width];
    }

    public boolean place(Ship ship, int latitude, int longitude, Direction direction) {

        if(addToBoard(ship, latitude, longitude)){
            ship.setBoardPlacement(latitude, longitude, direction);
            return true;
        }

        return false;
    }

    private boolean addToBoard(Ship ship, int latitude, int longitude){

        if(canBeAdded(ship, latitude, longitude)){
            placeOnBoard(ship, latitude, longitude);
            return true;
        } else {
            return false;
        }
    }
    /*
    private void placeOnBoard(Ship ship, int latitude, int longitude, int relLat, int relLon){
        for(int i=0;i<Math.abs(relLat);i++){
            for(int j=0;j<Math.abs(relLon);j++){
                int currentLat = latitude + i * Integer.signum(relLat);
                int currentLon = longitude + j * Integer.signum(relLon);

                board[currentLat][currentLon] = new BoardTile(ship, i, j);
            }
        }
    }
    */

    private void placeOnBoard(Ship ship, int latitude, int longitude) {
        for (int i = 0; i < ship.getLength(); i++) {
            for (int j = 0; j < ship.getWidth(); j++) {
                switch (ship.getDirection()){
                    case NORTH:
                        board[latitude + i][longitude + j] = new BoardTile(ship, i, j);
                    case EAST:
                        board[longitude + i][latitude + j] = new BoardTile(ship, i, j);
                    case SOUTH:
                        board[latitude - i][longitude - j] = new BoardTile(ship, i, j);
                    case WEST:
                        board[longitude - i][latitude - j] = new BoardTile(ship, i, j);
                }
            }
        }
    }

    private boolean canBeAdded(Ship ship, int latitude, int longitude){
        for (int i = 0; i < ship.getLength(); i++) {
            for (int j = 0; j < ship.getWidth(); j++) {
                int latitudeToCheck = 0;
                int longitudeToCheck = 0;
                switch (ship.getDirection()){
                    case NORTH:
                        latitudeToCheck = latitude + i;
                        longitudeToCheck = longitude + j;
                    case EAST:
                        latitudeToCheck = longitude + i;
                        longitudeToCheck = latitude + j;
                    case SOUTH:
                        latitudeToCheck = latitude - i;
                        longitudeToCheck = longitude - j;
                    case WEST:
                        latitudeToCheck = longitude - i;
                        longitudeToCheck = latitude - j;
                }
                if(isOccupied(latitudeToCheck, longitudeToCheck) || !isInBounds(latitudeToCheck, longitudeToCheck)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isOccupied(int latitude, int longitude){
        if(get(latitude, longitude) == null){
            return false;
        } else {
            return true;
        }
    }

    private boolean isInBounds(int latitude, int longitude){
        return latitude >= 0 && latitude < height && longitude >= 0 && longitude < width;
    }

    public Ship get(int latitude, int longitude) {

        return board[latitude][longitude].getShip();
    }

    public Ship hit(int latitude, int longitude) {
        BoardTile tile = board[latitude][longitude];
        Ship ship = tile.getShip();
        ship.hit(tile.getShipX(), tile.getShipY());
        return ship;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
