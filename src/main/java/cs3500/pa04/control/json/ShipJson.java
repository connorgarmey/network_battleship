package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Direction;

public record ShipJson(
    @JsonProperty("coord")
    CoordJson coordJson,
    @JsonProperty("length")
    int length,
    @JsonProperty("direction")
    Direction direction) {


}
