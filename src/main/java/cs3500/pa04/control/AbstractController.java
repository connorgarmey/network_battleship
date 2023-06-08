package cs3500.pa04.control;

import static cs3500.pa04.model.GameResult.DRAW;
import static cs3500.pa04.model.GameResult.LOSE;
import static cs3500.pa04.model.GameResult.WIN;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.Player;
import java.util.List;

public abstract class AbstractController implements Controller {
  protected final Player p1;
  //protected final View view;
  protected boolean isInputValid;
  int height;
  int width;
  GameResult gameResult;

  /**
   * Controller constructor
   *
   * @param p1 Player one
   * @param v The view object
   */
  public AbstractController(Player p1) {
    //this.view = v;
    this.isInputValid = false;
    this.p1 = p1;
    this.height = 0;
    this.width = 0;
  }


  /**
   * The game is over if a player has no shots left
   *
   * @param list1 p1's remaining shots
   * @param list2 p2's remaining shots
   *
   * @return whether the game is over
   */
  public boolean gameOver(List<Coord> list1, List<Coord> list2) {
    if (list1.size() == 0 && list2.size() == 0) {
      gameResult = DRAW;
      return true;
    }
    if (list1.size() == 0) {
      gameResult = LOSE;
      return true;
    }
    if (list2.size() == 0) {
      gameResult = WIN;
      return true;
    } else {
      return false;
    }
  }


}
