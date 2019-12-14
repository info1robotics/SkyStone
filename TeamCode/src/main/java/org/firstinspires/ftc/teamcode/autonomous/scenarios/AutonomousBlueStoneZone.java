package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "Autonomie Albastru Stone Zone", group = "Simplu")
public class AutonomousBlueStoneZone extends AutonomousBase {
    public void runCommands() {
        movementController.moveLeftAutonomous(MOVEMENT_SPEED, 92.0);
        movementController.stopAll();
    }
}
