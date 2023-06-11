package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Abstract class representing a player
 */
public abstract class AbstractPlayer implements Player {
  protected BoardInteractions board;
  protected Random random;
  protected List<Coord> hits;
  protected List<Coord> allGuesses;



  /**
   * Constructor a player's abstract class
   *
   * @param board the Board object
   */
  AbstractPlayer(BoardInteractions board) {
    this.board = board;
    this.random = new Random();
    this.hits = new ArrayList<>();
    this.allGuesses = new ArrayList<>();

  }

  /**
   * Constructor with a seeded random
   *
   * @param board the Board object
   */
  AbstractPlayer(BoardInteractions board, Random random) {
    this.board = board;
    this.random = random;
    this.hits = new ArrayList<>();
    this.allGuesses = new ArrayList<>();
  }

  private List<Coord> makeAllGuesses() {
    List<Coord> guesses = new ArrayList<>();
    for (int i = 0; i < board.getBoardHeight(); i++) {
      for (int j = 0; j < board.getBoardWidth(); j++) {
        Coord addition = new Coord(j, i);
        guesses.add(addition);
      }
    }
    return guesses;
  }



  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "User";
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the list of ships on the players board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> ships = new ArrayList<>();
    List<ShipType> shipTypes = new ArrayList<>();

    for (ShipType s : specifications.keySet()) {
      for (int i = 0; i < specifications.get(s); i++) {
        shipTypes.add(s);
      }
    }
    shipTypes.sort(Comparator.naturalOrder());

    for (ShipType st : shipTypes) {
      boolean colliding = true;
      Ship tbd = null;
      while (colliding) {
        tbd = createShip(st, height, width);
        colliding = !tbd.isValid(ships);
      }
      ships.add(tbd);
    }
    this.board.setBoards(ships);
    this.allGuesses = makeAllGuesses();
    return ships;
  }


  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> damage = new ArrayList<>();
    board.updateUserBoard(opponentShotsOnBoard);

    for (Coord c : opponentShotsOnBoard) {
      for (int i = 0; i < board.getBoard().size(); i++) {
        Ship s = board.getBoard().get(i);
        if (s.isHit(c)) {
          damage.add(c);
        }
        if (s.isSunk()) {
          board.removeShip(s);
        }
      }
    }
    return damage;
  }


  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    hits.addAll(shotsThatHitOpponentShips);
    board.updateOpponentBoard(shotsThatHitOpponentShips);
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
  }


  /**
   * Creates a random ship in the bounds of the board
   *
   * @param type the type of ship
   * @param height board height
   * @param width board width
   *
   * @return a Ship object
   */
  private Ship createShip(ShipType type, int height, int width) {
    int x;
    int y;
    boolean pickedDirection = random.nextBoolean();

    if (pickedDirection) {
      y = random.nextInt(height);
      if (width - type.getShipSize() == 0) {
        x = 0;
      } else {
        x = random.nextInt(width - type.getShipSize());
      }
    } else {
      if (height - type.getShipSize() == 0) {
        y = 0;
      } else {
        y = random.nextInt(height - type.getShipSize());
      }
      x = random.nextInt(width);
    }
    // Set the board's dimensions
    board.setHeightWidth(height, width);

    return new Ship(type, y, x, pickedDirection);
  }




}
