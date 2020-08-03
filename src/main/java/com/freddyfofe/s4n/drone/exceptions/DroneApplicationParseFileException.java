package com.freddyfofe.s4n.drone.exceptions;

/**
 * Basic runtime exception for unchecked file exceptions
 */
public class DroneApplicationParseFileException extends RuntimeException {

  public DroneApplicationParseFileException(String message) {
    super(message);
  }

}
