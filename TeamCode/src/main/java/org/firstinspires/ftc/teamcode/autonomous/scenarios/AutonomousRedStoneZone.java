package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "Autonomie Roșu Stone Zone")
public class AutonomousRedStoneZone extends AutonomousBase {
    public void runCommands() {
        movementController.moveRightAutonomous(MOVEMENT_SPEED, 92.0);
        movementController.stopAll();
    }
}
