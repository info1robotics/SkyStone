package org.firstinspires.ftc.teamcode.arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.constants.MotorsConstants;

public class ArmsController {
    private Servo barrierLeft, barrierRight;

    private Telemetry telemetry;

    private LinearOpMode opMode;


    public ArmsController(HardwareMap hardwareMap, Telemetry telemetry, LinearOpMode opMode) {
        this.opMode = opMode;
        this.telemetry = telemetry;

        barrierLeft = hardwareMap.get(Servo.class, "motorBarrierLeft");
        barrierRight = hardwareMap.get(Servo.class, "motorBarrierRight");
        barrierLeft.setPosition(MotorsConstants.barrierMotors.LEFT_IDLE);
        barrierRight.setPosition(MotorsConstants.barrierMotors.RIGHT_IDLE);


    }

    public void openBarrier() {
        barrierLeft.setPosition(MotorsConstants.barrierMotors.LEFT_IDLE);
        barrierRight.setPosition(MotorsConstants.barrierMotors.RIGHT_IDLE);
    }


    public void closeBarrier() {
        barrierLeft.setPosition(MotorsConstants.barrierMotors.LEFT_HOLD);
        barrierRight.setPosition(MotorsConstants.barrierMotors.RIGHT_HOLD);

        telemetry.addLine("CLOSE SERVO");
        telemetry.update();
    }

    public void stopAll() {
        telemetry.addLine("Stopped arm completely");
        telemetry.update();
    }
}
