package com.freddyfofe.s4n.drone.entity;

import com.freddyfofe.s4n.drone.utility.Direction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Delivery {

  private Position position;
  private Direction direction;

  public Delivery(Position position, Direction direction) {
    this.position = new Position(position.getPosX(), position.getPosY());
    this.direction = direction;
  }

}
