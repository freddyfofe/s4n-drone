package com.freddyfofe.s4n.drone.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Itinerary {

  List<Delivery> deliveries;
  Drone drone;
  List<Route> routes;

}
