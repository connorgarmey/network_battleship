package cs3500.pa04.model;

/**
 * Enumeration representing the four ship sizes
 */
public enum ShipType {

  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);

  private final int shipSize;

  ShipType(int shipSize) {
    this.shipSize = shipSize;
  }

  public int getShipSize() {
    return this.shipSize;
  }


}
