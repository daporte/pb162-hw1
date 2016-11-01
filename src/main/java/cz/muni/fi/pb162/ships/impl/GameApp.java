package cz.muni.fi.pb162.ships.impl;

import cz.muni.fi.pb162.ships.PlayingBoard;
import cz.muni.fi.pb162.ships.game.AbstractGameApp;
import cz.muni.fi.pb162.ships.game.Game;

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


        return false;
    }

    public static void main(String[] args){
        PlayingBoard board = new DefaultPlayingBoard(100, 100);
        Game g = new Game(board);
        g.placeShips(new Frigate(), new Cruiser(), new ScoutingShip());
        AbstractGameApp app = new GameApp(g);
        app.run();
    }
}
