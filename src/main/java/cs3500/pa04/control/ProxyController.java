package cs3500.pa04.control;

import static cs3500.pa04.control.GameType.SINGLE;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Online Battleship
 */
public class ProxyController extends AbstractController {
  private Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final ObjectMapper mapper = new ObjectMapper();

  // TODO: TRY CATCH IOEXCEPTION when constructing a Proxy Controller
  public ProxyController(Player p1, View v, Socket server) throws IOException {
    super(p1,v);
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
  }


  public void runApp() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }
  }


  /**
   * Delegates out the servers messages based on the name in the message
   *
   * @param message
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


  /**
   * Responds to the server with a github username and the type
   * of game which is single player
   *
   * @param args the Server prompt
   */
  private void handleJoin(JsonNode args) {
      JoinJson joinJson = new JoinJson("connorgarmey", SINGLE);
      this.out.println(joinJson);
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
        p1.setup(setupParams.width(), setupParams.height(), setupParams.fleet().makeMap());

    for (Ship s : ships) {
      shipJsons.add(s.makeShipJSON());
    }

    FleetJson response = new FleetJson(shipJsons);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    this.out.println(jsonResponse);
  }


  private void handleShots(JsonNode args) {
    List<Coord> shots = p1.takeShots();
    List<CoordJson> shotsJson = makeCoordJson(shots);

    VolleyJson response = new VolleyJson(shotsJson);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    this.out.println(jsonResponse);
  }


  private void handleDamage(JsonNode args) {
    VolleyJson volleyJson = this.mapper.convertValue(args, VolleyJson.class);
    List<Coord> hits = p1.reportDamage(volleyJson.convertToCoords());
    List<CoordJson> hitsJson = makeCoordJson(hits);

    VolleyJson response = new VolleyJson(hitsJson);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    this.out.println(jsonResponse);
  }


  private void handleHits(JsonNode args) {
    VolleyJson volleyJson = this.mapper.convertValue(args, VolleyJson.class);
    p1.successfulHits(volleyJson.convertToCoords());

    JsonNode jsonResponse = mapper.createObjectNode();
    this.out.println(jsonResponse);
  }


  private void handleEnd(JsonNode args) {
    EndGameJson endJson = this.mapper.convertValue(args, EndGameJson.class);
    p1.endGame(endJson.gameResult(), endJson.reason());
    JsonNode jsonResponse = mapper.createObjectNode();
    this.out.println(jsonResponse);
  }





  private List<CoordJson> makeCoordJson(List<Coord> coords) {
    List<CoordJson> coordJsons = new ArrayList<>();
    for (Coord c : coords) {
      coordJsons.add(c.makeCoordJson());
    }
    return coordJsons;
  }


  private VolleyJson makeVolleyJson(List<Coord> volley) {
    List<CoordJson> coordJsons = new ArrayList<>();
    for (Coord c : volley) {
      coordJsons.add(c.makeCoordJson());
    }
    return new VolleyJson(coordJsons);
  }

}
