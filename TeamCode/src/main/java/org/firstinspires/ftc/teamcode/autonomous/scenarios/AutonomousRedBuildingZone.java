package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "Autonomie Roșu Building Zone")
public class AutonomousRedBuildingZone extends AutonomousBase {
    public void runCommands() throws InterruptedException {
        MOVEMENT_SPEED = 0.5;

        movementController.moveRightAutonomous(MOVEMENT_SPEED, 52.0);
        movementController.moveForwardAutonomous(MOVEMENT_SPEED, 85.0);
        armController.closeBarrier();
        Thread.sleep(1000);
        movementController.moveBackwardAutonomous(MOVEMENT_SPEED, 89.0);
        armController.openBarrier();
        Thread.sleep(500);
        movementController.moveLeftAutonomous(MOVEMENT_SPEED, 83.0);
        movementController.moveForwardAutonomous(MOVEMENT_SPEED, 61.5);
        movementController.moveLeftAutonomous(MOVEMENT_SPEED, 62.5);

    }
}