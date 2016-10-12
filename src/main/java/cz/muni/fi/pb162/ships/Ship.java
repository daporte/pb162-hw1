package cz.muni.fi.pb162.ships;

/**
 * A description of an arbitrary ship.
 *
 * Similarly to well know pen&paper game of ships, the main aspects of ship are its size (length, width)
 * and the armor status of it's parts.
 *
 * @author jcechace
 */
public interface Ship {

    /**
     * @return length of the ship
     */
    int getLength();

    /**
     * @return width of the ship
     */
    int getWidth();

    /**
     * Set the latitude, longitude, and direction of this ship on a playing board.
     *
     * @param latitude x-coordinate of reference point on playing board
     * @param longitude y-coordinate of reference point on playing board
     * @param direction direction of the ship
     *
     * For NORTH facing ship, latitude and longitude are the coordinates of bottom-left cell. Coordinates for
     * other directions are determined by simple clockwise rotation.
     * The Diagrams bellow should help in understanding what is the reference point for each direction.
     *
     *  NORTH           EAST                SOUTH           WEST
     *
     *                      Smaller ship example (e.g. Frigate of length 3 and width 1)
     *  _____                               _____
     *  |   |                               | X |
     *  -----                               -----
     *  |   |                               |   |
     *  -----           -------------       -----           -------------
     *  | X |           | X |   |   |       |   |           |   |   | X |
     *  -----           -------------       -----           -------------
     *
     *                      Bigger ship example (e.g. Cruiser of length 3 and width 2)
     *  _________                           _________
     *  |   |   |                           |   | X |
     *  ---------       -------------       ---------       -------------
     *  |   |   |       | X |   |   |       |   |   |       |   |   |   |
     *  ---------       -------------       ---------       -------------
     *  | X |   |       |   |   |   |       |   |   |       |   |   | X |
     *  ---------       -------------       ---------       -------------
     *
     */
    void setBoardPlacement(int latitude, int longitude, Direction direction);

    /**
     * Indicates whether the ship was placed on a playing board.
     *
     * Contract: if ship was placed on board its longitude, latitude, and Direction must be set
     * @return true if this ship is on a playing board.
     */
    boolean isPlacedOnBoard();

    /**
     * Return the longitude of the ship
     * based on reference point
     *
     * @return y coordinate of reference point
     */
    int getLongitude();


    /**
     * Return the latitude of the ship
     * based on reference point.
     *
     * @return x coordinate of reference point
     */
    int getLatitude();

    /**
     * Return ship's direction
     *
     * @return direction
     */
    Direction getDirection();

    /**
     * Get a status of armor at given part of the ship
     *
     * @param x x coordinate of ship's cell (min: 0, max: {@link #getWidth()} - 1)
     * @param y y coordinate of ship's cell (min: 0, max: {@link #getLength()} - 1)
     * @return state of ship's armor at given cell
     */
    ArmorState getArmor(int x, int y);

    /**
     * Hit the ship. After part of the ship is hit, it's armor is lowered (unless already destroyed).
     * See {@link ArmorState} for details.
     *
     * @param x x coordinate of ship's cell (min: 0, max: {@link #getWidth()} - 1)
     * @param y y coordinate of ship's cell (min: 0, max: {@link #getLength()} - 1)
     * @return armor state of hit part of the ship or null if coordinates are out of scope
     */
    ArmorState hit(int x, int y);

    /**
     * Indicates whether the ship was destroyed
     *
     * Ship is destroyed if {@link #getArmor(int, int)} returns {@link ArmorState#DESTROYED}
     * for the entirety of ship's coordinates.
     *
     * @return true if ship is destroyed, false otehrwise.
     */
    boolean isDestroyed();
}
