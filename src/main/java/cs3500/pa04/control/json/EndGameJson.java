package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.GameResult;

/**
 * JSON for ending the game
 *
 * @param gameResult the result of the game
 * @param reason the reason for the game's end
 */
public record EndGameJson(
    @JsonProperty("result")
    GameResult gameResult,
    @JsonProperty("reason")
    String reason) {

}


