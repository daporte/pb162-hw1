package cz.muni.fi.pb162.ships;

/**
 * @author jcechace
 */
public interface PlayingBoard {


    /**
     * Place a ship onto playing board facing given direction.
     * If the ship was placed successfully it's latitude and longitude is set.
     *
     * This method needs to call {@link Ship#setBoardPlacement(int, int, Direction)}. See its javadoc
     * for detailed information about latitude, longitude, and directions.
     *
     * @param ship ship
     * @param latitude x coordinate of ship (used as ship's latitude)
     * @param longitude y coordinate of ship (used as ship's longitude)
     * @param direction facing direction of given ship
     * @return true if ship could be placed on given position
     */
    boolean     place(Ship ship, int latitude, int longitude, Direction direction);

    /**
     * Retrieve ship at given coordinates from playing board
     *
     * @param latitude x coordinate of ship
     * @param longitude y coordinate of ship
     * @return ship or null
     */
    Ship get(int latitude, int longitude);

    /**
     * Fire at given coordinates
     *
     * This method needs to call {@link Ship#hit(int, int)}.
     * Note that the coordinates passed to {@link Ship#hit(int, int)} might be different.
     *
     * The diagram bellow should make it clear, it show an EAST facing Frigate placed on [1, 1] board coordinates.
     *
     *  3 | | | | |
     *  2 | | | | |
     *  1 | |*|X|*|
     *  0 | | | | |
     *     0 1 2 3
     *
     * X marks the hit coordinates [2, 1]. However when calling {@link Ship#hit(int, int)} the coordinates
     * should be transformed to match the ship (as it has no idea about the actual board size) -- in this case  [0, 1]
     *
     * @param latitude x coordinate
     * @param longitude y coordinate
     * @return instance of {@link Ship} or null if given coordinates are not occupied
     */
    Ship hit(int latitude, int longitude);

    /**
     * Return width of playing board.
     *
     * @return width of playing board
     */
    int getWidth();

    /**
     * Return height of playing board.
     *
     * @return height of playing board
     */
    int getHeight();
}
