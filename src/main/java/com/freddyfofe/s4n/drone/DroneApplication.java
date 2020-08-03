package com.freddyfofe.s4n.drone;

import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.view.DroneApplicationConsole;
import java.net.URISyntaxException;

public class DroneApplication {

  public static void main(String[] args) throws URISyntaxException, DroneApplicationException {
    DroneApplicationConsole droneApplicationConsole = new DroneApplicationConsole();
    droneApplicationConsole.run();
  }

}
