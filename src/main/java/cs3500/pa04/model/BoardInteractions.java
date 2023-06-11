package cs3500.pa04.model;

import java.util.List;

/**
 * Interactive interface for board
 */
public interface BoardInteractions extends BoardObserver {

  /**
   * Sets up the boards
   *
   * @param ships the ships to place
   */
  void setBoards(List<Ship> ships);


  /**
   * Updates the opponent board with new knowledge
   *
   * @param successfulShots the shots that hit
   */
  void updateOpponentBoard(List<Coord> successfulShots);


  /**
   * Updates this user's board
   *
   * @param opponentShots all the opponent's shots
   */
  void updateUserBoard(List<Coord> opponentShots);


  /**
   * Removes a ship from a players board
   *
   * @param ship the ship to be removed
   */
  public void removeShip(Ship ship);


  /**
   * Sets the shots that missed
   *
   * @param shots missed shots
   */
  public void setMisses(List<Coord> shots);


  /**
   * Set the board's height and width
   *
   * @param height the vertical squares
   * @param width the horizontal squares
   */
  public void setHeightWidth(int height, int width);




}
