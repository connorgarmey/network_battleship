package cs3500.pa04.model;

import static cs3500.pa04.model.CellType.EMPTY;
import static cs3500.pa04.model.CellType.HIT;
import static cs3500.pa04.model.CellType.MISS;
import static cs3500.pa04.model.CellType.SHIP;

import cs3500.pa04.control.json.FleetJson;
import cs3500.pa04.control.json.ShipJson;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a game baord
 */
public class BoardImpl implements BoardInteractions {
  private List<Ship> board;
  private CellType[][] user;
  private CellType[][] opponent;
  private int height;
  private int width;

  /**
   * Constructor for board
   */
  public BoardImpl() {
    this.board = new ArrayList<>();
    this.opponent = null;
    this.user = null;
  }


  /**
   * Sets up the boards
   *
   * @param ships the ships to place
   */
  public void setBoards(List<Ship> ships) {
    board = ships;
    user = new CellType[height][width];
    opponent = new CellType[height][width];
    List<Coord> coordList = new ArrayList<>();

    for (Ship ship : ships) {
      coordList.addAll(ship.getLocations());
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (coordList.contains(new Coord(x, y))) {
          user[y][x] = SHIP;
        } else {
          user[y][x] = EMPTY;
        }
        opponent[y][x] = EMPTY;
      }
    }

  }


  /**
   * Gets a player's list of ships remaining
   *
   * @return the player's remaining ships
   */
  public List<Ship> getBoard() {
    return this.board;
  }




  /**
   * Updates the opponent board with new knowledge
   *
   * @param successfulShots the shots that hit
   */
  public void updateOpponentBoard(List<Coord> successfulShots) {
    for (Coord c : successfulShots) {
      opponent[c.getY()][c.getX()] = HIT;
    }
  }

  /**
   * Updates this user's board
   *
   * @param opponentShots all the opponent's shots
   */
  public void updateUserBoard(List<Coord> opponentShots) {
    for (Coord c : opponentShots) {
      if (user[c.getY()][c.getX()] == SHIP
          || user[c.getY()][c.getX()] == HIT) {
        user[c.getY()][c.getX()] = HIT;
      } else {
        user[c.getY()][c.getX()] = MISS;
      }
    }
  }


  /**
   * Removes a ship from a players board
   *
   * @param ship the ship to be removed
   */
  public void removeShip(Ship ship) {
    board.remove(ship);
  }


  /**
   * Overriding the toString method for a Board object
   *
   * @return a String representation of user and opponent
   *     board statuses
   */
  public String toString() {
    StringBuilder line = new StringBuilder();
    StringBuilder lines = new StringBuilder();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        CellType cellTypeOpp = opponent[i][j];
        if (cellTypeOpp == CellType.HIT) {
          line.append("H ");
        } else if (cellTypeOpp == MISS) {
          line.append("M ");
        } else {
          line.append("? ");
        }
      }
      lines.append(line).append('\n');
      line = new StringBuilder();
    }
    lines.append("\n\n");

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        CellType cellType = user[y][x];
        if (cellType == CellType.SHIP) {
          line.append("S ");
        } else if (cellType == HIT) {
          line.append("H ");
        } else if (cellType == MISS) {
          line.append("M ");
        } else {
          line.append(". ");
        }
      }
      lines.append(line).append('\n');
      line = new StringBuilder();
    }
    lines.append("\n\n");

    return lines.toString();
  }


  /**
   * Getter for board height
   *
   * @return board height
   */
  public int getBoardHeight() {
    return height;
  }

  /**
   * Getter for board width
   *
   * @return width
   */
  public int getBoardWidth() {
    return width;
  }

  /**
   * Sets the shots that missed
   *
   * @param shots missed shots
   */
  public void setMisses(List<Coord> shots) {
    for (Coord c : shots) {
      opponent[c.getY()][c.getX()] = MISS;
    }
  }

  /**
   * Set the board's height and width
   *
   * @param height the vertical squares
   * @param width the horizontal squares
   */
  public void setHeightWidth(int height, int width) {
    this.height = height;
    this.width = width;
  }



}
