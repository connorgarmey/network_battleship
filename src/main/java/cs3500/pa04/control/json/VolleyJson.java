package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record VolleyJson(

  @JsonProperty("coordinates")
  List<CoordJson> coordJsons) {
}
