package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Autonomie Albastru Dreapta")
public class AutonomousBlueLeft extends AutonomousBasic {
    public void runCommands() {
        movementController.moveLeft(MOVEMENT_SPEED, 92.0);
        movementController.stopAll();
    }
}
