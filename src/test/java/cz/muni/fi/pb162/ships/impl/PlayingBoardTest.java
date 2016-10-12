package cz.muni.fi.pb162.ships.impl;

import static org.assertj.core.api.Assertions.assertThat;

import cz.muni.fi.pb162.ships.ArmorState;
import cz.muni.fi.pb162.ships.Direction;
import cz.muni.fi.pb162.ships.PlayingBoard;
import cz.muni.fi.pb162.ships.Ship;
import cz.muni.fi.pb162.ships.impl.ships.Frigate;
import cz.muni.fi.pb162.ships.impl.ships.ScoutingShip;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jcechace
 */
public class PlayingBoardTest {

    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;

    private PlayingBoard board;
    private Ship[][] initialSnaphost;

    @Before
    public void setUp() {
        board = new DefaultPlayingBoard(WIDTH, HEIGHT);
        initialSnaphost = snapshotBoard();
    }

    @Test
    public void hasCorrectDimensions() {
        assertThat(board.getWidth()).isEqualTo(WIDTH);
        assertThat(board.getHeight()).isEqualTo(HEIGHT);
    }

    @Test
    public void isCreatedEmpty() {
        assertClearBoard();
    }

    @Test
    public void placeShipCorrectly() {
        // given
        Ship s = new Frigate();
        // then
        placeShipAndAssert(s, 0, 0, Direction.NORTH, true);
    }

    @Test
    public void hitPlacedShip() {
        // given
        Ship s = new Frigate();
        board.place(s, 1, 1, Direction.NORTH);
        // when
        Ship hit1 = board.hit(1, 1);
        Ship hit2 = board.hit(1, 3);
        // then
        assertThat(s).isSameAs(hit1).isSameAs(hit2);
        assertThat(s.getArmor(0, 0)).isEqualTo(ArmorState.DESTROYED);
        assertThat(s.getArmor(0, 2)).isEqualTo(ArmorState.DESTROYED);
    }

    @Test
    public void placeShipPartiallyOutsideNorth() {
        // given
        Ship s = new Frigate();
        // then
        Ship[][] snapshot = placeShipAndAssert(s, 1, 3, Direction.NORTH, false);
        assertThat(snapshot).isEqualTo(initialSnaphost);
    }

    @Test
    public void placeShipPartiallyOutsideEast() {
        // given
        Ship s = new Frigate();
        // then
        Ship[][] snapshot = placeShipAndAssert(s, 3, 1, Direction.EAST, false);
        assertThat(snapshot).isEqualTo(initialSnaphost);
    }

    @Test
    public void placeShipPartiallyOutsideSouth() {
        // given
        Ship s = new Frigate();
        // then
        Ship[][] snapshot = placeShipAndAssert(s, 1, 1, Direction.SOUTH, false);
        assertThat(snapshot).isEqualTo(initialSnaphost);
    }

    @Test
    public void placeShipPartiallyOutsideWest() {
        // given
        Ship s = new Frigate();
        // then
        Ship[][] snapshot = placeShipAndAssert(s, 1, 1, Direction.WEST, false);
        assertThat(snapshot).isEqualTo(initialSnaphost);
    }

    @Test
    public void placeOverlappingShips() {
        // given
        Ship s1 = new ScoutingShip();
        Ship s2 = new ScoutingShip();
        // then
        Ship[][] before = placeShipAndAssert(s1, 1, 1, Direction.NORTH, true);
        Ship[][] after = placeShipAndAssert(s1, 0, 1, Direction.WEST, false);
        assertThat(after).isEqualTo(before);
    }

    private Ship[][] placeShipAndAssert(Ship s, int latitude, int longitude, Direction direction, boolean outcome) {
        // when
        boolean placed = board.place(s, latitude, longitude, direction);
        // then
        assertThat(placed).isEqualTo(outcome);
        return snapshotBoard();
    }

    private void assertClearBoard() {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y ++) {
                assertThat(board.get(x, y)).isNull();
            }
        }
    }

    private Ship[][] snapshotBoard() {
        Ship[][] snapshot = new Ship[board.getWidth()][board.getHeight()];

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y ++) {
                snapshot[x][y] = board.get(x, y);
            }
        }

        return snapshot;
    }

}
