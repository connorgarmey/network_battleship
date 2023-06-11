package cs3500.pa04.control;

import static cs3500.pa04.control.GameType.SINGLE;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.control.json.CoordJson;
import cs3500.pa04.control.json.EndGameJson;
import cs3500.pa04.control.json.FleetJson;
import cs3500.pa04.control.json.JoinJson;
import cs3500.pa04.control.json.JsonUtils;
import cs3500.pa04.control.json.MessageJson;
import cs3500.pa04.control.json.SetupJson;
import cs3500.pa04.control.json.ShipJson;
import cs3500.pa04.control.json.VolleyJson;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.Ship;
import cs3500.pa04.view.ServerView;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Online Battleship
 */
public class ProxyController extends AbstractController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final ObjectMapper mapper = new ObjectMapper();
  private final ServerView view;


  /**
   * Constructor for a proxy controller
   *
   * @param ai the AI player
   * @param v the view
   * @param server the socket
   *
   * @throws IOException invalid inputs
   */
  public ProxyController(Player ai, ServerView v, Socket server) throws IOException {
    super(ai);
    this.view = v;
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.runApp();
  }


  /**
   * Method for running the server game
   */
  public void runApp() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.out.println("Error parsing message");
    }
  }


  /**
   * Delegates out the servers messages based on the name in the message
   *
   * @param message the message received from the server
   */
  private void delegateMessage(MessageJson message) {
    String methodName = message.methodName();
    JsonNode arguments = message.arguments();

    switch (methodName) {
      case "join" -> handleJoin(arguments);
      case "setup" -> handleSetup(arguments);
      case "take-shots" -> handleShots(arguments);
      case "report-damage" -> handleDamage(arguments);
      case "successful-hits" -> handleHits(arguments);
      case "end-game" -> handleEnd(arguments);
      default -> throw new IllegalStateException("Bad JSON method name");
    }
  }

  private void sendResponse(JsonNode node, String methodName) {
    MessageJson response = new MessageJson(methodName, node);
    this.out.println(response);
    view.printLine(response.toString());
  }


  /**
   * Responds to the server with a github username and the type
   * of game which is single player
   *
   * @param args the Server prompt
   */
  private void handleJoin(JsonNode args) {
    JoinJson joinJson = new JoinJson("connorgarmey", SINGLE);
    JsonNode node = JsonUtils.serializeRecord(joinJson);
    sendResponse(node, "join");
  }


  /**
   * Converts the JSON setup arguments to a width, height, and fleet
   * and returns a serialized response
   *
   * @param args the Server response
   */
  private void handleSetup(JsonNode args) {
    SetupJson setupParams = this.mapper.convertValue(args, SetupJson.class);
    List<ShipJson> shipJsons = new ArrayList<>();

    List<Ship> ships =
        ai.setup(setupParams.height(), setupParams.width(), setupParams.fleet().makeMap());

    for (Ship s : ships) {
      shipJsons.add(s.makeShipJson());
    }

    FleetJson response = new FleetJson(shipJsons);
    JsonNode node = JsonUtils.serializeRecord(response);
    sendResponse(node, "setup");
  }


  private void handleShots(JsonNode args) {
    List<Coord> shots = ai.takeShots();
    List<CoordJson> shotsJson = makeCoordJson(shots);

    VolleyJson response = new VolleyJson(shotsJson);
    JsonNode node = JsonUtils.serializeRecord(response);
    sendResponse(node, "take-shots");
  }


  private void handleDamage(JsonNode args) {
    VolleyJson volleyJson = this.mapper.convertValue(args, VolleyJson.class);
    List<Coord> hits = ai.reportDamage(volleyJson.convertToCoords());
    List<CoordJson> hitsJson = makeCoordJson(hits);

    VolleyJson response = new VolleyJson(hitsJson);
    JsonNode node = JsonUtils.serializeRecord(response);
    sendResponse(node, "report-damage");
  }


  private void handleHits(JsonNode args) {
    VolleyJson volleyJson = this.mapper.convertValue(args, VolleyJson.class);
    ai.successfulHits(volleyJson.convertToCoords());

    JsonNode node = mapper.createObjectNode();
    sendResponse(node, "successful-hits");
  }


  private void handleEnd(JsonNode args) {
    EndGameJson endJson = this.mapper.convertValue(args, EndGameJson.class);
    ai.endGame(endJson.gameResult(), endJson.reason());
    JsonNode node = mapper.createObjectNode();
    sendResponse(node, "end-game");

    try {
      this.server.close();
    } catch (Exception e) {
      System.out.println("Error closing server");
    }
  }



  private List<CoordJson> makeCoordJson(List<Coord> coords) {
    List<CoordJson> coordJsons = new ArrayList<>();
    for (Coord c : coords) {
      coordJsons.add(c.makeCoordJson());
    }
    return coordJsons;
  }

}
