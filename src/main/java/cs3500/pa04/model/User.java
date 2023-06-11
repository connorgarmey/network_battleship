package cs3500.pa04.model;

import cs3500.pa04.control.DependencyInjector;
import java.util.List;


/**
 * Represents the user as a player
 */
public class User extends AbstractPlayer {
  private final DependencyInjector di;

  /**
   * Constructor for the User class
   *
   * @param board the Board object
   * @param di the Dependency Injector object
   */
  public User(BoardInteractions board, DependencyInjector di) {
    super(board);
    this.di = di;
  }


  /**
   * While the game is not over, fires the shots
   * that the user inputs
   *
   * @return the list of shot coordinates to fire
   */
  @Override
  public List<Coord> takeShots() {
    System.out.println(board.getBoard().size());
    di.acceptShots(board.getBoardHeight(), board.getBoardWidth(),
        board.getBoard().size());
    List<Coord> shots = di.retrieveShots();
    board.setMisses(shots);
    return shots;
  }

}
