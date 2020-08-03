package com.freddyfofe.s4n.drone.service;

import com.freddyfofe.s4n.drone.entity.Drone;
import com.freddyfofe.s4n.drone.entity.Itinerary;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import java.util.List;
import java.util.Map;

public interface IDroneDeliveryService {

  void validateInputs(Map<String, List<String>> inputInformation) throws DroneApplicationException;

  List<Itinerary> prepareData(Map<String, List<String>> inputInformation);

  void processDeliveries(List<Itinerary> itineraries);

}
