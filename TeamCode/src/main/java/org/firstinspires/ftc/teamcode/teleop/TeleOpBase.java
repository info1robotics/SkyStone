package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.arm.ArmsController;
import org.firstinspires.ftc.teamcode.arm.IntakeController;
import org.firstinspires.ftc.teamcode.movement.MovementController;

/**
 * Class which listens for controller input and calls the correct
 * method in the Utilities class to perform the action.
 */

public abstract class TeleOpBase extends LinearOpMode {

    public MovementController movementController;
    public ArmsController armsController;
    public IntakeController intakeController;
    public FtcDashboard dashboard;
    //ArmsController armsController;

    @Override
    public void runOpMode() {

        movementController = new MovementController(hardwareMap, telemetry,
                this);
        armsController = new ArmsController(hardwareMap, telemetry, this);
        intakeController = new IntakeController(hardwareMap, telemetry, this);
        dashboard = FtcDashboard.getInstance();



        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("status",
                    "waiting for start command");
            telemetry.update();
            sleep(200);
        }


        run();

        while(opModeIsActive());

    }

    public abstract void run();


}
