package cz.muni.fi.pb162.ships.game;

/**
 * Exception to be used when it is not possible to place a ship on playing board
 *
 * @author jludvice
 */
class ImpossibleToPlace extends RuntimeException {
    /** Constructor
     *
     * @param message error message
     */
    public ImpossibleToPlace(String message) {
        super(message);
    }
}
