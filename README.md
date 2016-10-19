Homework assignment no. 1, Ships
====================================

**Publication date:** 13th October 2016

**Submission deadline:** 3rd November 2016

General information
-------------------
The goal of this assignment is to write an implementation (or at least the important part ;) for well know [game of ships](https://en.wikipedia.org/wiki/Battleship_(game)).

The maximum number of points for this assignment is **7**.

- **4 points** for passing tests (attached tests do not guarantee a 100% correctness).
- **2 points** for correct implementation (evaluated by your class teacher).
- **1 points** for clean and elegant implementation (code conventions, minimal code repetition).

In cases **when provided tests do not pass** with submitted solution you can be granted no more than **4 points** (this means that you can be granted **0 points** as well)!

### Preconditions
To succesfuly implement this assignment you need to know the following

1. What is the difference between class and object and how to work with both.
2. What are interfaces and how to use them.
3. What is inheritance and how it helps to avoid duplicities in your code.
4. How to use basic control structures such as if, while and switch.

### Project structure
The structure of project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.ships``` contains classes and interfaces provided as part of the assignment.
  - **Do not modify or add any classes or subpackages into this package.**
2. Package  ```cz.muni.fi.pb162.ships.impl``` should contain your implementation.
  - **Anything outside this package will be ignored during evaluation.**

### Names in this document
Unless fully classified name is provided, all class names are relative to  package ```cz.muni.fi.pb162.ships``` or ```cz.muni.fi.pb162.ships.impl``` for classes implemented as part of your solution.

### Assignment description/documentation
The following is only a high-level description of given assignment. For specifics please consult the detailed javadoc documentation provided for each class and interface.

### Compiling the project
The project can be compiled and packaged in the same way you already know

```bash
$ mvn clean compile
```

The only difference is, that unlike with seminar project, this time checks for missing documentation and style violation will produce an error.
You can temporarily disable this behavior when running this command.

```bash
$ mvn clean compile -Dcheckstyle.fail=false
```

You can consult your seminar teacher to help you set the ```checkstyle.fail``` property in your IDE (or just google it).


### Submitting the assignment
The procedure to submit your solution may differ based on your seminar group. However generally it should be ok to submit ```target/hw01-ships-1.0-SNAPSHOT-sources.jar``` to the homework vault.
While working on ths assignment you are free to create any additional classes/interfaces/methods/... as long as they are kept within the ```cz.muni.fi.pb162.ships.impl``` package

Step 1: Implement Ship interface
---------------------------
Create a few different implementations of ```cz.muni.fi.pb162.ships.Ship``` interface. These classes will represent various ships used in our game.
We need multiple implementations as the ship types are different in some aspects.

Create classes for the following ships:

| Class name | Length | Width | Default armor status |
| ---------- | -------| ----- | ---------------------|
| cz.muni.fi.pb162.ships.impl.ships.ScoutingShip    | 3 | 1 | SOUND |
| cz.muni.fi.pb162.ships.impl.ships.Frigate         | 5 | 1 | SOUND |
| cz.muni.fi.pb162.ships.impl.ships.Cruiser         | 5 | 2 | REINFORCED |

**Note:** Notice that cruiser are tougher compared to other ships. After consulting the javadocs, you could probably tell what it means. ```REINFORCED``` parts of the ship need to be hit twice before they are destroyed.
All different states of ship's armor are described in ```cz.munii.fi.pb162.ships.ArmorState```.

 ```Ship``` implementations are expected to have a parameterless constructor.
The actual name of the constructor may (and will) vary and you are free to add any other constructor.

**Hint:** Think about what will be the actual difference between these classes and what aspects of Java can you utilise in order to achieve optimal implementation.


Step 2: Implement The playing Board
---------------------------
Ships alone would not do much good for our game. To play we need to implement the playing board.
Create an implementation of ```cz.muni.fi.pb162.ships.PlayingBoard``` called ```DefaultPlayingBoard```.

This task should be pretty straightforward, the only notable methods prescribed by ```PlayingBoard``` are

```java
    /**
     * Place a ship onto playing board facing given direction.
     * If the ship was placed successfully it's latitude and longitude is set.
     *
     * See javadoc of {@link Ship#setBoardPlacement(int, int, Direction)} for detailed information about
     * latitude, longitude, and directions.
     *
     * @param ship ship
     * @param latitude x coordinate of ship (used as ship's latitude)
     * @param longitude y coordinate of ship (used as ship's longitude)
     * @param direction facing direction of given ship
     * @return true if ship could be placed on given position
     */
    boolean place(Ship ship, int latitude, int longitude, Direction direction);
```
This method is used to place a ship on the board so that the ship is placed on given coordinates and facing given direction. This method is related and should internally call
```Ship#setBoardPlacement(int, int, Direction)``` -- read its javadoc and make sure you understand what is the relation between different attributes of Ships and Playing board.

The other significant method in ```Ship``` interface is

```java
    /**
     * Fire at given coordinates
     *
     * This method needs to call {@link Ship#hit(int, int)}.
     * Note that the coordinates passed to {@link Ship#hit(int, int)} might be different.
     *
     *  ... omitted ...
     *
     * @param latitude x coordinate
     * @param longitude y coordinate
     * @return instance of {@link Ship} or null if given coordinates are not occupied
     */
    Ship hit(int latitude, int longitude);
```
The purpose of this method should be clear. However a notable things are the coordinates which are relative to the dimension of the entire board.
Once again the actual javadoc should clarify this.

Step 3: Implement the runnable program
--------------------------------------
We now have a complete game, except we can't really play it because our program doesn't have an executable entry point.
Start by creating a class called ```GameApp``` and make it inherit from ```AbstractGameApp```. Methods inherited from this base class 
will deal with stuff like user input or randomizing the placement of ships on playing board for you. 

There is only single which you are required to implement:

```java
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
```
This method will take a String input, parse it for coordinates and hit the playing board. 

Finally the last thing left is to add a main method to our ```GameApp```. In this method we should create and run a game.
The following code is an obfuscated example of how such main method should look like. Notice the last two lines -- we are creating 
an instance of the class containing the main method itself. It may seem strange, however it's quite common practice. 
 ```java
     /**
      * Program entry point
      * @param args command line arguments (not supported)
      */
     public static void main(String[] args) {
         PlayingBoard board = ...;
         Game g = ...;
         g.placeShips(...);
 
         AbstractGameApp app = new GameApp(g);
         app.run();
     }
 ```
