package org.firstinspires.ftc.teamcode.arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MotorsConstants;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBasic;

public class ArmMotors {
    private DcMotor extenderMotor, barrierMotor;

    private Servo hookLeft, hookRight;

    private Telemetry console;

    private TeleOpBasic opMode;

    private final double EXTENDER_MOTOR_SPEED = 0.6;

    private final double HOOK_LEFT_POSITION_HOLD = 1;
    private final double HOOK_LEFT_POSITION_IDLE = 0.85;
    private final double HOOK_RIGHT_POSITION_HOLD = 0;
    private final double HOOK_RIGHT_POSITION_IDLE = 0.3;


    public ArmMotors(HardwareMap hardwareMap, Telemetry telemetry,
                     TeleOpBasic opMode) {
        extenderMotor = hardwareMap.get(DcMotor.class,
                "extenderMotor");
        barrierMotor = hardwareMap.get(DcMotor.class, "barrierMotor");
        hookLeft = hardwareMap.get(Servo.class, "hookLeftMotor");
        hookRight = hardwareMap.get(Servo.class, "hookRightMotor");


        this.opMode = opMode;

        hookLeft.setPosition(HOOK_LEFT_POSITION_IDLE);
        hookRight.setPosition(HOOK_RIGHT_POSITION_IDLE);

        console = telemetry;
        console.addData("ArmMotors class", "Set up.");
    }

    public void extendArm(double powerFactor) {
        extenderMotor.setPower(powerFactor * EXTENDER_MOTOR_SPEED);
    }

    public void contractArm(double powerFactor) {
        extenderMotor.setPower(-1.0 * powerFactor * EXTENDER_MOTOR_SPEED);
    }

    public void makeHookIdle() {
        hookLeft.setPosition(HOOK_LEFT_POSITION_IDLE);
        hookRight.setPosition(HOOK_RIGHT_POSITION_IDLE);
    }

    public void makeHookHold() {
        hookLeft.setPosition(HOOK_LEFT_POSITION_HOLD);
        hookRight.setPosition(HOOK_RIGHT_POSITION_HOLD);


    }

    public void raiseBarrier() {
        barrierMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        barrierMotor.setTargetPosition(MotorsConstants.barrierMotor.TICKS);
        barrierMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        barrierMotor.setPower(MotorsConstants.barrierMotor.SPEED);


        while (this.opMode.opModeIsActive() && this.barrierMotor.isBusy()) {
            console.addLine(Integer.toString(barrierMotor.getCurrentPosition()));
            console.update();
            barrierMotor.setPower(MotorsConstants.barrierMotor.SPEED);
            opMode.idle();
        }

        stopAll();
    }


    public void lowerBarrier() {

        barrierMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        barrierMotor.setTargetPosition(-MotorsConstants.barrierMotor.TICKS);
        barrierMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        barrierMotor.setPower(-MotorsConstants.barrierMotor.SPEED);


        while (this.opMode.opModeIsActive() && this.barrierMotor.isBusy()) {
            console.addLine(Integer.toString(barrierMotor.getCurrentPosition()));
            console.update();
            barrierMotor.setPower(-MotorsConstants.barrierMotor.SPEED);
            opMode.idle();
        }

        stopAll();
    }

    public void stopAll() {
        extenderMotor.setPower(0);

        barrierMotor.setPower(0);
        //climbMotor.setPower(0);
        console.addLine("Stopped arm completely");
        console.update();
    }
}
