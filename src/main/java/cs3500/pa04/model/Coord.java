package cs3500.pa04.model;

import cs3500.pa04.control.json.CoordJson;

/**
 * Coordinate class
 */
public class Coord {
  private final int width;
  private final int height;

  /**
   * Coordinate constructor
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public Coord(int x, int y) {
    this.width = x;
    this.height = y;
  }

  /**
   * Constructor to convert a JSON coordinate
   *
   * @param coordJson the JSON representation
   */
  public Coord(CoordJson coordJson) {
    this.width = coordJson.x();
    this.height = coordJson.y();
  }

  /**
   * Getter the x coordinate
   *
   * @return int x
   */
  public int getX() {
    return this.width;
  }

  /**
   * Getter the y coordinate
   *
   * @return int y
   */
  public int getY() {
    return this.height;
  }

  /**
   * Overrides equals for the Coord class
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
    Coord coord = (Coord) o;
    return width == coord.width && height == coord.height;
  }

  /**
   * Prints the coordinate
   *
   * @return the String version of the coordinate
   */
  public String printCoord() {
    return "(" + width + ", " + height + ")";
  }


  public CoordJson makeCoordJson() {
    return new CoordJson(width, height);
  }



}
