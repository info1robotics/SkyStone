package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.arm.ArmMotors;
import org.firstinspires.ftc.teamcode.movement.MoveRobotAsym;

public abstract class AutonomousBasic extends LinearOpMode {
    MoveRobotAsym movementController;
    ArmMotors armController;

    protected static double MOVEMENT_SPEED = 1.0;

    public void runOpMode() {

        movementController = new MoveRobotAsym(hardwareMap, telemetry,
                this, gamepad1);
        armController = new ArmMotors(hardwareMap, telemetry, this);

        telemetry.update();


        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("status",
                    "waiting for start command");
            telemetry.update();
            sleep(200);
        }

        try {
            runCommands();
        } catch(InterruptedException e) { }


    }

    public abstract void runCommands() throws InterruptedException;
}
