package com.freddyfofe.s4n.drone.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.freddyfofe.s4n.drone.entity.Itinerary;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.service.IDroneDeliveryService;
import com.freddyfofe.s4n.drone.utility.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DroneDeliveryServiceImplTests {

  IDroneDeliveryService droneDeliveryService = DroneDeliveryServiceImpl.getInstance();

  @Test
  @DisplayName("Valid Input Should be Ok")
  public void shouldBeValidInput() throws DroneApplicationException {
    Map<String, List<String>> simpleInputInformation = getSimpleInputInformation(1);
    droneDeliveryService.validateInputs(simpleInputInformation);
  }

  @Test
  @DisplayName("Input has more lunches than it can carry")
  public void shouldBeInvalidInputWithExceedLunchCapacity() {
    Map<String, List<String>> simpleInputInformation = getSimpleInputInformation(20);
    simpleInputInformation.get("01").add("AAIADDAA");
    assertThrows(DroneApplicationException.class, () -> {
      droneDeliveryService.validateInputs(simpleInputInformation);
    });
  }

  @Test
  @DisplayName("Input has drone quantity exceeded")
  public void shouldBeInvalidInputWithExceedDroneQuantity() {
    Map<String, List<String>> simpleInputInformation = getSimpleInputInformation(21);
    assertThrows(DroneApplicationException.class, () -> {
      droneDeliveryService.validateInputs(simpleInputInformation);
    });
  }

  @Test
  @DisplayName("Input should be converted successfully into itineraries")
  public void shouldBeConvertedSuccessfully() {
    Map<String, List<String>> simpleInputInformation = getSimpleInputInformation(20);
    List<Itinerary> itineraries = droneDeliveryService.prepareData(simpleInputInformation);
    Assertions.assertEquals(20, itineraries.size());
  }

  @Test
  @DisplayName("Itineraries are processed correctly")
  public void shouldBeProcessedSuccessfully() {
    Map<String, List<String>> simpleInputInformation = getSimpleInputInformation(20);
    List<Itinerary> itineraries = droneDeliveryService.prepareData(simpleInputInformation);
    droneDeliveryService.processDeliveries(itineraries);

    assertEquals(0, itineraries.get(0).getDrone().getPosition().getPosX());
    assertEquals(0, itineraries.get(0).getDrone().getPosition().getPosY());
    assertEquals(Direction.WEST, itineraries.get(0).getDrone().getDirection());
  }

  private Map<String, List<String>> getSimpleInputInformation(int droneQuantity) {
    Map<String, List<String>> inputInformation = new HashMap<>();
    for (int i = 1; i <= droneQuantity; i++) {
      List<String> routes = new ArrayList<>();
      routes.add("AAAAIAA");
      routes.add("DDDAIAD");
      routes.add("AAIADAD");
      inputInformation.put(String.format("%02d", i), routes);
    }
    return inputInformation;
  }

}
