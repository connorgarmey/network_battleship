package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Coordinate JSON record
 *
 * @param x the x
 * @param y the y
 */
public record CoordJson(
    @JsonProperty("x")
    int x,
    @JsonProperty("y")
    int y) {

}
