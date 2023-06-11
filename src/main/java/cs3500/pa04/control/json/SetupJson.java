package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record for setting up a game board
 *
 * @param width the board width
 * @param height the board height
 * @param fleet the fleet of ships JSON object
 */
public record SetupJson(
    @JsonProperty("width")
    int width,
    @JsonProperty("height")
    int height,
    @JsonProperty("fleet-spec")
    FleetSpecJson fleet) {


}
