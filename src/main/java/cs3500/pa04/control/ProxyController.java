package cs3500.pa04.control;

import cs3500.pa04.control.json.CoordJson;
import cs3500.pa04.control.json.VolleyJson;
import cs3500.pa04.model.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Online Battleship
 */
public class ProxyController {

  public void runOnline() {

  }

  private VolleyJson makeVolleyJson(List<Coord> volley) {
    List<CoordJson> coordJsons = new ArrayList<>();
    for (Coord c : volley) {
      coordJsons.add(c.makeCoordJson());
    }
    return new VolleyJson(coordJsons);
  }

}
