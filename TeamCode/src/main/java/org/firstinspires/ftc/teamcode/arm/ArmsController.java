package org.firstinspires.ftc.teamcode.arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.constants.MotorsConstants;

public class ArmsController {

    private Telemetry console;

    private LinearOpMode opMode;

    private DcMotor motorArm1, motorArm2;
    CRServo servoMacara;
    Servo servoCarlig;

    boolean isCarligHolding = false;

    public ArmsController(HardwareMap hardwareMap, Telemetry console, LinearOpMode opMode) {
        this.opMode = opMode;
        this.console = console;

        motorArm1 = hardwareMap.get(DcMotor.class, "motorArm1");
        motorArm2 = hardwareMap.get(DcMotor.class, "motorArm2");
        servoMacara = hardwareMap.get(CRServo.class, "servoMacara");
        servoCarlig = hardwareMap.get(Servo.class, "servoCarlig");


        motorArm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorArm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoMacara.setDirection(CRServo.Direction.REVERSE);
        servoCarlig.setPosition(MotorsConstants.servoMotors.CARLIG_IDLE);


    }

    public void arm1Up(double speed) {
        motorArm1.setPower(speed);
        motorArm2.setPower(-1.0 * speed);
    }

    public void arm1Down(double speed) {
        motorArm1.setPower(-1.0 * speed);
        motorArm2.setPower(speed);
    }

    public void servoMacaraForward() {
        servoMacara.setDirection(CRServo.Direction.FORWARD);
        servoMacara.setPower(-1.0);
    }

    public void servoMacaraBackward() {
        servoMacara.setDirection(CRServo.Direction.REVERSE);
        servoMacara.setPower(-1.0);
    }

    public void servoCarligToggle() {
        if(isCarligHolding) {
            servoCarlig.setPosition(MotorsConstants.servoMotors.CARLIG_IDLE);
        } else {
            servoCarlig.setPosition(MotorsConstants.servoMotors.CARLIG_HOLD);
        }
        isCarligHolding = !isCarligHolding;
    }

    public void stopServo() {
        servoMacara.setPower(0);
    }

    public void stopDc() {
        motorArm1.setPower(0);
        motorArm2.setPower(0);
    }

}
