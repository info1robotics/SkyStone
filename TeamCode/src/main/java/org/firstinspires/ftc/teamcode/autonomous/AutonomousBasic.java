package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.arm.ArmMotors;
import org.firstinspires.ftc.teamcode.movement.MoveRobotAsym;
import org.firstinspires.ftc.teamcode.vision.WebcamVision;

public abstract class AutonomousBasic extends LinearOpMode {
    MoveRobotAsym movementController;
    ArmMotors armController;
    WebcamVision visionController;


    FtcDashboard dashboard;
    Telemetry dashboardTelemetry;

    protected static double MOVEMENT_SPEED = 1.0;

    public void runOpMode() {

        msStuckDetectStop = 2500;

        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        movementController = new MoveRobotAsym(hardwareMap, telemetry,
                this, gamepad1);
        armController = new ArmMotors(hardwareMap, telemetry, this);
        visionController = new WebcamVision(hardwareMap, dashboardTelemetry);

        FtcDashboard.getInstance().startCameraStream(visionController.tfod, 0);


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
