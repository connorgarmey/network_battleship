package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AiTest {
  StringBuilder sb;
  BoardImplTest bt;
  Ai ai;

  @BeforeEach
  void setUp() {
    bt = new BoardImplTest();
    bt.initData();
    sb = new StringBuilder();
    ai = new Ai(bt.b1);
  }

  @Test
  void takeShots() {
    assertEquals(new ArrayList<>(), ai.takeShots());
    // Method that generates the shots already tested
  }

  /*@Test
  void aiShots() {
    ai.aiShots(0, 0);
    assertEquals(new ArrayList<>(), ai.takeShots());
    ai.aiShots(1, 1);
  }*/


}