package cs3500.pa04.model;

import java.util.List;

public interface Board {


  /**
   * Gets a player's list of ships remaining
   *
   * @return the player's remaining ships
   */
  List<Ship> getBoard();


  /**
   * Overriding the toString method for a Board object
   *
   * @return a String representation of user and opponent
   *     board statuses
   */
  String toString();


  /**
   * Getter for board height
   *
   * @return board height
   */
  int getBoardHeight();


  /**
   * Getter for board width
   *
   * @return width
   */
  public int getBoardWidth();


}
