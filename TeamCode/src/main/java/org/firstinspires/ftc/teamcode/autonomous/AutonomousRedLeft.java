package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Autonomie Roșu Stânga")
public class AutonomousRedLeft extends AutonomousBasic {
    public void runCommands() {
        movementController.moveRight(MOVEMENT_SPEED, 92.0);
        movementController.stopAll();
    }
}
