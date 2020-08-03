package com.freddyfofe.s4n.drone.entity;

import com.freddyfofe.s4n.drone.utility.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DroneTests {

  @Test
  @DisplayName("Turn Left options should be correct")
  public void shouldBeCorrectWhenTurningLeft() {
    Drone drone = new Drone();
    drone.setDirection(Direction.NORTH);
    Assertions.assertEquals(drone.getDirection(), Direction.NORTH);
    drone.turnLeft();
    Assertions.assertEquals(drone.getDirection(), Direction.WEST);
    drone.turnLeft();
    Assertions.assertEquals(drone.getDirection(), Direction.SOUTH);
    drone.turnLeft();
    Assertions.assertEquals(drone.getDirection(), Direction.EAST);
    drone.turnLeft();
    Assertions.assertEquals(drone.getDirection(), Direction.NORTH);
  }

  @Test
  @DisplayName("Turn Right options should be correct")
  public void shouldBeCorrectWhenTurningRight() {
    Drone drone = new Drone();
    drone.setDirection(Direction.NORTH);
    Assertions.assertEquals(drone.getDirection(), Direction.NORTH);
    drone.turnRight();
    Assertions.assertEquals(drone.getDirection(), Direction.EAST);
    drone.turnRight();
    Assertions.assertEquals(drone.getDirection(), Direction.SOUTH);
    drone.turnRight();
    Assertions.assertEquals(drone.getDirection(), Direction.WEST);
    drone.turnRight();
    Assertions.assertEquals(drone.getDirection(), Direction.NORTH);
  }

  @Test
  @DisplayName("Moving forward should be correct with a preset direction")
  public void shouldMoveWhenMovingForward() {
    Drone drone = new Drone();

    drone.setDirection(Direction.NORTH);
    drone.setPosition(new Position(0, 0));
    drone.moveForward();

    Assertions.assertEquals(drone.getPosition().getPosX(), 0);
    Assertions.assertEquals(drone.getPosition().getPosY(), 1);

    drone.setDirection(Direction.EAST);
    drone.setPosition(new Position(0, 0));
    drone.moveForward();

    Assertions.assertEquals(drone.getPosition().getPosX(), 1);
    Assertions.assertEquals(drone.getPosition().getPosY(), 0);

    drone.setDirection(Direction.SOUTH);
    drone.setPosition(new Position(0, 0));
    drone.moveForward();

    Assertions.assertEquals(drone.getPosition().getPosX(), 0);
    Assertions.assertEquals(drone.getPosition().getPosY(), -1);

    drone.setDirection(Direction.WEST);
    drone.setPosition(new Position(0, 0));
    drone.moveForward();

    Assertions.assertEquals(drone.getPosition().getPosX(), -1);
    Assertions.assertEquals(drone.getPosition().getPosY(), 0);
  }

  @Test
  @DisplayName("Simulation of Moving forward should be correct with a preset direction")
  public void shouldReturnOkWhenSimulateMovingForward() {
    int mapDimension = 10;
    Drone drone = new Drone();
    drone.setId(1);
    drone.setLunchCapacity(3);
    drone.setPosition(new Position(0, 0));
    testSimulateMovingForwardInAllDirections(true, true, true, true, drone, mapDimension);
  }

  @Test
  @DisplayName("Simulation of Moving forward should be correct with boundary values")
  public void shouldReturnOkWhenSimulateMovingForwardWithBoundaryValues() {
    int mapDimension = 10;
    Drone drone = new Drone();

    //Within range values
    drone.setPosition(new Position(9, 9));
    testSimulateMovingForwardInAllDirections(true, true, true, true, drone, mapDimension);
    drone.setPosition(new Position(-9, 9));
    testSimulateMovingForwardInAllDirections(true, true, true, true, drone, mapDimension);
    drone.setPosition(new Position(9, -9));
    testSimulateMovingForwardInAllDirections(true, true, true, true, drone, mapDimension);
    drone.setPosition(new Position(-9, -9));
    testSimulateMovingForwardInAllDirections(true, true, true, true, drone, mapDimension);

    //Out of range values
    drone.setPosition(new Position(10, 10));
    testSimulateMovingForwardInAllDirections(false, true, true, false, drone, mapDimension);
    drone.setPosition(new Position(-10, 10));
    testSimulateMovingForwardInAllDirections(false, false, true, true, drone, mapDimension);
    drone.setPosition(new Position(10, -10));
    testSimulateMovingForwardInAllDirections(true, true, false, false, drone, mapDimension);
    drone.setPosition(new Position(-10, -10));
    testSimulateMovingForwardInAllDirections(true, false, false, true, drone, mapDimension);
  }

  private void testSimulateMovingForwardInAllDirections(boolean northValue, boolean westValue,
                                                        boolean southValue, boolean eastValue,
                                                        Drone drone, int mapDimension) {
    drone.setDirection(Direction.NORTH);
    Assertions.assertEquals(northValue, drone.simulateMoveForward(10));
    drone.setDirection(Direction.WEST);
    Assertions.assertEquals(westValue, drone.simulateMoveForward(10));
    drone.setDirection(Direction.EAST);
    Assertions.assertEquals(eastValue, drone.simulateMoveForward(10));
    drone.setDirection(Direction.SOUTH);
    Assertions.assertEquals(southValue, drone.simulateMoveForward(10));
  }

}
