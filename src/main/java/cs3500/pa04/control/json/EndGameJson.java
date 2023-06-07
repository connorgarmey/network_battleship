package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.GameResult;

public record EndGameJson(
    @JsonProperty("result")
    GameResult gameResult,
    @JsonProperty("reason")
    String reason) {

}

