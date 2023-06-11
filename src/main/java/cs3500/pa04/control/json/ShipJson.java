package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Direction;

/**
 * Record for a ship object
 *
 * @param coordJson the coordinate JSON
 * @param length the ship's length
 * @param direction the direction of the ship
 */
public record ShipJson(
    @JsonProperty("coord")
    CoordJson coordJson,
    @JsonProperty("length")
    int length,
    @JsonProperty("direction")
    Direction direction) {


}
