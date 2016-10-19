package cz.muni.fi.pb162.ships.game;

import cz.muni.fi.pb162.ships.PlayingBoard;
import cz.muni.fi.pb162.ships.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a simple game of ships
 *
 * @author jludvice
 * @author jcechace
 */
public class Game {

    private final List<Ship> placedShips;
    private final PlayingBoard board;
    private boolean[][] map;
    private Integer lastHitX;
    private Integer lastHitY;

    private Utils u = new Utils();

    /**
     * Constructor
     *
     * @param board playing board for this game
     */
    public Game(PlayingBoard board) {
        this.board = board;
        this.placedShips = new ArrayList<>();

        int width = board.getWidth();
        int height = board.getHeight();
        this.map = new boolean[width][height];
    }

    /**
     * Add a ship to this game
     * @param ships ship to be added into the game
     */
    public void placeShips(Ship... ships) {
        for (Ship s : ships) {
            u.placeShip(s, board);
            placedShips.add(s);
        }
    }

    /**
     * Fire at given coordinates on playing board associated with this game.
     *
     * @param x x coordinate (latitude)
     * @param y y coordinate (longitude)
     * @return hit ship or none
     */
    public Ship hit(int x, int y) {
        lastHitX = x;
        lastHitY = y;
        Ship s = board.hit(x, y);
        if (s != null) {
            map[x][y] = true;
        }
        if (s != null) {
            System.out.println("Congratulation, it's a hit!");
        } else {
            System.out.println("You missed!");
        }
        return s;
    }

    /**
     * Print the visual representation of playing board
     */
    public void printBoard() {
        for (int y = board.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (x == lastHitX && y == lastHitY) {
                    System.out.print("|x");
                } else {
                    System.out.print(map[x][y] ? "|*" : "| ");
                }
                if (x == board.getWidth() - 1) {
                    System.out.println("|");
                }
            }
        }
    }

    /**
     * Print the location of ships on playing board
     */
    public void printShipPlacement() {
        for (int y = board.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < board.getWidth(); x++) {
                System.out.print(board.get(x, y) != null ? "|*" : "| ");
                if (x == board.getWidth() - 1) {
                    System.out.println("|");
                }
            }
        }
    }
}
