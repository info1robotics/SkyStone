package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.arm.MoveArms;
import org.firstinspires.ftc.teamcode.movement.MoveRobot;
import org.firstinspires.ftc.teamcode.vision.WebcamVision;

public abstract class AutonomousBase extends LinearOpMode {
    public MoveRobot movementController;
    public MoveArms armController;
    public WebcamVision visionController;


    FtcDashboard dashboard;
    Telemetry dashboardTelemetry;

    public void runOpMode() {

        msStuckDetectStop = 2500;

        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        movementController = new MoveRobot(hardwareMap, telemetry,
                this);
        armController = new MoveArms(hardwareMap, telemetry, this);
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
