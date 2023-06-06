package cs3500.pa04.view;

import cs3500.pa04.model.BoardImpl;

/**
 * Class for user view
 */
public class View {
  private final Appendable appendable;
  private final BoardImpl board;

  public View(Appendable a, BoardImpl board) {
    this.appendable = a;
    this.board = board;
  }

  /**
   * Prints the given lines to the terminal
   *
   * @param lines a String of text
   */
  public void printLine(String lines) {
    try {
      appendable.append(lines).append(System.lineSeparator());
    } catch (Exception e) {
      // Handle exception if append fails
      e.printStackTrace();
    }
  }

  /**
   * Displays the current state of the player and their
   * opponent's boards
   */
  public void displayBoard() {
    System.out.println(board.toString());
  }

}
