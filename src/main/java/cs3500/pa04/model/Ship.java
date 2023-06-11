package cs3500.pa04.model;

import static cs3500.pa04.model.Direction.HORIZONTAL;
import static cs3500.pa04.model.Direction.VERTICAL;

import cs3500.pa04.control.json.ShipJson;
import java.util.ArrayList;
import java.util.List;


/**
 * Class to represent a Ship
 */
public class Ship {
  private final ShipType shipType;
  private final List<Coord> location;
  private final List<Coord> hit;
  private final Direction direction;

  /**
   * Constructor for a ship
   *
   * @param type ShipType
   * @param height Board height
   * @param width Board width
   * @param dir Ship Direction
   */
  Ship(ShipType type, int height, int width, boolean dir) {
    this.shipType = type;
    this.location = new ArrayList<>();
    this.hit = new ArrayList<>();
    if (dir) {
      this.direction = HORIZONTAL;
    } else {
      this.direction = VERTICAL;
    }
    this.setLocation(width, height, direction);
  }


  /**
   * Determines if every part of a ship is hit
   *
   * @return if the ship is sunk
   */
  public boolean isSunk() {
    return location.size() == hit.size();
  }


  /**
   * Determines if a ship has been hit
   *
   * @param coord the coordinate of the shot fired
   *
   * @return whether the ship is on the coordinate given
   */
  public boolean isHit(Coord coord) {
    if (location.contains(coord)) {
      hit.add(coord);
      return true;
    } else {
      return false;
    }
  }


  /**
   * Sets the location of a ship
   *
   * @param x the x location to start at
   * @param y the y location to start at
   * @param dir the direction of the ship
   */
  private void setLocation(int x, int y, Direction dir) {
    if (dir == HORIZONTAL) {
      for (int i = x; i < x + shipType.getShipSize(); i++) {
        location.add(new Coord(i, y));
      }
    } else {
      for (int j = y; j < y + shipType.getShipSize(); j++) {
        location.add(new Coord(x, j));
      }
    }
  }


  /**
   * Determines if a ship is valid given where ships already
   * are
   *
   * @param ships ships on the board
   *
   * @return if the ship can be placed here
   */
  public boolean isValid(List<Ship> ships) {
    for (Coord c1 : this.location) {
      for (Coord c2 : getAllLocations(ships)) {
        if (c1.equals(c2)) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * Gets the coordinates of all ships
   *
   * @param ships all the ships on the board
   *
   * @return a list of coordinates
   */
  private List<Coord> getAllLocations(List<Ship> ships) {
    List<Coord> list = new ArrayList<>();
    for (Ship s : ships) {
      list.addAll(s.location);
    }
    return list;
  }


  /**
   * Getter for a ship's location
   *
   * @return list of coordinates
   */
  public List<Coord> getLocations() {
    return this.location;
  }


  /**
   * Overrides equals for the Ship class
   *
   * @param o the object to be compared
   * @return whether this and the parameter are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ship ship = (Ship) o;
    return this.location.equals(ship.location);
  }


  public ShipJson makeShipJson() {
    return new ShipJson(location.get(0).makeCoordJson(),
        shipType.getShipSize(), direction);
  }
}



