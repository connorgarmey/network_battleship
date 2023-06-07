package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;
import java.util.ArrayList;
import java.util.List;

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
