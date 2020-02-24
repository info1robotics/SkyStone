package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.arm.ArmsController;
import org.firstinspires.ftc.teamcode.arm.IntakeController;
import org.firstinspires.ftc.teamcode.movement.MovementController;

public abstract class AutonomousBase extends LinearOpMode {
    public MovementController movementController;
    public ArmsController armController;
    public IntakeController intakeController;
    //public WebcamVision visionController;


    private FtcDashboard dashboard;
    protected Telemetry dashboardTelemetry;

    private BNO055IMU imu;

    public void runOpMode() {

        msStuckDetectStop = 2500;

        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        movementController = new MovementController(hardwareMap, telemetry,
                this);
        armController = new ArmsController(hardwareMap, telemetry, this);
        intakeController = new IntakeController(hardwareMap, telemetry, this);
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters=new BNO055IMU.Parameters();
        parameters.mode=BNO055IMU.SensorMode.IMU;
        parameters.angleUnit=BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit=BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled=false;
        imu.initialize(parameters);
        //visionController = new WebcamVision(hardwareMap, dashboardTelemetry);

        //FtcDashboard.getInstance().startCameraStream(visionController.tfod, 0);




        while (!isStarted() && !isStopRequested()) {
//            telemetry.addData("status",
//                    "waiting for start command");
//            telemetry.update();
            sleep(200);
        }

        try {
            runCommands();
        } catch(InterruptedException e) { }

    }

    public abstract void runCommands() throws InterruptedException;
}
