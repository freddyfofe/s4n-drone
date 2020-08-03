package com.freddyfofe.s4n.drone.exceptions;

public class DroneApplicationException extends Exception {

  public DroneApplicationException(String message) {
    super(message);
  }

  public DroneApplicationException(String message, Throwable e) {
    super(message, e);
  }

}
