package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.arm.ArmMotors;
import org.firstinspires.ftc.teamcode.movement.MoveRobotLinear;

/**
 * Class which listens for controller input and calls the correct
 * method in the Utilities class to perform the action.
 */

@TeleOp(name = "Basic Manual Control")
public class TeleOpBasic extends LinearOpMode {

    MoveRobotLinear movementController;
    ArmMotors armController;

    //ArmMotors armController;
    private final double MOVEMENT_SPEED = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {

        movementController = new MoveRobotLinear(hardwareMap, telemetry,
                this);
        armController = new ArmMotors(hardwareMap, telemetry, this);

        //armController = new ArmMotors(hardwareMap, telemetry, this);

        telemetry.update();


        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("status",
                    "waiting for start command");
            telemetry.update();
            sleep(200);
        }


        while (opModeIsActive()) {

            // Robot Movement
            if (gamepad1.dpad_down)
                Utilities.moveBackward(movementController,
                        gamepad1, MOVEMENT_SPEED, this);
            if (gamepad1.dpad_up) Utilities.moveForward(movementController,
                    gamepad1, MOVEMENT_SPEED, this);

            if (gamepad1.dpad_right)
                Utilities.moveRight(movementController,
                        gamepad1, MOVEMENT_SPEED, this);
            if (gamepad1.dpad_left) Utilities.moveLeft(movementController,
                    gamepad1, MOVEMENT_SPEED, this);

            if (gamepad1.left_trigger > 0)
                Utilities.spinLeft(movementController, gamepad1, this);
            if (gamepad1.right_trigger > 0)
                Utilities.spinRight(movementController, gamepad1, this);


            // Arm Movement


            if(gamepad1.left_bumper) {
                Utilities.contractArm(armController, gamepad1, this);
            }

            if(gamepad1.right_bumper) {
                Utilities.extendArm(armController, gamepad1, this);
            }

            if (gamepad1.a) {
                armController.makeHookIdle();
            }

            if (gamepad1.b) {
                armController.makeHookHold();
            }

            if(gamepad1.x) {
                armController.lowerBarrier();
            }

            if(gamepad1.y) {
                armController.raiseBarrier();
            }


            idle();
            telemetry.addLine("Connection active");
            telemetry.update();
        }

    }

}
