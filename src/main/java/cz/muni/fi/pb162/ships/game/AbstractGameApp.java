package cz.muni.fi.pb162.ships.game;

import java.util.Scanner;

/**
 * The class is a command line interface for {@link Game}
 * @author jcechace
 */
public abstract class AbstractGameApp {
    public static final String EXIT_CMD = ":q";
    public static final String SHOW_CMD = ":s";

    private Game game;

    /**
     * Constructor
     * @param game game to be played
     */
    public AbstractGameApp(Game game) {
        this.game = game;
    }

    /**
     * Accessor method
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Execute at the start of application run
     */
    private void start() {
        System.out.println("Welcome to The Ship game!");
        System.out.println("Type in \":q\" to quit the game.\n");
    }

    /**
     * Play the game
     */
    private void play() {
        Scanner scanner = new Scanner(System.in, "utf-8");
        String line = null;
        boolean run = true;

        do {
            System.out.print("Insert coordinates: ");
            line = scanner.nextLine();

            switch (line) {
                case EXIT_CMD:
                    run = false;
                    break;
                case SHOW_CMD:
                    game.printShipPlacement();
                    break;
                default:
                    playTurn(line);
                    game.printBoard();
            }

            System.out.println();
        } while (run);
    }

    /**
     * Run the app
     */
    public void run() {
        start();
        play();
    }

    /**
     * Process user's input and play a turn,
     * if coordinates are given, then attempt to hit that place,
     * otherwise the method will do nothing and return false.
     *
     * Valid coordinates have a form of two number separated by whitespace (e.g.: "5 7")
     *
     * @param input input string.
     * @return true if an attempt to hit was made, false otherwise
     */
    protected abstract boolean playTurn(String input);
}
