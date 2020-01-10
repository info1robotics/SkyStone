package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "Autonomie Roșu Building Zone", group = "Rosu")
public class AutonomousRedBuildingZone extends AutonomousBase {
    public void runCommands() {
        movementController.moveForwardAutonomous(MOVEMENT_SPEED, 50.0);
        movementController.stopAll();
    }
}
