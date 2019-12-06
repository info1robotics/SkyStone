package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MotorsConstants;

public class MovementController {
    public MovementMotors motorsController;
    private LinearOpMode opMode;

    private Telemetry console;

    private boolean isMoving = false;

    public MovementController(HardwareMap hardwareMap, Telemetry telemetry,
                              LinearOpMode opMode) {
        motorsController = new MovementMotors(hardwareMap, telemetry);
        this.opMode = opMode;

        console = telemetry;
    }


    public void move(Power direction, Power speed, double centimeters) {
        motorsController.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorsController.setTargetPosition((int)(centimeters * MotorsConstants.ticks.TICKS_PER_CENTIMETER), direction);
        motorsController.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(motorsController.isBusy()) {
            motorsController.setPower(speed.multiply(direction));
            console.addLine(speed.multiply(direction).toString());
            console.update();
            opMode.idle();
        }

        isMoving = true;

    }

    public void move(double drive, double strafe, double twist) {
        double[] powers = {
                (drive + strafe + twist),
                (strafe - drive - twist),
                (strafe - drive + twist),
                (drive + strafe - twist)
        };

        double max = Math.abs(powers[0]);
        for(int i = 1; i < powers.length; i++)
            if(max < Math.abs(powers[i])) {
                max = Math.abs(powers[i]);
            }

        if(max > 1) {
            for(int i = 0; i < powers.length; i++)
                powers[i] /= max;
        }
        motorsController.setPower(new Power(
                powers[0],
                powers[1],
                powers[2],
                powers[3]
        ));

        isMoving = true;


    }

    public void moveForward(double speed, double centimeters) {
        move(Signs.FORWARD, new Power(speed), centimeters);
    }


    public void moveBackward(double speed, double centimeters) {
        move(Signs.BACKWARD, new Power(speed), centimeters);
    }

    public void moveLeft(double speed, double centimeters) {
        move(Signs.LEFT, new Power(speed), centimeters);
    }

    public void moveRight(double speed, double centimeters) {
        move(Signs.RIGHT, new Power(speed), centimeters);
    }

    public void stopAll() {
        motorsController.stopAll();
        isMoving = false;
    }

    public boolean isMoving() { return isMoving; }

}
