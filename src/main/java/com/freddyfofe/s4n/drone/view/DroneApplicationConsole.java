package com.freddyfofe.s4n.drone.view;

import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.service.IFileReaderService;
import com.freddyfofe.s4n.drone.service.implementation.FileReaderServiceImpl;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroneApplicationConsole {

  private static Logger LOGGER = LoggerFactory.getLogger(DroneApplicationConsole.class);
  private final String INPUT_FILES_ROUTE = "INPUT_FILES_ROUTE";

  IFileReaderService fileReaderService = new FileReaderServiceImpl();

  Properties properties;

  public DroneApplicationConsole() throws DroneApplicationException {
    this.loadPropertiesFile();
  }

  public void run() throws URISyntaxException, DroneApplicationException {
    LOGGER.info("**** Se ha iniciado la ejecución *****");
    URL fileUrl =
        this.getClass().getClassLoader().getResource(properties.getProperty(INPUT_FILES_ROUTE));
    File file = new File(fileUrl.toURI());
    LOGGER.info("Leyendo los archivos");
    Map inputInformation = fileReaderService.readFiles(file);

  }

  /**
   * Loads the properties file found in the resources dir
   *
   * @throws DroneApplicationException
   */
  private void loadPropertiesFile() throws DroneApplicationException {
    String propertiesFileName = "droneApplication.properties";
    try {
      URL fileInputStream = this.getClass().getClassLoader().getResource(propertiesFileName);
      File propFile = new File(fileInputStream.toURI());
      properties = new Properties();
      FileReader reader = new FileReader(propFile);
      properties.load(reader);
      reader.close();
    } catch (URISyntaxException | IOException e) {
      throw new DroneApplicationException("Error al cargar el archivo de propiedades",
          e.getCause());
    } catch (NullPointerException e) {
      throw new DroneApplicationException("Error: No se encuentra el archivo de propiedades",
          e.getCause());
    }
  }

}
