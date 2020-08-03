package com.freddyfofe.s4n.drone.service.implementation;

import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationParseFileException;
import com.freddyfofe.s4n.drone.service.IFileReaderService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReaderServiceImpl implements IFileReaderService {

  private static Logger LOGGER = LoggerFactory.getLogger(FileReaderServiceImpl.class);

  /**
   * Reads the files and validate its lines and names
   *
   * @param directory
   * @return a map of K - droneId V - Commands Lines of the delivery
   */
  @Override
  public Map<String, List<String>> readFiles(File directory) throws DroneApplicationException {

    Map inputInformation = new HashMap();

    File file = new File(directory.toURI());
    String[] pathNames = file.list();

    for (String pathName : pathNames) {
      if (isValidInputFileName(pathName)) {
        List<String> lines = readFile(retrieveFile(file.getAbsolutePath() + "/" + pathName));
        inputInformation.put(getDroneId(pathName), lines);
      }
    }

    return inputInformation;
  }

  private List<String> readFile(File file) throws DroneApplicationException {
    List<String> lines;

    try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
      lines = stream.map(item -> this.isInputLineValid(item)).collect(Collectors.toList());
    } catch (IOException e) {
      throw new DroneApplicationException("No se pudo leer el archivo");
    }

    return lines;
  }

  private String isInputLineValid(String line) throws DroneApplicationParseFileException {
    if (this.isLineACommand(line)) {
      return line;
    } else {
      throw new DroneApplicationParseFileException(
          "Error leyendo la línea [" + line + "]: no tiene formato válido");
    }
  }

  /**
   * Use a regular expression to validate if the line has a correct format Player->Score
   *
   * @param line
   * @return
   */
  private Boolean isLineACommand(String line) {
    String regex = "^([ADI]+)$";
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(line).matches();
  }

  private Boolean isValidInputFileName(String line) {
    String regex = "^[iI][nN](([0][1-9])|([1-2][0-9]))\\.txt$";
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(line).matches();
  }

  /**
   * Validates if file exists before execution
   *
   * @param path
   * @return File
   */
  private File retrieveFile(String path) throws DroneApplicationException {
    File file = new File(path);
    if (file.exists() && !file.isDirectory()) {
      LOGGER.info("Procesando archivo: " + path);
      return file;
    } else {
      throw new DroneApplicationException("No se pudo encontrar el archivo");
    }
  }

  private String getDroneId(String fileName) {
    return fileName.toLowerCase()
        .replace("in", "")
        .replace(".txt", "");
  }

}
