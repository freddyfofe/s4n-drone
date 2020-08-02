package com.freddyfofe.s4n.drone.entity;

public class Drone {
  private Position position;
  private int lunchCapacity;

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public int getLunchCapacity() {
    return lunchCapacity;
  }

  public void setLunchCapacity(int lunchCapacity) {
    this.lunchCapacity = lunchCapacity;
  }
}
