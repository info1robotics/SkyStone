package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MoveRobotAsym {
    private MovementMotors motorsController;
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
        Power targetPower = new Power(movementPower.multiply(direction));
        Power currentPower = new Power(0.1).multiply(direction);


        while(opMode.opModeIsActive() && (gamepad.dpad_up || gamepad.dpad_down || gamepad.dpad_left || gamepad.dpad_right)) {
            currentPower = currentPower.add(targetPower.subtract(currentPower).multiply(0.1));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            console.addLine("CURRENT");
            console.addLine(currentPower.toString());
            console.update();
            motorsController.setPower(currentPower);
        }
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
