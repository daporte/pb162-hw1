package cz.muni.fi.pb162.ships.game;

import cz.muni.fi.pb162.ships.Direction;
import cz.muni.fi.pb162.ships.PlayingBoard;
import cz.muni.fi.pb162.ships.Ship;

import java.util.Random;

/**
 * @author jludvice
 * @author jcechace
 */
class Utils {

    private Random random = new Random(System.currentTimeMillis());

    /**
     * Return random numer {@code [0,max]}.
     *
     * @param max max value inclusive
     * @return {@code x in [0,max]}
     */
    public int next(int max) {
        return random.nextInt(max + 1);
    }

    /**
     * Return random number {@code x in [min,max]}
     *
     * @param min min value inclusive
     * @param max max value inclusive
     * @return {@code x in [min,max]}
     */
    public int next(int min, int max) {
        return min + next(max - min);
    }

    /**
     * Attempt to randomly place the ship on given board
     *
     * @param ship ship to be placed
     * @param board board
     */
    public void placeShip(Ship ship, PlayingBoard board) {
        int minX = 0, maxX = 0;
        int minY = 0, maxY = 0;

        int retryCount = 0;
        boolean placed = false;

        int direction = next(Direction.values().length - 1);


        while (!placed) {
            Direction d = Direction.values()[direction];

            switch (d) {
                case NORTH:
                    minX = 0;
                    maxX = board.getWidth() - ship.getWidth();
                    minY = 0;
                    maxY = board.getHeight() - ship.getLength();
                    break;
                case SOUTH:
                    minX = ship.getWidth() - 1;
                    maxX = board.getWidth() - 1;
                    minY = ship.getLength() - 1;
                    maxY = board.getHeight() -1;
                    break;
                case EAST:
                    minX = 0;
                    maxX = board.getWidth() - ship.getLength();
                    minY = ship.getWidth() -1;
                    maxY = board.getHeight() - 1;
                    break;
                case WEST:
                    minX = ship.getLength() - 1;
                    maxX = board.getWidth() - 1;
                    minY = 0;
                    maxY = board.getHeight() - ship.getWidth();
                    break;
            }

            int x = next(minX, maxX);
            int y = next(minY, maxY);
            placed = board.place(ship, x, y, d);
            retryCount++;
            if (retryCount > 100) {
                throw new ImpossibleToPlace("Couldn't place ship after " + retryCount + " retries. Giving up.");
            }
        }
    }
}
