package cs3500.pa04.model;

/**
 * Enumeration for a 2D direction
 */
public enum Direction {
  VERTICAL, HORIZONTAL;

  /**
   * Converts Direction to string
   *
   * @return String representation
   */
  @Override
  public String toString() {
    if (this == VERTICAL) {
      return "VERTICAL";
    } else {
      return "HORIZONTAL";
    }
  }
}
