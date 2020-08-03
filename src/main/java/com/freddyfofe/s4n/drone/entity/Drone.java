package com.freddyfofe.s4n.drone.entity;

import com.freddyfofe.s4n.drone.utility.Direction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drone {

  private int id;
  private Position position;
  private int lunchCapacity;
  private Direction direction;

  public void turnLeft() {
    this.direction = this.direction.turnLeft();
  }

  public void turnRight() {
    this.direction = this.direction.turnRight();
  }

  public void moveForward() {
    switch (this.direction) {
      case NORTH:
        this.position.setPosY(this.position.getPosY() + 1);
        break;
      case EAST:
        this.position.setPosX(this.position.getPosX() + 1);
        break;
      case SOUTH:
        this.position.setPosY(this.position.getPosY() - 1);
        break;
      case WEST:
        this.position.setPosX(this.position.getPosX() - 1);
        break;
    }
  }

  public boolean simulateMoveForward(int mapDimension) {
    switch (this.getDirection()) {
      case NORTH:
        return (this.getPosition().getPosY() + 1 <= mapDimension);
      case EAST:
        return (this.getPosition().getPosX() + 1 <= mapDimension);
      case SOUTH:
        return (this.getPosition().getPosY() - 1 >= -mapDimension);
      case WEST:
        return (this.getPosition().getPosX() - 1 >= -mapDimension);
    }
    return false;
  }

}
