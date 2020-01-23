package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "AUTONOMIE SUPREMA DE PARCARE", group = "Rosu")
public class AutonomousRedBuildingZone extends AutonomousBase {
    public void runCommands() throws InterruptedException {
        movementController.moveForwardAutonomous(0.5, 30.0);
        movementController.stopAll();
    }
}
