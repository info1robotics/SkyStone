package org.firstinspires.ftc.teamcode.arm;

import android.os.SystemClock;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBasic;

public class ArmMotors {
    private DcMotor baseMotorLeft, baseMotorRight;
    private DcMotor extenderMotor;
    private DcMotor hookMotorRotator;
    public DcMotor climbMotor;

    private Servo hookLeft, hookRight;

    private Telemetry console;

    private TeleOpBasic opMode;

    private final double BASE_MOTORS_SPEED = 0.7;
    private final double EXTENDER_MOTOR_SPEED = 0.6;
    private final double HOOK_MOTOR_ROTATOR_SPEED = 0.6;

    private final double HOOK_LEFT_POSITION_HOLD = 0.85;
    private final double HOOK_LEFT_POSITION_MIDDLE = 0.9;
    private final double HOOK_LEFT_POSITION_IDLE = 1.0;
    private final double HOOK_RIGHT_POSITION_HOLD = 0.75;
    private final double HOOK_RIGHT_POSITION_MIDDLE = 0.7;
    private final double HOOK_RIGHT_POSITION_IDLE = 0.60;
    private final double CLIMB_MOTOR_SPEED = 1 /* TO BE DETERMINED */;

    public ArmMotors(HardwareMap hardwareMap, Telemetry telemetry, TeleOpBasic opMode) {
        /*baseMotorLeft = hardwareMap.get(DcMotor.class,
                "baseMotorLeft");
        baseMotorRight = hardwareMap.get(DcMotor.class,
                "baseMotorRight");
        extenderMotor = hardwareMap.get(DcMotor.class,
                "extenderMotor");
        hookMotorRotator = hardwareMap.get(DcMotor.class,
                "hookMotorRotator");*/
        climbMotor = hardwareMap.get(DcMotor.class, "climbMotor");



        this.opMode = opMode;

        /*hookLeft = hardwareMap.get(Servo.class, "hookLeft");
        hookRight = hardwareMap.get(Servo.class, "hookRight");
        hookLeft.setPosition(HOOK_LEFT_POSITION_IDLE);
        hookRight.setPosition(HOOK_RIGHT_POSITION_IDLE);

        hookMotorRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        baseMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        baseMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/
        console = telemetry;
        console.addData("MoveArms class", "Set up.");
    }

    public void raiseArm(double powerFactor) {
        baseMotorLeft.setPower(powerFactor * BASE_MOTORS_SPEED);
        baseMotorRight.setPower(-1.0 * powerFactor * BASE_MOTORS_SPEED);

        console.addLine("Raising arm");
        console.update();
    }

    public void lowerArm(double powerFactor) {
        baseMotorLeft.setPower(-1.0 * powerFactor * BASE_MOTORS_SPEED);
        baseMotorRight.setPower(powerFactor * BASE_MOTORS_SPEED);

        console.addLine("Lowering arm");
        console.update();
    }

    public void extendArm(double powerFactor) {
        extenderMotor.setPower(powerFactor * EXTENDER_MOTOR_SPEED);
    }

    public void contractArm(double powerFactor) {
        extenderMotor.setPower(-1.0 * powerFactor * EXTENDER_MOTOR_SPEED);
    }

    public void raiseHookRotator(double powerFactor) {
        hookMotorRotator.setPower(powerFactor * HOOK_MOTOR_ROTATOR_SPEED);
    }

    public void lowerHookRotator(double powerFactor) {
        hookMotorRotator.setPower(-1.0 * powerFactor * HOOK_MOTOR_ROTATOR_SPEED);
    }

    public void makeHookIdle() {
        hookLeft.setPosition(HOOK_LEFT_POSITION_IDLE);
        hookRight.setPosition(HOOK_RIGHT_POSITION_IDLE);
    }

    public void makeHookHold() {
        hookLeft.setPosition(HOOK_LEFT_POSITION_HOLD);
        hookRight.setPosition(HOOK_RIGHT_POSITION_HOLD);


    }

    public void climbOnLadder(double powerFactor) {
        console.addLine("Setting climbMotor power: " + powerFactor
                * CLIMB_MOTOR_SPEED);
        console.update();
        climbMotor.setPower(powerFactor * CLIMB_MOTOR_SPEED);
    }

    public void descendFromLander(double powerFactor) {
        console.addLine("Setting climbMotor power: " + -1.0 *
                powerFactor * CLIMB_MOTOR_SPEED);
        console.update();
        climbMotor.setPower(-1.0 * powerFactor * CLIMB_MOTOR_SPEED);
    }

    public void stopAll() {
        /*baseMotorLeft.setPower(0);
        baseMotorRight.setPower(0);
        extenderMotor.setPower(0);
        hookMotorRotator.setPower(0);*/
        climbMotor.setPower(0);
        console.addLine("Stopped arm completely");
        console.update();
    }
}
