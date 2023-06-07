package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ShipType;
import java.util.List;
import java.util.Map;

public record FleetJson(
  @JsonProperty("fleet")
  List<ShipJson> shipJsonList) {




}
