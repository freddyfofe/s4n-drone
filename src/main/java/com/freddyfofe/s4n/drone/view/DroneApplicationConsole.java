package com.freddyfofe.s4n.drone.view;

import com.freddyfofe.s4n.drone.entity.Itinerary;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.service.IDroneDeliveryService;
import com.freddyfofe.s4n.drone.service.IFilePrinterService;
import com.freddyfofe.s4n.drone.service.IFileReaderService;
import com.freddyfofe.s4n.drone.service.implementation.DroneDeliveryServiceImpl;
import com.freddyfofe.s4n.drone.service.implementation.FilePrinterServiceImpl;
import com.freddyfofe.s4n.drone.service.implementation.FileReaderServiceImpl;
import com.freddyfofe.s4n.drone.utility.DroneApplicationConfig;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroneApplicationConsole {

  private static Logger LOGGER = LoggerFactory.getLogger(DroneApplicationConsole.class);
  private final String INPUT_FILES_ROUTE = "INPUT_FILES_ROUTE";
  private final String OUTPUT_FILES_ROUTE = "OUTPUT_FILES_ROUTE";

  IFileReaderService fileReaderService = FileReaderServiceImpl.getInstance();
  IDroneDeliveryService droneDeliveryService = DroneDeliveryServiceImpl.getInstance();
  IFilePrinterService filePrinterService = FilePrinterServiceImpl.getInstance();

  public void run() throws URISyntaxException, DroneApplicationException {
    LOGGER.info("**** Se ha iniciado la ejecución *****");
    URL inputUrl =
        this.getClass().getClassLoader()
            .getResource(DroneApplicationConfig.getProperty(INPUT_FILES_ROUTE));
    URL outputUrl =
        this.getClass().getClassLoader()
            .getResource(DroneApplicationConfig.getProperty(OUTPUT_FILES_ROUTE));
    File inputDirectory = new File(inputUrl.toURI());
    File outputDirectory = new File(outputUrl.toURI());
    LOGGER.info("Leyendo los archivos");
    Map inputInformation = fileReaderService.readFiles(inputDirectory);
    LOGGER.info("Validando las entradas");
    droneDeliveryService.validateInputs(inputInformation);
    LOGGER.info("Preparando la Data");
    List<Itinerary> itineraries = droneDeliveryService.prepareData(inputInformation);
    LOGGER.info("Ejecutando las entregas");
    droneDeliveryService.processDeliveries(itineraries);
    LOGGER.info("Escribiendo los resultados");
    filePrinterService.printResults(itineraries, outputDirectory);
  }

}
