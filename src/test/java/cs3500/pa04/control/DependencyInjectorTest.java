package cs3500.pa04.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.BoardImplTest;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipTest;
import cs3500.pa04.view.UserView;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DependencyInjectorTest {
  ShipTest st;
  BoardImplTest bt;
  StringReader shots;
  StringReader shots2;


  @BeforeEach
  void initData() {
    bt = new BoardImplTest();
    bt.initData();
    st = new ShipTest();
    st.initData();
    shots = new StringReader("0 0 0 1 0 2 0 3");
    shots2 = new StringReader("0 0 0 1 0 d 0 3");

  }

  @Test
  void acceptShots() {
    StringBuilder one = new StringBuilder();
    DependencyInjector di =
        new DependencyInjector(new Scanner(shots), new UserView(one, bt.b1));
    di.acceptShots(6, 6, 0);
    assertEquals(new ArrayList<Ship>(), di.retrieveShots());
    this.initData();
    StringBuilder two = new StringBuilder();
    DependencyInjector di2 =
        new DependencyInjector(new Scanner(shots2), new UserView(two, bt.b1));
    di2.acceptShots(6, 6, 0);
    assertEquals("Please enter 0 shots\n"
        + "_____________________\n", two.toString());
    this.initData();
    StringBuilder three = new StringBuilder();
    DependencyInjector di3 =
        new DependencyInjector(new Scanner(shots2), new UserView(three, bt.b1));
    di3.acceptShots(6, 6, 4);
    assertEquals("Please enter 4 shots\n"
            + "_____________________\nPlease enter valid and unique coordinates.\n",
        three.toString());

  }


}