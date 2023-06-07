package cs3500.pa04;

import cs3500.pa04.control.OfflineController;
import cs3500.pa04.control.DependencyInjector;
import cs3500.pa04.control.ProxyController;
import cs3500.pa04.model.Ai;
import cs3500.pa04.model.BoardImpl;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.User;
import cs3500.pa04.view.View;
import java.io.InputStreamReader;
import java.net.Socket;
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
    BoardInteractions board = new BoardImpl();
    Player p1 = new Ai(board);
    View view = new View(System.out, board);

    if (args.length == 2) {
      runClient(args, p2);
    }

    BoardImpl board1 = new BoardImpl();
    View view = new View(System.out, board1);
    Scanner scan = new Scanner(new InputStreamReader(System.in));
    DependencyInjector di = new DependencyInjector(scan, view);
    Player p1 = new User(board1, di);

    OfflineController controller = new OfflineController(p1, p2, view, scan, di);

    controller.runApp();
  }


  private static void runClient(String[] args, Player ai ) {
    String host;
    int port;

    try {
      host = args[0];
      port = Integer.parseInt(args[1]);
      Socket server = new Socket(host, port);

      ProxyController proxyController = new ProxyController(server, ai);
    } catch (Exception e) {
      System.out.println("Invalid args");
    }





  }


}