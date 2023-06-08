package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.BoardImplTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserViewTest {
  BoardImplTest bt;

  @BeforeEach
  void initData() {
    bt = new BoardImplTest();
    bt.initData();
  }



  @Test
  void printLine() {
    StringBuilder sb = new StringBuilder();
    UserView v = new UserView(sb, bt.b1);
    v.printLine("Hey");
    assertEquals("Hey\n", sb.toString());
  }

  @Test
  void displayBoard() {
    StringBuilder sb = new StringBuilder();
    UserView v = new UserView(sb, bt.b1);
    v.displayBoard();
    assertEquals("", sb.toString());
    // Tested in BoardTest class as well
  }
}