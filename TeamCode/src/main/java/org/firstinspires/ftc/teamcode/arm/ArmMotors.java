package org.firstinspires.ftc.teamcode.arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MotorsConstants;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBasic;

public class ArmMotors {
    private Servo barrierLeft, barrierRight;

    private Telemetry console;

    private LinearOpMode opMode;


    public ArmMotors(HardwareMap hardwareMap, Telemetry telemetry, LinearOpMode opMode) {
        this.opMode = opMode;

        barrierLeft = hardwareMap.get(Servo.class, "motorBarrierLeft");
        barrierRight = hardwareMap.get(Servo.class, "motorBarrierRight");
        barrierLeft.setPosition(MotorsConstants.barrierMotors.LEFT_IDLE);
        barrierRight.setPosition(MotorsConstants.barrierMotors.RIGHT_IDLE);

        console = telemetry;
        console.addData("ArmMotors class", "Set up.");
    }

    public void openBarrier() {
        barrierLeft.setPosition(MotorsConstants.barrierMotors.LEFT_IDLE);
        barrierRight.setPosition(MotorsConstants.barrierMotors.RIGHT_IDLE);
    }


    public void closeBarrier() {
        barrierLeft.setPosition(MotorsConstants.barrierMotors.LEFT_HOLD);
        barrierRight.setPosition(MotorsConstants.barrierMotors.RIGHT_HOLD);

        console.addLine("CLOSE SERVO");
        console.update();
    }

    public void stopAll() {
        console.addLine("Stopped arm completely");
        console.update();
    }
}
