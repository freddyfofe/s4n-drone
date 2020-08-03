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
    this.direction.turnLeft();
  }

  public void turnRight() {
    this.direction.turnRight();
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

}
