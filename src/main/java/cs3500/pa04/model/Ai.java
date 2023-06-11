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
  private Random random;




  /**
   * AI constructor
   *
   * @param board the Board object
   */
  public Ai(BoardInteractions board) {
    super(board);
    this.current = new ArrayList<>();
    this.count = 0;
    this.random = new Random();
  }

  /**
   * AI constructor
   *
   * @param board the Board object
   */
  public Ai(BoardInteractions board, Random random) {
    super(board, random);
    this.current = new ArrayList<>();
    this.count = 0;
    this.random = random;
  }



  /**
   * Method for taking shots
   *
   * @return a list of AI generated shots
   */
  @Override
  public List<Coord> takeShots() {
    count = board.getBoard().size();
    noHits(count);
    List<Coord> round = new ArrayList<>(current);
    current = new ArrayList<>();
    return round;
  }

  /*public void newAiShots() {
    if (hits.size() == 0) {
      noHits(count);
    } else {

    }
  }*/ // TODO: update for smarter ai for tournament

  private void noHits(int size) {
    for (int i = 0; i < size; i++) {
      int listSize = allGuesses.size();
      int guess = random.nextInt(listSize);
      Coord theGuess = allGuesses.remove(guess);
      current.add(theGuess);
    }
  }
}
