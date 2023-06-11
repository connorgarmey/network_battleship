package cs3500.pa04.view;

import cs3500.pa04.model.BoardObserver;


/**
 * Class for user view
 */
public class UserView extends AbstractView {
  private final BoardObserver board;

  /**
   * Constructor for a user view
   *
   * @param a the appendable object
   * @param board a read only board
   */
  public UserView(Appendable a, BoardObserver board) {
    super(a);
    this.board = board;
  }

  /**
   * Displays the current state of the player and their
   * opponent's boards
   */
  public void displayBoard() {
    System.out.println(board.toString());
  }

}
