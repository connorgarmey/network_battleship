package cs3500.pa04.control.json;

import static cs3500.pa04.model.ShipType.BATTLESHIP;
import static cs3500.pa04.model.ShipType.CARRIER;
import static cs3500.pa04.model.ShipType.DESTROYER;
import static cs3500.pa04.model.ShipType.SUBMARINE;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ShipType;
import java.util.HashMap;
import java.util.Map;

public record FleetSpecJson(
    @JsonProperty("CARRIER")
    int numCarrier,
    @JsonProperty("BATTLESHIP")
    int numBattle,
    @JsonProperty("DESTROYER")
    int numDestroy,
    @JsonProperty("SUBMARINE")
    int numSub) {

    public Map<ShipType, Integer> makeMap() {
        Map<ShipType, Integer> map = new HashMap<>();

        map.put(CARRIER, this.numCarrier);
        map.put(BATTLESHIP, this.numBattle);
        map.put(DESTROYER, this.numDestroy);
        map.put(SUBMARINE, this.numSub);

        return map;
    }

}


