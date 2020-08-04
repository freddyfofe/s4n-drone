package com.freddyfofe.s4n.drone.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Itinerary {

  List<Delivery> deliveries;
  Drone drone;
  List<Route> routes;
  boolean successful;

  public void addDelivery(Delivery delivery) {
    if (null == deliveries) {
      deliveries = new ArrayList<>();
    }
    deliveries.add(delivery);
  }

}
