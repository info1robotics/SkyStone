package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED;
import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED_SLOW;

@Autonomous(name = "ACEASTA AUTONOMIE CU ROBOT MERGAND INTAI SPRE STANGA")
public class AutonomousUseThisLeft extends AutonomousBase {
    @Override
    public void runCommands() throws InterruptedException {

        movementController.moveLeftAutonomous(MOVEMENT_SPEED_SLOW, 85.0);
        movementController.moveForwardAutonomous(MOVEMENT_SPEED_SLOW, 75.0);
        movementController.stopAll();
    }
}