package cs3500.pa04;

import cs3500.pa04.control.DependencyInjector;
import cs3500.pa04.control.OfflineController;
import cs3500.pa04.control.ProxyController;
import cs3500.pa04.model.Ai;
import cs3500.pa04.model.BoardImpl;
import cs3500.pa04.model.BoardInteractions;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.User;
import cs3500.pa04.view.ServerView;
import cs3500.pa04.view.UserView;
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
   * @param args - host and port or nothing for offline game mode
   */
  public static void main(String[] args) {

    BoardInteractions aiBoard = new BoardImpl();
    Player p2 = new Ai(aiBoard);

    if (args.length == 2) {
      ServerView serverView = new ServerView(System.out);
      runClient(args, p2, serverView);
    } else {

      Scanner scan = new Scanner(new InputStreamReader(System.in));
      BoardInteractions userBoard = new BoardImpl();
      UserView userView = new UserView(System.out, userBoard);
      DependencyInjector di = new DependencyInjector(scan, userView);
      Player p1 = new User(userBoard, di);

      OfflineController controller = new OfflineController(p1, userView, p2, scan, di);
      controller.runApp();
    }
  }



  /**
   * Parses the command line arguments to retrieve a host and port for the
   * server and creates a new proxy controller for the online game
   *
   * @param args the host and port
   * @param ai the AI player object
   * @param serverView the view of JSON interactions
   */
  private static void runClient(String[] args, Player ai, ServerView serverView) {
    String host;
    int port;

    try {
      host = args[0];
      port = Integer.parseInt(args[1]);
      Socket server = new Socket(host, port);
      new ProxyController(ai, serverView, server);
    } catch (Exception e) {
      System.out.println("Invalid args");
    }





  }


}