package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing Board
 */
public class BoardImplTest {
  public BoardInteractions b1;
  ShipTest st;
  ArrayList<Coord> hits;
  ArrayList<Coord> misses;
  ArrayList<Coord> shots;
  ArrayList<Coord> successfulShots;
  ArrayList<Coord> shots2;

  /**
   * Initialize data
   */
  @BeforeEach
  public void initData() {
    b1 = new BoardImpl();
    st = new ShipTest();
    st.initData();
    hits = new ArrayList<>(Arrays.asList(new Coord(5, 5)));
    misses = new ArrayList<>(Arrays.asList(new Coord(1, 1)));
    shots = new ArrayList<>(Arrays.asList(new Coord(0, 3),
        new Coord(0, 4)));
    successfulShots = new ArrayList<>(Arrays.asList(new Coord(0, 4)));
    shots2 = new ArrayList<>(Arrays.asList(new Coord(2, 4),
        new Coord(0, 4),  new Coord(1, 4)));

  }

  @Test
  void setBoards() {
    b1.setBoards(st.ships);
  }

  @Test
  void getBoard() {
    assertEquals(new ArrayList<Ship>(), b1.getBoard());
  }


  @Test
  void removeShip() {
    this.initData();
    BoardInteractions b2 = b1;
    b2.removeShip(st.sub);
    this.initData();
    assertFalse(b1.equals(b2));
  }

  @Test
  void testToString() {
    this.initData();
    b1.setHeightWidth(6, 6);
    b1.setBoards(st.ships);
    assertEquals("? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "\n"
            + "\n"
            + ". . . . . . \n"
            + ". . . . . S \n"
            + ". . . . . S \n"
            + "S S S . . S \n"
            + ". S S S . . \n"
            + ". . S S S . "
        + "\n\n\n", b1.toString());
    b1.updateOpponentBoard(hits);
    b1.setMisses(misses);
    assertEquals("? ? ? ? ? ? \n"
            + "? M ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? H \n"
            + "\n"
            + "\n"
            + ". . . . . . \n"
            + ". . . . . S \n"
            + ". . . . . S \n"
            + "S S S . . S \n"
            + ". S S S . . \n"
            + ". . S S S . "
            + "\n\n\n", b1.toString());
    b1.updateUserBoard(shots);
    assertEquals("? ? ? ? ? ? \n"
            + "? M ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? ? \n"
            + "? ? ? ? ? H \n"
            + "\n"
            + "\n"
            + ". . . . . . \n"
            + ". . . . . S \n"
            + ". . . . . S \n"
            + "H S S . . S \n"
            + "M S S S . . \n"
            + ". . S S S . "
            + "\n\n\n", b1.toString());
  }

  @Test
  void getBoardHeight() {
    this.initData();
    assertEquals(0, b1.getBoardHeight());
  }

  @Test
  void getBoardWidth() {
    this.initData();
    assertEquals(0, b1.getBoardWidth());
  }

}