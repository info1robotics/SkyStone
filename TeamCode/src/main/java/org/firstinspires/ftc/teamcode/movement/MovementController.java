package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.constants.MotorsConstants;

public class MovementController {
    public MovementMotors motorsController;
    private LinearOpMode opMode;

    public BNO055IMU imu;

    private Telemetry console;

    public MovementController(HardwareMap hardwareMap, Telemetry telemetry,
                              LinearOpMode opMode) {
        motorsController = new MovementMotors(hardwareMap, telemetry);
        imu = hardwareMap.get(BNO055IMU.class,"imu");

        this.opMode = opMode;

        console = telemetry;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;
        imu.initialize(parameters);
    }


    private void moveAutonomous(Power speed, Power direction, double centimeters) {
        motorsController.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorsController.setTargetPosition((int)(centimeters * MotorsConstants.ticks.TICKS_PER_CENTIMETER), direction);
        motorsController.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorsController.setPower(speed.multiply(direction));

        while(motorsController.isBusy() && opMode.opModeIsActive()) {
            opMode.idle();
        }

    }

    public Power normalizePower(Power power) {
        double fl = power.fl, fr = power.fr, bl = power.bl, br = power.br;
        double max = Math.max(
                Math.max(Math.abs(fl), Math.abs(fr)),
                Math.max(Math.abs(bl), Math.abs(br))
                );
        max = Math.max(max, 1.0);
        fr = fr / max;
        bl = bl / max;
        br = br / max;
        fl = fl / max;
        return new Power(fl, fr, bl, br);
    }

    public void moveTeleOp(double drive, double strafe, double twist) {
        Power movementPower = new Power(
                (drive + strafe + twist),
                (strafe - drive - twist),
                (strafe - drive + twist),
                (drive + strafe - twist)
        );

        movementPower = normalizePower(movementPower);
        motorsController.setPower(movementPower);

    }

    public void moveForwardAutonomous(double speed, double centimeters) {
        moveAutonomous(new Power(speed), Signs.FORWARD, centimeters);
    }

    public void moveBackwardAutonomous(double speed, double centimeters) {
        moveAutonomous(new Power(speed), Signs.BACKWARD, centimeters);
    }

    public void moveLeftAutonomous(double speed, double centimeters) {
        moveAutonomous(new Power(speed), Signs.LEFT, centimeters);
    }

    public void moveRightAutonomous(double speed, double centimeters) {
        moveAutonomous(new Power(speed), Signs.RIGHT, centimeters);
    }

    public void stopAll() {
        motorsController.stopAll();
    }

    Orientation getGyroAngle() {
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }

    private double getAngleDelta(Orientation angle) {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double deltaAngle = angles.firstAngle - angle.firstAngle;
        while (deltaAngle < -180) deltaAngle += 360;
        while (deltaAngle > 180) deltaAngle -= 360;
        return deltaAngle;
    }

    public void spinAutonomous(double speed, double angle) {
        Power signs;

        if (angle < 0) signs = Signs.SPIN_LEFT;
        else signs = Signs.SPIN_RIGHT;
        this.motorsController.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Orientation refAngle = new Orientation();
        double angleDiff = getAngleDelta(refAngle);

        while (Math.abs(angleDiff) < Math.abs(angle) && opMode.opModeIsActive()) {
            this.motorsController.setPower(signs.multiply(new Power(speed)));
            console.addLine(Double.toString(Math.abs(angleDiff)));
            console.addLine(Double.toString(Math.abs(angle)));
            console.update();
            angleDiff = getAngleDelta(refAngle);
        }

        this.stopAll();
    }
}