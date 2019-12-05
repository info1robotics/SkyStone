package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MotorsConstants;

public class MoveRobotAsym {
    public MovementMotors motorsController;
    private Power movementPower;
    private LinearOpMode opMode;
    private Gamepad gamepad;

    private Telemetry console;

    public MoveRobotAsym(HardwareMap hardwareMap, Telemetry telemetry,
                         LinearOpMode opMode, Gamepad gamepad) {
        motorsController = new MovementMotors(hardwareMap, telemetry);
        movementPower = new Power();
        this.opMode = opMode;

        this.gamepad = gamepad;
        console = telemetry;
    }

    private void move(Power direction) {
        motorsController.setPower(direction);
    }

    public void move(Power direction, double centimeters) {
        motorsController.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorsController.setTargetPosition((int)(centimeters * MotorsConstants.ticks.TICKS_PER_CENTIMETER_FORWARD), direction);
        motorsController.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(motorsController.isBusy()) {
            motorsController.setPower(movementPower.multiply(direction));
            console.addLine(movementPower.multiply(direction).toString());
            console.update();
            opMode.idle();
        }

    }

    public void moveExp(double drive, double strafe, double twist) {
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


    }

    public void moveForward(double speed) {
        movementPower.setPower(speed);
        move(Signs.FORWARD);
    }

    public void moveForward(double speed, double centimeters) {
        movementPower.setPower(speed);
        move(Signs.FORWARD, centimeters);
    }

    public void moveBackward(double speed) {
        movementPower.setPower(speed);
        move(Signs.BACKWARD);
    }


    public void moveBackward(double speed, double centimeters) {
        movementPower.setPower(speed);
        move(Signs.BACKWARD, centimeters);
    }

    public void moveLeft(double speed) {
        movementPower.setPower(speed);
        move(Signs.LEFT);
    }


    public void moveLeft(double speed, double centimeters) {
        movementPower.setPower(speed);
        move(Signs.LEFT, centimeters);
    }

    public void moveRight(double speed) {
        movementPower.setPower(speed);
        move(Signs.RIGHT);
    }


    public void moveRight(double speed, double centimeters) {
        movementPower.setPower(speed);
        move(Signs.RIGHT, centimeters);
    }

    public void moveForwardLeft(double speed, double angularSpeed) {
        movementPower.setPower(
                new Power(speed, angularSpeed, speed, angularSpeed));
        opMode.telemetry.addLine("Moving ForwardLeft: " +
                speed);
        opMode.telemetry.update();
        move(Signs.FORWARD_LEFT);
    }

    public void moveForwardRight(double speed, double angularSpeed) {
        movementPower.setPower(
                new Power(speed, angularSpeed, speed, angularSpeed));
        move(Signs.FORWARD_RIGHT);
        opMode.telemetry.addLine("Moving ForwardRight: " +
                speed);
        opMode.telemetry.update();
    }


    public void spin(double power, double direction) {
        if (direction != 1 && direction != -1) return;
        Power resultingPower = (new Power(power))
                .multiply(new Power(direction));
        motorsController.setPower(resultingPower);
    }

    public void stopAll() {
        motorsController.stopAll();
    }

}
