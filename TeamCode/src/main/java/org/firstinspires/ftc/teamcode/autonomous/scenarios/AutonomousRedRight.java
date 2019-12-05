package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "Autonomie Roșu Dreapta")
public class AutonomousRedRight extends AutonomousBase {
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
