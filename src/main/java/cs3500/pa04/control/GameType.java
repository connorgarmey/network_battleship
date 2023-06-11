package cs3500.pa04.control;

/**
 * Enumeration for a GameType
 */
public enum GameType {
  SINGLE,
  MULTIPLAYER;

  @Override
  public String toString() {
    if (this == SINGLE) {
      return "SINGLE";
    } else {
      return "MULTI";
    }
  }
}
