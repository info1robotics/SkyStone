package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "Autonomie Albastru Dreapta", group = "Simplu")
public class AutonomousBlueLeft extends AutonomousBase {
    public void runCommands() {
        movementController.moveLeft(MOVEMENT_SPEED, 92.0);
        movementController.stopAll();
    }
}
