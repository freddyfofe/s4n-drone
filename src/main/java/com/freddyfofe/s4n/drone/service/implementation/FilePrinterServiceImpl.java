package com.freddyfofe.s4n.drone.service.implementation;

import com.freddyfofe.s4n.drone.entity.Delivery;
import com.freddyfofe.s4n.drone.entity.Itinerary;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.service.IFilePrinterService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilePrinterServiceImpl implements IFilePrinterService {

  private volatile static FilePrinterServiceImpl instance;

  private FilePrinterServiceImpl(){}

  public static FilePrinterServiceImpl getInstance() {
    if (instance == null) {
      synchronized (FilePrinterServiceImpl.class) {
        if (instance == null) {
          instance = new FilePrinterServiceImpl();
        }
      }
    }
    return instance;
  }

  @Override
  public void printResults(List<Itinerary> itineraries, File directory)
      throws DroneApplicationException {
    for (Itinerary itinerary : itineraries) {
      printItinerary(itinerary, directory);
    }
  }

  private void printItinerary(Itinerary itinerary, File directory)
      throws DroneApplicationException {


    File outFile = new File(
        directory.getAbsolutePath() + "/out" + String.format("%02d", itinerary.getDrone().getId()) +
            ".txt");
    try {
      FileWriter fw = new FileWriter(outFile, false);
      fw.write("== Reporte de entregas ==" + System.lineSeparator());
      for (Delivery delivery : itinerary.getDeliveries()) {
        fw.write(String.format("(%d, %d) dirección %s", delivery.getPosition().getPosX(),
            delivery.getPosition().getPosY(), delivery.getDirection().getDirectionDisplay()) +
            System.lineSeparator());
      }
      fw.close();
    } catch (IOException e) {
      throw new DroneApplicationException(
          "Error al crear el archivo de salida para el dron: " + itinerary.getDrone().getId(),
          e.getCause());
    }
  }
}
