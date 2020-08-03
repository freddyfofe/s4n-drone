package com.freddyfofe.s4n.drone.service;

import com.freddyfofe.s4n.drone.entity.Itinerary;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import java.io.File;
import java.util.List;

public interface IFilePrinterService {

  void printResults(List<Itinerary> itineraries, File directory) throws DroneApplicationException;

}
