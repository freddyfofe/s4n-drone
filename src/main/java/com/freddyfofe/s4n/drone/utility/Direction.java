package com.freddyfofe.s4n.drone.utility;

public enum Direction {
  NORTH("Norte"),
  EAST("Oriente"),
  SOUTH("Sur"),
  WEST("Occidente");

  private static Direction[] directions = values();

  private String directionDisplay;

  Direction(final String directionDisplay) {
    this.directionDisplay = directionDisplay;
  }

  public String getDirectionDisplay() {
    return this.directionDisplay;
  }

  public Direction turnRight() {
    return directions[(this.ordinal()+1) % directions.length];
  }

  public Direction turnLeft() {
    return directions[(this.ordinal()+ directions.length - 1) % directions.length];
  }

}
