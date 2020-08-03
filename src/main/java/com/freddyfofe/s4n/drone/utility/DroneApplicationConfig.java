package com.freddyfofe.s4n.drone.utility;

import com.freddyfofe.s4n.drone.DroneApplication;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class DroneApplicationConfig {
  private static Properties applicationProperties = new Properties();

  static {
    try {
      URL fileInputUrl =
          DroneApplication.class.getClassLoader().getResource("droneApplication.properties");
      File propFile = new File(fileInputUrl.toURI());
      FileReader reader = new FileReader(propFile);
      applicationProperties.load(reader);
    } catch (URISyntaxException | IOException e) {
      new DroneApplicationException("Error al cargar el archivo de propiedades",
          e.getCause()).printStackTrace();
    } catch (NullPointerException e) {
      new DroneApplicationException("Error: No se encuentra el archivo de propiedades",
          e.getCause()).printStackTrace();
    }
  }

  public static String getProperty(String key) {
    return applicationProperties.getProperty(key);
  }
}