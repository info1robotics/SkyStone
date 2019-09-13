package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.MotorsConstants;
import org.firstinspires.ftc.teamcode.arm.ArmMotors;
import org.firstinspires.ftc.teamcode.movement.MoveRobotLinear;


/**
 *      Class defining all required actions for a controlled stage.
 *
 *      Supports
 *          * Moving the robot on all 4 axes.
 *          * Moving the mineral arm.
 *          * Extending/ Contracting the robot arm.
 *          * Spinning the robot.
 *          * Changing the direction of the mineral brush.
 *          * Tilting the mineral box at 90/ 45 degrees from the mineral arm.
 *          * Turning the correction on/ off.
 */

public class Utilities {

    public static void moveForward(MoveRobotLinear movementController, Gamepad gamepad, double speed, LinearOpMode opMode) {
        while(opMode.opModeIsActive() && gamepad.dpad_up) {
            double left_trigger = gamepad.left_trigger;
            double right_trigger = gamepad.right_trigger;

            if(left_trigger > 0)
                movementController.moveForwardLeft(speed, left_trigger);
            else if(right_trigger > 0)
                movementController.moveForwardRight(speed, right_trigger);
            else movementController.moveForward(speed);

            opMode.idle();
            opMode.telemetry.addLine("Moving Forward");
            opMode.telemetry.update();
        }
        opMode.telemetry.addLine("Stopped");
        opMode.telemetry.update();
        movementController.stopAll();
    }

    public static void moveBackward(MoveRobotLinear movementHandler, Gamepad gamepad, double speed, LinearOpMode opMode) {
        while(opMode.opModeIsActive() && gamepad.dpad_down) {
            movementHandler.moveBackward(speed);
            opMode.idle();
            opMode.telemetry.addLine("Moving Backward");
            opMode.telemetry.update();

        }
        opMode.telemetry.addLine("Stopped");
        opMode.telemetry.update();
        movementHandler.stopAll();
    }

    public static void moveRight(MoveRobotLinear movementHandler, Gamepad gamepad, double speed, LinearOpMode opMode) {
        while(opMode.opModeIsActive() && gamepad.dpad_right) {
            movementHandler.moveRight(speed);
            opMode.idle();
            opMode.telemetry.addLine("Moving Right");
            opMode.telemetry.update();
        }

        opMode.telemetry.addLine("Stopped");
        opMode.telemetry.update();
        movementHandler.stopAll();
    }

    public static void moveLeft(MoveRobotLinear movementHandler, Gamepad gamepad, double speed, LinearOpMode opMode) {
        while(opMode.opModeIsActive() && gamepad.dpad_left) {
            movementHandler.moveLeft(speed);
            opMode.idle();
            opMode.telemetry.addLine("Moving Left");
            opMode.telemetry.update();
        }

        opMode.telemetry.addLine("Stopped");
        opMode.telemetry.update();
        movementHandler.stopAll();
    }


    public static void spinLeft(MoveRobotLinear movementHandler, Gamepad gamepad, LinearOpMode opMode) {
        while(opMode.opModeIsActive() && gamepad.left_trigger != 0) {
            double speed = gamepad.left_trigger;
            movementHandler.spin(speed, 1);
            opMode.idle();
        }
        movementHandler.stopAll();
    }

    public static void spinRight(MoveRobotLinear movementHandler,
                                 Gamepad gamepad, LinearOpMode opMode) {
        while (opMode.opModeIsActive() && gamepad.right_trigger != 0) {
            double speed = gamepad.right_trigger;
            movementHandler.spin(speed, -1);
            opMode.idle();
        }
        movementHandler.stopAll();
    }

    public static void changeArmElevation(ArmMotors armController,
                                          Gamepad gamepad, LinearOpMode opMode) {

        opMode.telemetry.addLine("Changing arm Elevation");
        opMode.telemetry.update();
        int direction = 0;
        while(opMode.opModeIsActive() && gamepad.left_stick_y != 0) {
            double left_stick_y = gamepad.left_stick_y;
            double power = Math.abs(left_stick_y);

            if(left_stick_y > 0) {
                direction = 1;
                armController.raiseArm(power);
            }
            else {
                direction = -1;
                armController.lowerArm(power);
            }
            opMode.idle();
            opMode.telemetry.addLine("In progress of Changing arm Elevation");
            opMode.telemetry.update();
        }

        for(int i = 0;
            i < 5 && opMode.opModeIsActive() && gamepad.left_stick_y == 0;
            i++) {
            if(direction == 1)
                armController.lowerArm(1);
            else armController.raiseArm(0);
            opMode.idle();
            opMode.telemetry.addLine("Attenuating arm fall");
            opMode.telemetry.update();
        }
        armController.stopAll();
    }

    public static void extendArm(ArmMotors armsHandler, Gamepad gamepad,
                                        LinearOpMode opMode) {
        while (opMode.opModeIsActive() && gamepad.right_bumper) {
            armsHandler.extendArm(1);
            opMode.idle();

            opMode.telemetry.addLine("Extending arm");
            opMode.telemetry.update();
        }
        armsHandler.stopAll();
    }

    public static void contractArm(ArmMotors armsHandler, Gamepad gamepad,
                                        LinearOpMode opMode) {
        while (opMode.opModeIsActive() && gamepad.left_bumper) {
            armsHandler.contractArm(1);
            opMode.idle();

            opMode.telemetry.addLine("Contracting arm");
            opMode.telemetry.update();
        }
        armsHandler.stopAll();
    }


    public static void changeHookRotatorElevation(ArmMotors armController,
                                                  Gamepad gamepad,
                                                  TeleOpBasic opMode) {
        while(opMode.opModeIsActive() && gamepad.right_stick_y != 0) {
            double right_stick_y = gamepad.right_stick_y;
            double power = Math.abs(gamepad.right_stick_y);
            if(right_stick_y > 0) {
                armController.raiseHookRotator(power);
            }
            else {
                armController.lowerHookRotator(power);
            }
            opMode.idle();
            opMode.telemetry.addLine("Changing arm hook elevation");
            opMode.telemetry.update();
        }
        armController.stopAll();
    }

    public static void descendFromLander(ArmMotors armController,
                                         Gamepad gamepad,
                                         TeleOpBasic opMode) {
        double power = MotorsConstants.INITIAL_CLIMB_MOTOR_SPEED;

        while(opMode.opModeIsActive() && gamepad.x) {
            armController.descendFromLander(power);
            power *= MotorsConstants.CLIMB_MOTOR_ACCELERATION;
        }

        armController.stopAll();

    }

    public static void climbOnLadder(ArmMotors armController,
                                     Gamepad gamepad,
                                     TeleOpBasic opMode) {
        double power = MotorsConstants.INITIAL_CLIMB_MOTOR_SPEED;

        while(opMode.opModeIsActive() && gamepad.y) {
            armController.climbOnLadder(power);
            power *= MotorsConstants.CLIMB_MOTOR_ACCELERATION;
        }

        armController.stopAll();

    }

}
