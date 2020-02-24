package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.AppConstants.intakeMotors.INTAKE_SPEED;
import static org.firstinspires.ftc.teamcode.constants.AppConstants.robotMovement.MOVEMENT_SPEED_SLOW;

@Autonomous(name = "Autonomie Albastru Stone Zone", group = "Albastru")

public class AutonomousBlueStoneZone extends AutonomousBase {
    public void runCommands() {
        intakeController.runIn(INTAKE_SPEED);
        movementController.moveForwardAutonomous(MOVEMENT_SPEED_SLOW, 120.0);
        intakeController.stopAll();
        sleep(300);
        movementController.moveBackwardAutonomous(MOVEMENT_SPEED_SLOW, 55.0);
        sleep(300);
        movementController.moveLeftAutonomous(MOVEMENT_SPEED_SLOW, 150.0);
        sleep(300);
        intakeController.runOut(INTAKE_SPEED);
        movementController.moveRightAutonomous(MOVEMENT_SPEED_SLOW, 200.0);
        sleep(300);
        intakeController.runIn(INTAKE_SPEED);
        movementController.moveForwardAutonomous(MOVEMENT_SPEED_SLOW, 100.0);
        intakeController.stopAll();
        sleep(300);
        movementController.moveBackwardAutonomous(MOVEMENT_SPEED_SLOW, 85.0);
        sleep(300);
        movementController.moveLeftAutonomous(MOVEMENT_SPEED_SLOW, 250.0);
        sleep(300);
        movementController.moveRightAutonomous(MOVEMENT_SPEED_SLOW, 90.0);
        movementController.stopAll();
        sleep(300);
    }
}
