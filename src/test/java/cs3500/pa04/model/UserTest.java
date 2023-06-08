package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.control.DependencyInjector;
import cs3500.pa04.view.UserView;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
  StringBuilder sb;
  DependencyInjector di;
  BoardImplTest bt;
  User user;

  @BeforeEach
  void setUp() {
    bt = new BoardImplTest();
    bt.initData();
    sb = new StringBuilder();
    di = new DependencyInjector(new Scanner(System.in), new UserView(sb, bt.b1));
    user = new User(bt.b1, di);
  }

  @Test
  void takeShots() {
    assertEquals(new ArrayList<>(), user.takeShots());
    //Already tested in Board as well as update user board method
  }


}