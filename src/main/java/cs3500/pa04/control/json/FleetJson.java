package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * JSON for a fleet of ships
 *
 * @param shipJsonList the list of ship JSON
 */
public record FleetJson(
    @JsonProperty("fleet")
    List<ShipJson> shipJsonList) {




}
