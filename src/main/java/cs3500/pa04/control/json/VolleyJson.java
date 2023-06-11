package cs3500.pa04.control.json;

import static cs3500.pa04.model.ShipType.BATTLESHIP;
import static cs3500.pa04.model.ShipType.CARRIER;
import static cs3500.pa04.model.ShipType.DESTROYER;
import static cs3500.pa04.model.ShipType.SUBMARINE;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Record for a volley of shots
 *
 * @param coordJsons the coordinates as JSON
 */
public record VolleyJson(

    @JsonProperty("coordinates")
    List<CoordJson> coordJsons) {

  /**
   * Converts a list of CoordJson to a list of coordinates
   *
   * @return a list of Coords
   */
  public List<Coord> convertToCoords() {
    List<Coord> coords = new ArrayList<>();

    for (CoordJson coordJson : this.coordJsons) {
      coords.add(new Coord(coordJson.x(), coordJson.y()));
    }
    return coords;
  }



}
