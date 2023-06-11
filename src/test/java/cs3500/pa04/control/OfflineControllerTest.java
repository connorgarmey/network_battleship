package cs3500.pa04.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.Ai;
import cs3500.pa04.model.BoardImpl;
import cs3500.pa04.model.BoardInteractions;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.User;
import cs3500.pa04.view.UserView;
import java.io.StringReader;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OfflineControllerTest {
  String args;
  BoardInteractions board1;
  BoardInteractions board2;
  UserView view;
  Scanner scan;

  @BeforeEach
  void initData() {
    StringReader sr = new StringReader("6 6 1 1 1 1 ");
    BoardInteractions board1 = new BoardImpl();
    BoardInteractions board2 = new BoardImpl();
    StringBuilder sb = new StringBuilder();
    UserView view = new UserView(sb, board1);
    Scanner scan = new Scanner(sr);
    DependencyInjector di = new DependencyInjector(scan, view);
    Player p1 = new User(board1, di);
    Player p2 = new Ai(board2);

    Controller controller = new OfflineController(p1, view, p2, scan, di);

  }

  @Test
  void runApp() {
    StringReader sr = new StringReader("6 6 1 1 1 1 ");
    BoardInteractions board1 = new BoardImpl();
    BoardInteractions board2 = new BoardImpl();
    StringBuilder sb = new StringBuilder();
    UserView view = new UserView(sb, board1);
    Scanner scan = new Scanner(sr);
    DependencyInjector di = new DependencyInjector(scan, view);
    Player p1 = new User(board1, di);
    Player p2 = new Ai(board2);

    Controller controller = new OfflineController(p1, view, p2, scan, di);

    controller.runApp();
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n"
        + "------------------------------------------------------\n"
        + "\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size 6\n"
        + "--------------------------------------------------------------------------------\n"
        + "Please enter 4 shots\n"
        + "_____________________\n"
        + "You lose, not slay babe :(\n", sb.toString());
  }

  @Test
  void runDimensions() {
    StringReader sr = new StringReader("6 111 6 6 11 1 1 1 1 1 1 1");
    BoardInteractions board1 = new BoardImpl();
    BoardInteractions board2 = new BoardImpl();
    StringBuilder sb = new StringBuilder();
    UserView view = new UserView(sb, board1);
    Scanner scan = new Scanner(sr);
    DependencyInjector di = new DependencyInjector(scan, view);
    Player p1 = new User(board1, di);
    Player p2 = new Ai(board2);

    OfflineController controller = new OfflineController(p1, view, p2, scan, di);

    controller.runApp();
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n"
        + "------------------------------------------------------\n"
        + "\n" + "Uh Oh! You've entered invalid dimensions. \n"
        + "Please remember that the height and width\n"
        + "of the game must be in the range (6, 15), inclusive. Try again!\n"
        + "------------------------------------------------------\n"
        + "\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size 6\n"
        + "--------------------------------------------------------------------------------\n"
        + "Uh Oh! You've entered invalid fleet sizes.\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size 6\n"
        + "--------------------------------------------------------------------------------\n"
        + "Please enter 4 shots\n"
        + "_____________________\n"
        + "You lose, not slay babe :(\n", sb.toString());
  }

  @Test
  void fullGame() {
    StringReader sr = new StringReader("k h -1 7 20 5 "
        + "8 8 "
        + "8 0 -1 6 "
        + "2 2 2 2 "
        + "1 1 1 1 "
        + "0 0 0 1 0 2 0 3"
        + "0 4 0 5 1 0 1 1"
        + "1 2 1 3 1 4 1 5"
        + "2 0 2 1 2 2 2 3"
        + "2 4 2 5 3 0 3 1"
        + "3 2 3 3 3 4 3 5"
        + "4 0 4 1 4 2 4 3"
        + "4 4 4 5 5 0 5 1"
        + "5 2 5 3 5 4 5 5");
    BoardInteractions board1 = new BoardImpl();
    BoardInteractions board2 = new BoardImpl();
    StringBuilder sb = new StringBuilder();
    UserView view = new UserView(sb, board1);
    Scanner scan = new Scanner(sr);
    DependencyInjector di = new DependencyInjector(scan, view);
    Player p1 = new User(board1, di);
    Player p2 = new Ai(board2);

    OfflineController controller = new OfflineController(p1, view, p2, scan, di);

    controller.runApp();
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
        +
        "Please enter a valid height and width below:\n"
        +
        "------------------------------------------------------\n"
        +
        "\n"
        +
        "Uh Oh! You've entered invalid dimensions. \n"
        +
        "Please remember that the height and width\n"
        +
        "of the game must be in the range (6, 15), inclusive. Try again!\n"
        +
        "------------------------------------------------------\n"
        +
        "\n"
        +
        "Uh Oh! You've entered invalid dimensions. \n"
        +
        "Please remember that the height and width\n"
        +
        "of the game must be in the range (6, 15), inclusive. Try again!\n"
        +
        "------------------------------------------------------\n"
        +
        "\n"
        +
        "Uh Oh! You've entered invalid dimensions. \n"
        +
        "Please remember that the height and width\n"
        +
        "of the game must be in the range (6, 15), inclusive. Try again!\n"
        +
        "------------------------------------------------------\n"
        +
        "\n"
        +
        "Uh Oh! You've entered invalid dimensions. \n"
        +
        "Please remember that the height and width\n"
        +
        "of the game must be in the range (6, 15), inclusive. Try again!\n"
        +
        "------------------------------------------------------\n"
        +
        "\n"
        +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        +
        "Remember, your fleet may not exceed size 8\n"
        +
        "--------------------------------------------------------------------------------\n"
        +
        "Uh Oh! You've entered invalid fleet sizes.\n"
        +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        +
        "Remember, your fleet may not exceed size 8\n"
        +
        "--------------------------------------------------------------------------------\n"
        +
        "Please enter 8 shots\n"
        +
        "_____________________\n"
        +
        "Please enter valid and unique coordinates.\n"
        +
        "Please enter valid and unique coordinates.\n"
        +
        "Please enter valid and unique coordinates.\n"
        +
        "Please enter valid and unique coordinates.\n"
        +
        "Please enter 8 shots\n"
        +
        "_____________________\n"
        +
        "You lose, not slay babe :(\n", sb.toString());
  }
}