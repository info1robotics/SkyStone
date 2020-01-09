package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "Autonomie Roșu Building Zone")
public class AutonomousRedBuildingZone extends AutonomousBase {
    public void runCommands() throws InterruptedException {
        MOVEMENT_SPEED = 0.5;

    }
}
