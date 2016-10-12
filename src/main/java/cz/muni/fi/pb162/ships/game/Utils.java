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

    private Random r = new Random(System.currentTimeMillis());

    /**
     * Return random numer {@code [0,max]}.
     *
     * @param max max value inclusive
     * @return {@code x in [0,max]}
     */
    public int next(int max) {
        return r.nextInt(max + 1);
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
     * @param s ship to be placed
     * @param b board
     */
    public void placeShip(Ship s, PlayingBoard b) {
        int minX = 0, maxX = 0;
        int minY = 0, maxY = 0;

        Utils u = new Utils();

        int retryCount = 0;
        boolean placed = false;

        int direction = next(Direction.values().length - 1);


        while (!placed) {
            Direction d = Direction.values()[direction];

            switch (d) {
                case NORTH:
                    minX = 0;
                    maxX = b.getWidth() - s.getWidth();
                    minY = 0;
                    maxY = b.getHeight() - s.getLength();
                    break;
                case SOUTH:
                    minX = s.getWidth() - 1;
                    maxX = b.getWidth() - 1;
                    minY = s.getLength() - 1;
                    maxY = b.getHeight() -1;
                    break;
                case EAST:
                    minX = 0;
                    maxX = b.getWidth() - s.getLength();
                    minY = s.getWidth() -1;
                    maxY = b.getHeight() - 1;
                    break;
                case WEST:
                    minX = s.getLength() - 1;
                    maxX = b.getWidth() - 1;
                    minY = 0;
                    maxY = b.getHeight() - s.getWidth();
                    break;
            }

            int x = u.next(minX, maxX);
            int y = u.next(minY, maxY);
            placed = b.place(s, x, y, d);
            retryCount++;
            if (retryCount > 100) {
                throw new ImpossibleToPlace("Couldn't place ship after " + retryCount + " retries. Giving up.");
            }
        }
    }
}
