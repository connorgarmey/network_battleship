package cs3500.pa04.control;

import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.Player;

/**
 * Abstracted controller class
 */
public abstract class AbstractController implements Controller {
  protected final Player ai;
  protected boolean isInputValid;
  protected int height;
  protected int width;


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
