package cs3500.pa04;

import cs3500.pa04.control.Controller;
import cs3500.pa04.control.DependencyInjector;
import cs3500.pa04.model.Ai;
import cs3500.pa04.model.BoardImpl;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.User;
import cs3500.pa04.view.View;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    BoardImpl board1 = new BoardImpl();
    BoardImpl board2 = new BoardImpl();
    View view = new View(System.out, board1);
    Scanner scan = new Scanner(new InputStreamReader(System.in));
    DependencyInjector di = new DependencyInjector(scan, view);
    Player p1 = new User(board1, di);
    Player p2 = new Ai(board2);

    Controller controller = new Controller(p1, p2, view, scan, di);

    if (args.length == 2) {
      runClient(args[0], Integer.parseInt(args[1]));
    }

    controller.runApp();

  }


  private static void runClient(String host, int port) {

  }


}