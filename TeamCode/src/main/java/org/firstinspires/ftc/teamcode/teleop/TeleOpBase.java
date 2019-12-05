package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.arm.MoveArms;
import org.firstinspires.ftc.teamcode.movement.MoveRobot;

/**
 * Class which listens for controller input and calls the correct
 * method in the Utilities class to perform the action.
 */

public abstract class TeleOpBase extends LinearOpMode {

    MoveRobot movementController;
    MoveArms armController;
    //MoveArms armController;

    @Override
    public void runOpMode() throws InterruptedException {

        movementController = new MoveRobot(hardwareMap, telemetry,
                this);
        armController = new MoveArms(hardwareMap, telemetry, this);

        telemetry.update();


        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("status",
                    "waiting for start command");
            telemetry.update();
            sleep(200);
        }


        while (opModeIsActive()) {

            checkInputs();

        }

    }

    abstract void checkInputs();


}
