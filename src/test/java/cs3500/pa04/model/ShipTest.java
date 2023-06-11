package cs3500.pa04.model;


import static cs3500.pa04.model.Direction.HORIZONTAL;
import static cs3500.pa04.model.Direction.VERTICAL;
import static cs3500.pa04.model.ShipType.CARRIER;
import static cs3500.pa04.model.ShipType.SUBMARINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.control.DependencyInjector;
import cs3500.pa04.control.json.CoordJson;
import cs3500.pa04.control.json.ShipJson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing Ship class
 */
public class ShipTest {
  public BoardInteractions board;
  DependencyInjector di;
  List<Ship> ships;
  public Player p1;
  List<Ship> ships2;
  HashMap<ShipType, Integer> map;
  Random random;
  Ship sub;
  ShipJson shipJson;
  CoordJson coordJson;

  /**
   * Initialize data
   */
  @BeforeEach
  public void initData() {

    board = new BoardImpl();
    random = new Random(0);
    p1 = new Ai(board, random);
    map = new HashMap<>();
    HashMap<ShipType, Integer> map2 = new HashMap<>();
    map.put(SUBMARINE, 4);
    map2.put(CARRIER, 1);
    ships = p1.setup(6, 6, map);
    ships2 = p1.setup(6, 6, map2);
    sub = ships.get(0);
    coordJson = new CoordJson(1, 4);
    shipJson = new ShipJson(coordJson, 3, HORIZONTAL);


  }
  
  @Test
  void testMakeShipJson() {
    assertEquals(sub.makeShipJson(), shipJson);
  }

  @Test
  void isSunk() {
    this.initData();
    assertFalse(sub.isSunk());
    sub.isHit(new Coord(1, 4));
    sub.isHit(new Coord(2, 4));
    sub.isHit(new Coord(3, 4));
    assertTrue(sub.isSunk());
  }

  @Test
  void isHit() {
    this.initData();
    assertTrue(sub.isHit(new Coord(1, 4)));
    assertFalse(sub.isHit(new Coord(1, 1)));
  }

  @Test
  void isValid() {
    assertFalse(sub.isValid(ships));
    ships.remove(sub);
    assertTrue(sub.isValid(ships));
  }

  @Test
  void getLocations() {
    assertEquals(sub.getLocations(), new ArrayList<>(Arrays.asList(
        new Coord(1, 4), new Coord(2, 4), new Coord(3, 4))));
  }

  @Test
  void testEquals() {
    this.initData();
    assertTrue(sub.equals(sub));
    assertFalse(sub.equals(ships.get(1)));
    assertFalse(sub.equals(null));
    assertFalse(sub.equals(board));
    sub.isHit(new Coord(1, 4));
    assertTrue(sub.equals(sub));
    assertFalse(sub.equals(ships2.get(0)));

  }

  @Test
  void testDirection() {
    assertEquals("HORIZONTAL", HORIZONTAL.toString());
    assertEquals("VERTICAL", VERTICAL.toString());
  }

}