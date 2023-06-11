package cs3500.pa04.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.control.json.CoordJson;
import cs3500.pa04.control.json.EndGameJson;
import cs3500.pa04.control.json.FleetSpecJson;
import cs3500.pa04.control.json.JsonUtils;
import cs3500.pa04.control.json.MessageJson;
import cs3500.pa04.control.json.SetupJson;
import cs3500.pa04.control.json.VolleyJson;
import cs3500.pa04.model.BoardImpl;
import cs3500.pa04.model.BoardInteractions;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.ShipTest;
import cs3500.pa04.view.ServerView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ProxyControllerTest {

  private ByteArrayOutputStream testLog;
  private ProxyController controller;
  private ObjectMapper mapper;
  private Player ai;
  private ServerView view;
  private ShipTest st;



  @BeforeEach
  void setUp() {
    this.testLog = new ByteArrayOutputStream(2048);
    this.mapper = new ObjectMapper();
    BoardInteractions board = new BoardImpl();
    st = new ShipTest();
    st.initData();
    ai = st.p1;
    view = new ServerView(new StringBuilder());
  }

  @Test
  void runApp() {
  }

  @Test
  void testJoin() {
    JsonNode node = mapper.createObjectNode();
    MessageJson empty = new MessageJson("join", node);
    JsonNode serialized = JsonUtils.serializeRecord(empty);

    Mocket socket = new Mocket(this.testLog, List.of(serialized.toString()));

    try {
      controller = new ProxyController(ai, view, socket);
    } catch (IOException e) {
      fail();
    }

    controller.runApp();
    String expected = "{\"method-name\":\"join\",\"arguments\":{\"name\":\"connor"
        + "garmey\",\"game-type\":\"SINGLE\"}}\n";
    assertEquals(expected, logToString());
  }

  @Test
  void testSetUp() {
    FleetSpecJson fleetSpec = new FleetSpecJson(1, 1, 1, 1);
    SetupJson setupJson = new SetupJson(8, 8, fleetSpec);
    JsonNode message = createSampleMessage("setup", setupJson);

    Mocket socket = new Mocket(this.testLog, List.of(message.toString()));

    try {
      this.controller = new ProxyController(ai, view, socket);
    } catch (IOException e) {
      fail();
    }

    controller.runApp();
    String expected = "{\"method-name\":\"setup\",\"arguments\":{\"fleet\":[{\"coo"
        + "rd\":{\"x\":1,\"y\":1},\"length\":6,\"direction\":\"VERTICAL\"},{\"coo"
        + "rd\":{\"x\":5,\"y\":2},\"length\":5,\"direction\":\"VERTICAL\"},{\"coo"
        + "rd\":{\"x\":4,\"y\":0},\"length\":4,\"direction\":\"VERTICAL\"},{\"coo"
        + "rd\":{\"x\":0,\"y\":0},\"length\":3,\"direction\":\"VERTICAL\"}]}}\n";
    assertEquals(expected, logToString());
  }

  @Test
  void testShots() {
    JsonNode node = mapper.createObjectNode();
    MessageJson empty = new MessageJson("take-shots", node);
    JsonNode message = JsonUtils.serializeRecord(empty);

    Mocket socket = new Mocket(this.testLog, List.of(message.toString()));

    try {
      this.controller = new ProxyController(ai, view, socket);
    } catch (IOException e) {
      fail();
    }

    controller.runApp();
    String expected = "{\"method-name\":\"take-shots\",\"argum"
        +
        "ents\":{\"coordinates\":[{\"x\":3,\"y\":2}]}}\n";
    assertEquals(expected, logToString());

  }

  @Test
  void testDamage() {
    CoordJson coord1 = new CoordJson(1, 1);
    CoordJson coord2 = new CoordJson(0, 0);
    VolleyJson volley = new VolleyJson(new ArrayList<>(Arrays.asList(coord1, coord2)));
    JsonNode message = createSampleMessage("report-damage", volley);

    Mocket socket = new Mocket(this.testLog, List.of(message.toString()));

    try {
      this.controller = new ProxyController(ai, view, socket);
    } catch (IOException e) {
      fail();
    }

    controller.runApp();
    String expected = "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[]}}\n";
    assertEquals(expected, logToString());

  }

  @Test
  void testSuccess() {
    CoordJson coord1 = new CoordJson(1, 1);
    CoordJson coord2 = new CoordJson(0, 0);
    VolleyJson volley = new VolleyJson(new ArrayList<>(Arrays.asList(coord1, coord2)));
    JsonNode message = createSampleMessage("successful-hits", volley);

    Mocket socket = new Mocket(this.testLog, List.of(message.toString()));

    try {
      this.controller = new ProxyController(ai, view, socket);
    } catch (IOException e) {
      fail();
    }

    controller.runApp();
    String expected = "{\"method-name\":\"successful-hits\",\"arguments\":{}}\n";
    assertEquals(expected, logToString());

  }


  @Test
  void testEndLose() {
    EndGameJson end = new EndGameJson(GameResult.LOSE, "You lose!");
    JsonNode message = createSampleMessage("end-game", end);

    Mocket socket = new Mocket(this.testLog, List.of(message.toString()));

    try {
      this.controller = new ProxyController(ai, view, socket);
    } catch (IOException e) {
      fail();
    }


    controller.runApp();
    String expected = "{\"method-name\":\"end-game\",\"arguments\":{}}\n";
    assertEquals(expected, logToString());

  }

  @Test
  void testEndWin() {
    EndGameJson end = new EndGameJson(GameResult.WIN, "You win!");
    JsonNode message = createSampleMessage("end-game", end);

    Mocket socket = new Mocket(this.testLog, List.of(message.toString()));

    try {
      this.controller = new ProxyController(ai, view, socket);
    } catch (IOException e) {
      fail();
    }


    controller.runApp();
    String expected = "{\"method-name\":\"end-game\",\"arguments\":{}}\n";
    assertEquals(expected, logToString());

  }

  @Test
  void testEndTie() {
    EndGameJson end = new EndGameJson(GameResult.DRAW, "All ships sunk!");
    JsonNode message = createSampleMessage("end-game", end);

    Mocket socket = new Mocket(this.testLog, List.of(message.toString()));

    try {
      this.controller = new ProxyController(ai, view, socket);
    } catch (IOException e) {
      fail();
    }


    controller.runApp();
    String expected = "{\"method-name\":\"end-game\",\"arguments\":{}}\n";
    assertEquals(expected, logToString());

  }


  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson = new MessageJson(messageName,
        JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }



  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }
}