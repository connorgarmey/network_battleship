package cs3500.pa04.control;

import static cs3500.pa04.model.GameResult.DRAW;
import static cs3500.pa04.model.GameResult.LOSE;
import static cs3500.pa04.model.GameResult.WIN;
import static cs3500.pa04.model.ShipType.BATTLESHIP;
import static cs3500.pa04.model.ShipType.CARRIER;
import static cs3500.pa04.model.ShipType.DESTROYER;
import static cs3500.pa04.model.ShipType.SUBMARINE;

import cs3500.pa04.model.Coord;
import java.util.List;

public interface Controller {
  void runApp();


}
