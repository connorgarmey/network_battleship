package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.BoardImpl;
import cs3500.pa04.model.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CoordTest {
  Coord c1 = new Coord(1, 2);

  @BeforeEach
  void setUp() {
  }

  @Test
  void getX() {
    assertEquals(1, c1.getX());
  }

  @Test
  void getY() {
    assertEquals(2, c1.getY());
  }

  @Test
  void testEquals() {
    assertTrue(c1.equals(c1));
    assertFalse(c1.equals(null));
    assertFalse(c1.equals(new BoardImpl()));
  }

  @Test
  void printCoord() {
    assertEquals("(1, 2)", c1.printCoord());
  }
}