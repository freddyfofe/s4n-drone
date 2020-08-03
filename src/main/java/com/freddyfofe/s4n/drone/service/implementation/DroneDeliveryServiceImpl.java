package com.freddyfofe.s4n.drone.service.implementation;

import com.freddyfofe.s4n.drone.entity.Drone;
import com.freddyfofe.s4n.drone.entity.Itinerary;
import com.freddyfofe.s4n.drone.entity.Position;
import com.freddyfofe.s4n.drone.entity.Route;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.service.IDroneDeliveryService;
import com.freddyfofe.s4n.drone.utility.Direction;
import com.freddyfofe.s4n.drone.utility.DroneApplicationConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DroneDeliveryServiceImpl implements IDroneDeliveryService {

  private final String DRONE_QUANTITY = "DRONE_QUANTITY";
  private final String DRONE_LUNCH_CAPACITY = "DRONE_LUNCH_CAPACITY";

  @Override
  public void validateInputs(Map<String, List<String>> inputInformation)
      throws DroneApplicationException {
    if (Integer.parseInt(DroneApplicationConfig.getProperty(DRONE_QUANTITY)) <
        inputInformation.size()) {
      throw new DroneApplicationException("Existen más archivos de entrada de los que deberían");
    }

    for (Map.Entry<String, List<String>> input : inputInformation.entrySet()
    ) {
      if (input.getValue().size() >
          Integer.parseInt(DroneApplicationConfig.getProperty(DRONE_LUNCH_CAPACITY))) {
        throw new DroneApplicationException(
            "El archivo " + input.getKey() + " excede su capacidad");
      }
    }
  }

  @Override
  public List<Itinerary> prepareData(Map<String, List<String>> inputInformation) {

    List<Itinerary> itineraries = new ArrayList<>();

    for (Map.Entry<String, List<String>> information : inputInformation.entrySet()
    ) {
      Drone drone = createInitialPositionDrone(information.getKey());
      Itinerary itinerary = new Itinerary();
      itinerary.setDrone(drone);
      itinerary.setRoutes(
          information.getValue().stream().map(s -> new Route(s)).collect(Collectors.toList()));

      itineraries.add(itinerary);
    }

    return itineraries;

  }

  @Override
  public void processDeliveries(List<Itinerary> itineraries) {
    
  }

  private Drone createInitialPositionDrone(String id) {
    Drone drone = new Drone();
    drone.setId(Integer.parseInt(id));
    drone.setPosition(new Position(0, 0));
    drone.setDirection(Direction.NORTH);
    drone.setLunchCapacity(
        Integer.parseInt(DroneApplicationConfig.getProperty(DRONE_LUNCH_CAPACITY)));

    return drone;
  }
}
