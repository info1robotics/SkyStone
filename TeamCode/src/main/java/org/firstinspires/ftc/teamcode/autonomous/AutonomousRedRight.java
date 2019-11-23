package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Autonomie Roșu Dreapta")
public class AutonomousRedRight extends AutonomousBasic {
    public void runCommands() throws InterruptedException {
        MOVEMENT_SPEED = 0.5;

        movementController.moveRight(MOVEMENT_SPEED, 52.0);
        movementController.moveForward(MOVEMENT_SPEED, 85.0);
        armController.closeBarrier();
        Thread.sleep(1000);
        movementController.moveBackward(MOVEMENT_SPEED, 85.0);
        armController.openBarrier();
        Thread.sleep(500);
        movementController.moveLeft(MOVEMENT_SPEED, 83.0);
        movementController.moveForward(MOVEMENT_SPEED, 57.5);
        movementController.moveLeft(MOVEMENT_SPEED, 62.5);

    }
}
