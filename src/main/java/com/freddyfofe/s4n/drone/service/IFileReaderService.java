package com.freddyfofe.s4n.drone.service;

import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import java.io.File;
import java.util.List;
import java.util.Map;

public interface IFileReaderService {

  Map<String, List<String>> readFiles(File directory) throws DroneApplicationException;

}