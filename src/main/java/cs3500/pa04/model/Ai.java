package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an AI player
 */
public class Ai extends AbstractPlayer {
  private int count;
  private List<Coord> current;
  private List<Coord> aiGuessed;
  private Random random;



  /**
   * AI constructor
   *
   * @param board the Board object
   */
  public Ai(BoardImpl board) {
    super(board);
    this.current = new ArrayList<>();
    this.aiGuessed = new ArrayList<>();
    this.count = 0;
    this.random = new Random();
  }

  /**
   * AI constructor
   *
   * @param board the Board object
   */
  public Ai(BoardImpl board, Random random) {
    super(board, random);
    this.current = new ArrayList<>();
    this.aiGuessed = new ArrayList<>();
    this.count = 0;
  }


  /**
   * Method for taking shots
   *
   * @return a list of AI generated shots
   */
  @Override
  public List<Coord> takeShots() {
    count = board.getBoard().size();
    aiShots(board.getBoardHeight(), board.getBoardWidth());
    List<Coord> round = new ArrayList<>(current);
    current = new ArrayList<>();
    return round;
  }

  /**
   * Updates the AI player's board
   *
   * @param opponentShotsOnBoard the user's shots
   */
  public void updateUserBoard(List<Coord> opponentShotsOnBoard) {
    board.updateUserBoard(opponentShotsOnBoard);
  }


  //TODO: Fix
  /**
   * Generates random shots that the AI player will take
   *
   * @param height the board height
   * @param width the board width
   */
  public void aiShots(int height, int width) {
    boolean invalid;

    for (int i = 0; i < count; i++) {
      invalid = true;
      while (invalid) {
        int y = random.nextInt(height);
        int x = random.nextInt(width);
        Coord c = new Coord(x, y);

        if (!aiGuessed.contains(c)) {
          current.add(c);
          aiGuessed.add(c);
          invalid = false;
        }
      }
    }
  }


}
