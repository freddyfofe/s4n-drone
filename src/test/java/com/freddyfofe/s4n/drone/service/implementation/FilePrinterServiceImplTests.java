package com.freddyfofe.s4n.drone.service.implementation;

import com.freddyfofe.s4n.drone.entity.Itinerary;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.service.IDroneDeliveryService;
import com.freddyfofe.s4n.drone.service.IFilePrinterService;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FilePrinterServiceImplTests {

  IFilePrinterService filePrinterService = FilePrinterServiceImpl.getInstance();
  IDroneDeliveryService droneDeliveryService = DroneDeliveryServiceImpl.getInstance();

  @Test
  @DisplayName("The files should be read and should be correct")
  public void fileShouldBeCorrect() throws URISyntaxException, DroneApplicationException {
    URL inputUrl =
        this.getClass().getClassLoader()
            .getResource("out/success");
    File file = new File(inputUrl.toURI());

    filePrinterService.printResults(getSimpleItinerary(), file);

  }

  private List<Itinerary> getSimpleItinerary() {
    Map<String, List<String>> simpleInputInformation = getSimpleInputInformation(20);
    List<Itinerary> itineraries = droneDeliveryService.prepareData(simpleInputInformation);
    droneDeliveryService.processDeliveries(itineraries);
    return itineraries;
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
