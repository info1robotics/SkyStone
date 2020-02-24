package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.arm.ArmsController;
import org.firstinspires.ftc.teamcode.arm.IntakeController;
import org.firstinspires.ftc.teamcode.movement.MovementController;

/**
 * Abstract class which is our base TeleOp mode.
 * Initialises all <i>controller</i> classes for controlling our robot
 */

public abstract class TeleOpBase extends LinearOpMode {

    public MovementController movementController;
    public ArmsController armsController;
    public IntakeController intakeController;
<<<<<<< HEAD
    public FtcDashboard dashboard;
//    public WebcamVision webcamVision;

=======
    protected FtcDashboard dashboard;
>>>>>>> parent of 44f7c19... Added some odometry basics; added aoutonomous ticks moving; camera calibration preset added; removed nonfuctional no multi-threading  code
    //ArmsController armsController;

    @Override
    public void runOpMode() {

        movementController = new MovementController(hardwareMap, telemetry,
                this);
        armsController = new ArmsController(hardwareMap, telemetry, this);
        intakeController = new IntakeController(hardwareMap, telemetry, this);
//        webcamVision = new WebcamVision(hardwareMap, telemetry);
        dashboard = FtcDashboard.getInstance();



        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("status",
                    "waiting for start command");
            telemetry.update();
            sleep(200);
        }


        initActions();

        while(opModeIsActive()) {
            runLoop();
        }

    }

<<<<<<< HEAD
    public abstract void initActions();
=======
    abstract void run();
>>>>>>> parent of 44f7c19... Added some odometry basics; added aoutonomous ticks moving; camera calibration preset added; removed nonfuctional no multi-threading  code

    public abstract void runLoop();

}
