package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "Autonomie Roșu Stânga")
public class AutonomousRedLeft extends AutonomousBase {
    public void runCommands() {
        movementController.moveRight(MOVEMENT_SPEED, 92.0);
        movementController.stopAll();
    }
}
