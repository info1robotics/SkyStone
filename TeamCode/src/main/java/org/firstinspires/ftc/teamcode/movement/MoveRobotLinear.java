package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MoveRobotLinear {
    private MovementMotors motorsController;
    private Power movementPower;
    private LinearOpMode opMode;

    public MoveRobotLinear(HardwareMap hardwareMap, Telemetry telemetry,
                           LinearOpMode opMode) {
        motorsController = new MovementMotors(hardwareMap, telemetry);
        movementPower = new Power();
        this.opMode = opMode;
    }

    private void move(Power direction) {
        motorsController.setPower(movementPower.multiply(direction));
    }

    public void moveForward(double speed) {
        movementPower.setPower(speed);
        move(Signs.FORWARD);
    }

    public void moveBackward(double speed) {
        movementPower.setPower(speed);
        move(Signs.BACKWARD);
    }

    public void moveLeft(double speed) {
        movementPower.setPower(speed);
        move(Signs.LEFT);
    }

    public void moveRight(double speed) {
        movementPower.setPower(speed);
        move(Signs.RIGHT);
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
        motorsController.setPower(new Power());
    }

}
