package cz.muni.fi.pb162.ships.impl;

import cz.muni.fi.pb162.ships.ArmorState;
import cz.muni.fi.pb162.ships.Direction;
import cz.muni.fi.pb162.ships.PlayingBoard;
import cz.muni.fi.pb162.ships.Ship;

import java.util.ArrayList;
import java.util.Arrays;

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
        board = new BoardTile[width][height];
    }



    public boolean place(Ship ship, int latitude, int longitude, Direction direction) {
        ship.setBoardPlacement(latitude, longitude, direction);
        if(addToBoard(ship, latitude, longitude)){

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


    private void placeOnBoard(Ship ship, int latitude, int longitude) {

        for (int i = 0; i < ship.getWidth(); i++) {
            for (int j = 0; j < ship.getLength(); j++) {

                switch (ship.getDirection()){
                    case NORTH:
                        board[latitude + i][longitude + j] = new BoardTile(ship, i, j);
                        break;
                    case EAST:
                        board[latitude + j][longitude - i] = new BoardTile(ship, i, j);
                        break;
                    case SOUTH:
                        board[latitude - i][longitude - j] = new BoardTile(ship, i, j);
                        break;
                    case WEST:
                        board[latitude - j][longitude + i] = new BoardTile(ship, i, j);
                        break;
                }
            }
        }
    }

    private boolean canBeAdded(Ship ship, int latitude, int longitude){
        for (int i = 0; i < ship.getWidth(); i++) {
            for (int j = 0; j < ship.getLength(); j++) {
                int latitudeToCheck = 0;
                int longitudeToCheck = 0;

                switch (ship.getDirection()){
                    case NORTH:
                        latitudeToCheck = latitude + i;
                        longitudeToCheck = longitude + j;
                        break;
                    case EAST:
                        latitudeToCheck = latitude + j;
                        longitudeToCheck = longitude - i;
                        break;
                    case SOUTH:
                        latitudeToCheck = latitude - i;
                        longitudeToCheck = longitude - j;
                        break;
                    case WEST:
                        latitudeToCheck = latitude - j;
                        longitudeToCheck = longitude + i;
                        break;
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
        return latitude >= 0 && latitude < width && longitude >= 0 && longitude < height;
    }

    public Ship get(int latitude, int longitude) {
        try{
            return board[latitude][longitude].getShip();
        } catch (Exception e){
            return null;
        }



    }

    public Ship hit(int latitude, int longitude) {
        BoardTile tile = board[latitude][longitude];
        if(tile == null){
            return null;
        }
        Ship ship = tile.getShip();
        if(ship == null){
            return null;
        }

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
