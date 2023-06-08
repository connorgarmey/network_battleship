package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractPlayerTest {
  User user;
  Ai ai;
  ShipTest st;
  BoardImplTest bt;

  @BeforeEach
  void initData() {
    this.st = new ShipTest();
    this.bt = new BoardImplTest();
    st.initData();
    bt.initData();
    user = new User(st.board, st.di);
    ai = new Ai(st.board, st.random);
  }

  @Test
  void name() {
    this.initData();
    assertEquals("User", user.name());
  }

  @Test
  void reportDamage() {
    this.initData();
    assertEquals(bt.successfulShots, user.reportDamage(bt.shots));
    this.initData();
    assertEquals(bt.shots2, user.reportDamage(bt.shots2));
  }

  @Test
  void successfulHits() {
    this.initData();

  }

}