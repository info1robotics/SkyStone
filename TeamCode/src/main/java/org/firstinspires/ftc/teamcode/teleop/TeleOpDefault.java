package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MotorsConstants;

@TeleOp(name = "TeleOp Default", group = "Default")
public class TeleOpDefault extends TeleOpBase {

    Thread changeSpeed = new Thread() {
        @Override
        public void run() {
            if(gamepad1.x)
                MotorsConstants.robotMovement.MOVEMENT_SPEED = 0.5;
            if(gamepad1.y)
                MotorsConstants.robotMovement.MOVEMENT_SPEED = 1.0;
        }
    };

    Thread moveArmBarriers = new Thread() {
        @Override
        public void run() {
            if(gamepad1.a)
                armController.closeBarrier();

            if(gamepad1.b)
                armController.openBarrier();
        }
    };

    Thread moveRobot = new Thread() {
        @Override
        public void run() {
            if(Math.abs(gamepad1.left_stick_x) > 0.1 ||
                    Math.abs(gamepad1.left_stick_y) > 0.1 ||
                    gamepad1.left_trigger > 0.1 ||
                    gamepad2.right_trigger > 0.1) {
                if(gamepad1.left_trigger > 0.1)
                    movementController.move(gamepad1.left_stick_x,
                            -gamepad1.left_stick_y, -gamepad1.left_trigger);
                if(gamepad1.right_trigger > 0.1)
                    movementController.move(gamepad1.left_stick_x,
                            -gamepad1.left_stick_y, gamepad1.right_trigger);
            } else if(movementController.isMoving()) movementController.stopAll();
        }
    };

    @Override
    void checkInputs() {
        if(!changeSpeed.isAlive()) changeSpeed.start();
        if(!moveArmBarriers.isAlive()) moveArmBarriers.start();
        if(!moveRobot.isAlive()) moveRobot.start();
    }

}
