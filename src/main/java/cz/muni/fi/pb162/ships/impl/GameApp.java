package cz.muni.fi.pb162.ships.impl;

import cz.muni.fi.pb162.ships.Direction;
import cz.muni.fi.pb162.ships.PlayingBoard;
import cz.muni.fi.pb162.ships.Ship;
import cz.muni.fi.pb162.ships.game.AbstractGameApp;
import cz.muni.fi.pb162.ships.game.Game;
import cz.muni.fi.pb162.ships.impl.ships.Cruiser;
import cz.muni.fi.pb162.ships.impl.ships.Frigate;
import cz.muni.fi.pb162.ships.impl.ships.ScoutingShip;

/**
 * TODO : create javadoc
 *
 * @author David Portes
 */
public class GameApp extends AbstractGameApp {

    public GameApp(Game g){
        super(g);
    }

    protected boolean playTurn(String input) {

        String[] coords = input.split(" ");
        getGame().hit(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));

        return false;
    }

    public static void main(String[] args){
        PlayingBoard board = new DefaultPlayingBoard(10, 10);
        Game g = new Game(board);
        g.placeShips(new Frigate(), new Cruiser(), new ScoutingShip());
        Ship myShip = new Frigate();
        board.place(myShip, 0, 0, Direction.NORTH);
        AbstractGameApp app = new GameApp(g);
        app.run();
    }
}
