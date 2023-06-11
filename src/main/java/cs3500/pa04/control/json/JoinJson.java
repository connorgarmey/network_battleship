package cs3500.pa04.control.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.control.GameType;

/**
 * JSON for joining the server game
 *
 * @param github the player's GitHub username
 * @param gameType the type of game
 */
public record JoinJson(
    @JsonProperty("name")
    String github,
    @JsonProperty("game-type")
    GameType gameType) {

  public JoinJson(String github, GameType gameType) {
    this.github = github;
    this.gameType = gameType;
  }

}

