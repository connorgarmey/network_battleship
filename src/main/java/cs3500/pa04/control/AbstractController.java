package cs3500.pa04.control;

import static cs3500.pa04.model.GameResult.DRAW;
import static cs3500.pa04.model.GameResult.LOSE;
import static cs3500.pa04.model.GameResult.WIN;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.Player;
import java.util.List;

public abstract class AbstractController implements Controller {
  protected final Player ai;
  protected boolean isInputValid;
  int height;
  int width;
  GameResult gameResult;

  /**
   * Controller constructor
   *
   * @param ai Player one
   */
  public AbstractController(Player ai) {
    this.isInputValid = false;
    this.ai = ai;
    this.height = 0;
    this.width = 0;
  }




}
