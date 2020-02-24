package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.constants.AppConstants;
import org.firstinspires.ftc.teamcode.constants.Signs;
import org.firstinspires.ftc.teamcode.odometry.OdometryMovement;

/**
 * This is a <i>controller</i> class which is a one way solution to control
 * the movement of the robot, both in autonomous and TeleOp phases.
 * Supports:
 *     -> Moving the robot in any directions based on x, y, and turn components
 *     ->
 */


public class MovementController {
    public MovementMotors movementMotors;
    public OdometryMovement odometryMovement;
    private LinearOpMode opMode;


    public BNO055IMU imu;

    public Telemetry console;

    public MovementController(HardwareMap hardwareMap, Telemetry telemetry,
                              LinearOpMode opMode) {
        movementMotors = new MovementMotors(hardwareMap, telemetry);
        imu = hardwareMap.get(BNO055IMU.class,"imu");

        this.opMode = opMode;

        console = telemetry;
//
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.mode = BNO055IMU.SensorMode.IMU;
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.loggingEnabled = false;
//        imu.initialize(parameters);

        odometryMovement = new OdometryMovement(this);

    }


    /**
     * Asymptotic moveAutonomous
     * @param targetPower Max power to be reached by the wheels' motors
     * @param direction Direction to go in
     * @param centimeters Amount of centimeters to go in
     */

    private void moveAutonomous(Power targetPower, Power direction, double centimeters) {
        movementMotors.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        movementMotors.setTargetPosition((int)(centimeters * AppConstants.ticks.TICKS_PER_CENTIMETER), direction);
        movementMotors.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        movementMotors.setPower(targetPower.multiply(direction));

        AsymManager asymManager = new AsymManager(
                new Power(0.2),
                targetPower,
                opMode
        );
        while(movementMotors.isBusy() && opMode.opModeIsActive()) {
            movementMotors.setPower(asymManager.activePower.multiply(direction));
            opMode.idle();
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private void moveAutonomous(Power speed, Power direction, int ticks) {
        movementMotors.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        movementMotors.setTargetPosition(ticks, direction);
        movementMotors.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        movementMotors.setPower(speed.multiply(direction));

        while(movementMotors.isBusy() && opMode.opModeIsActive()) {
            opMode.idle();
        }


    }


    /**
     * Makes sure the power's members don't go above the maximum power (1.0)
     * @param power Power to be normalized.
     * @return Normalized power.
     */
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

    /**
     * @param drive X component (drive forward, drive backward) [-1.0; 1.0]
     * @param strafe Y component (strafe left, strafe right); [-1.0; 1.0]
     * @param twist turn component (turn to left, turn to right); [-1.0; 1.0]
     */
    public void moveTeleOp(double drive, double strafe, double twist) {
        Power movementPower = new Power(
                (drive - strafe + twist),
                (-strafe - drive - twist),
                (-strafe - drive + twist),
                (drive - strafe - twist)
        );

        console.addData("drive", drive);
        console.addData("strafe", strafe);
        console.addData("twist", twist);
        console.update();
        movementPower = normalizePower(movementPower);
        movementMotors.setPower(movementPower);

    }

    /**
     * @param speed [-1.0; 1.0]
     * @param centimeters Amount of centimeters to go forward
     */
    public void moveForwardAutonomous(double speed, double centimeters) {
        moveAutonomous(new Power(speed), Signs.FORWARD, centimeters);
    }


    /**
     * @param speed [-1.0; 1.0]
     * @param centimeters Amount of centimeters to go backward
     */

    public void moveBackwardAutonomous(double speed, double centimeters) {
        moveAutonomous(new Power(speed), Signs.BACKWARD, centimeters);
    }


    /**
     * @param speed [-1.0; 1.0]
     * @param centimeters Amount of centimeters to go left
     */
    public void moveLeftAutonomous(double speed, double centimeters) {
        moveAutonomous(new Power(speed), Signs.LEFT, centimeters);
    }


    /**
     * @param speed [-1.0; 1.0]
     * @param centimeters Amount of centimeters to go right
     */

    public void moveRightAutonomous(double speed, double centimeters) {
        moveAutonomous(new Power(speed), Signs.RIGHT, centimeters);
    }

    @Deprecated
    public void move100Ticks() {
        moveAutonomous(new Power(1.0), Signs.FORWARD, 1000);
    }


    /**
     * Stops the wheels' motors.
     */
    public void stopAll() {
        movementMotors.stopAll();
    }

}
