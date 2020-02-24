package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.AppConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name= "ASTA")
public class AutonomousStartTest extends AutonomousBase {
    @Override
    public void runCommands() throws InterruptedException {
        movementController.moveLeftAutonomous(MOVEMENT_SPEED, 265.0);
        movementController.moveBackwardAutonomous(MOVEMENT_SPEED, 80.0);
        movementController.stopAll();
        armController.grabFoundation();
        Thread.sleep(1300);
        movementController.moveForwardAutonomous(MOVEMENT_SPEED, 120.0);
        movementController.stopAll();
        armController.releaseFoundation();
        Thread.sleep(300);
        movementController.moveRightAutonomous(MOVEMENT_SPEED, 165.0);
    }
}
