package cs3500.pa04.control;

import static cs3500.pa04.model.GameResult.DRAW;
import static cs3500.pa04.model.GameResult.LOSE;
import static cs3500.pa04.model.GameResult.WIN;
import static cs3500.pa04.model.ShipType.BATTLESHIP;
import static cs3500.pa04.model.ShipType.CARRIER;
import static cs3500.pa04.model.ShipType.DESTROYER;
import static cs3500.pa04.model.ShipType.SUBMARINE;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.view.UserView;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * Class to handle player input for BattleSalvo game
 */
public class OfflineController extends AbstractController {
  private final Player p2;
  private final Scanner input;
  private final DependencyInjector di;
  private boolean isInputValid;
  private final UserView view;
  int height;
  int width;
  HashMap<ShipType, Integer> map;
  GameResult gameResult;

  /**
   * Controller constructor
   *
   * @param p1 Player one
   * @param p2 Player two
   * @param v The view object
   * @param s The Scanner object
   * @param di The Dependency Injector object
   */
  public OfflineController(Player p1, UserView v,
                           Player p2, Scanner s, DependencyInjector di) {
    super(p1);
    this.view = v;
    this.p2 = p2;
    this.input = s;
    this.di = di;
    this.map = new HashMap<>();
  }

  /**
   * Runs the Battleship game
   */
  public void runApp() {
    preface();

    while (!isInputValid) {
      getDimensions();
    }
    isInputValid = false;

    shipSetupPrompt(Math.min(height, width));

    while (!isInputValid) {
      getNumShips();
    }
    isInputValid = false;


    ai.setup(height, width, map);
    p2.setup(height, width, map);

    boolean gameNotOver = true;

    // Gameplay loop
    while (gameNotOver) {

      view.displayBoard();
      List<Coord> shotsFiredFromP1 = ai.takeShots();
      di.clear();
      List<Coord> shotsFiredFromP2 = p2.takeShots();

      List<Coord> damageOnP1 = ai.reportDamage(shotsFiredFromP2);
      List<Coord> damageOnP2 = p2.reportDamage(shotsFiredFromP1);

      // Player 1's successful hits
      ai.successfulHits(damageOnP2);
      // Player 2's successful hits
      p2.successfulHits(damageOnP1);

      if (gameOver(shotsFiredFromP1, shotsFiredFromP2)) {
        gameNotOver = false;
      }
    }
  }


  /**
   * Gets the dimensions from the user
   */
  private void getDimensions() {
    if (input.hasNext()) {
      try {
        height = Integer.parseInt(input.next());
        width = Integer.parseInt(input.next());
        this.checkDimensions(height, width);
        isInputValid = true;
      } catch (RuntimeException e) {
        invalidDimensions();
      }
    }
  }


  /**
   * Checks the given input dimensions for proper range : [6, 15] inclusive
   *
   * @param height the intended height of the board
   * @param width the intended width of the board
   *
   */
  private void checkDimensions(int height, int width) {
    if (!(height >= 6 && height <= 15
        && width >= 6 && width <= 15)) {
      throw new IllegalArgumentException();
    }
  }


  /**
   * Gets the number of each ship from the user
   */
  private void getNumShips() {
    if (input.hasNext()) {
      try {
        int numCarrier = Integer.parseInt(input.next());
        int numBattle = Integer.parseInt(input.next());
        int numDestroy = Integer.parseInt(input.next());
        int numSub = Integer.parseInt(input.next());
        makeMap(numCarrier, numBattle, numDestroy, numSub);
        isInputValid = true;
      } catch (RuntimeException e) {
        invalidShipSetup();
        shipSetupPrompt(Math.min(height, width));
      }
    }
  }

  /**
   * Makes a map of the number of each type of ship
   *
   * @param nc number of carriers
   * @param nb number of battleships
   * @param nd number of destroyers
   * @param ns number of submarines
   */
  public void makeMap(int nc, int nb, int nd, int ns) {
    if (nc + nb + nd + ns <= Math.min(height, width) + 1) {
      map.put(CARRIER, nc);
      map.put(BATTLESHIP, nb);
      map.put(DESTROYER, nd);
      map.put(SUBMARINE, ns);
    } else {
      throw new IllegalArgumentException();
    }
  }


  /**
   * Game's opening intro
   */
  private void preface() {
    view.printLine("""
        Hello! Welcome to the OOD BattleSalvo Game!\s
        Please enter a valid height and width below:
        ------------------------------------------------------
        """);
  }

  /**
   * Invalid dimensions prompt
   */
  private void invalidDimensions() {
    view.printLine("""
        Uh Oh! You've entered invalid dimensions.\s
        Please remember that the height and width
        of the game must be in the range (6, 15), inclusive. Try again!
        ------------------------------------------------------
        """);
  }

  /**
   * Prompt user to set up ships
   *
   * @param maxFleetSize the maximum fleet size
   */
  private void shipSetupPrompt(int maxFleetSize) {
    view.printLine(
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet may not exceed size " + maxFleetSize + "\n"
            + "--------------------------------------------------------"
            + "------------------------");
  }

  /**
   * The invalid ship setup prompt
   */
  private void invalidShipSetup() {
    view.printLine("Uh Oh! You've entered invalid fleet sizes.");
  }


  /**
   * The game is over if a player has no shots left
   *
   * @param list1 p1's remaining shots
   * @param list2 p2's remaining shots
   *
   * @return whether the game is over
   */
  private boolean gameOver(List<Coord> list1, List<Coord> list2) {
    if (list1.size() == 0 && list2.size() == 0) {
      gameResult = DRAW;
      view.printLine("Game ended in a draw");
      return true;
    } else if (list2.size() == 0) {
      gameResult = WIN;
      view.printLine("You win slay");
      return true;
    } else if (list1.size() == 0) {
      gameResult = LOSE;
      view.printLine("You lose, not slay babe :(");
      return true;
    } else {
      return false;
    }
  }

}

