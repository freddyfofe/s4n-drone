package com.freddyfofe.s4n.drone.view;

import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.service.IDroneDeliveryService;
import com.freddyfofe.s4n.drone.service.IFileReaderService;
import com.freddyfofe.s4n.drone.service.implementation.DroneDeliveryServiceImpl;
import com.freddyfofe.s4n.drone.service.implementation.FileReaderServiceImpl;
import com.freddyfofe.s4n.drone.utility.DroneApplicationConfig;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroneApplicationConsole {

  private static Logger LOGGER = LoggerFactory.getLogger(DroneApplicationConsole.class);
  private final String INPUT_FILES_ROUTE = "INPUT_FILES_ROUTE";

  IFileReaderService fileReaderService = new FileReaderServiceImpl();
  IDroneDeliveryService droneDeliveryService = new DroneDeliveryServiceImpl();

  public void run() throws URISyntaxException, DroneApplicationException {
    LOGGER.info("**** Se ha iniciado la ejecución *****");
    URL fileUrl =
        this.getClass().getClassLoader()
            .getResource(DroneApplicationConfig.getProperty(INPUT_FILES_ROUTE));
    File file = new File(fileUrl.toURI());
    LOGGER.info("Leyendo los archivos");
    Map inputInformation = fileReaderService.readFiles(file);
    LOGGER.info("Validando las entradas");
    droneDeliveryService.validateInputs(inputInformation);

  }

}
