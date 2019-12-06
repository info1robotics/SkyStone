package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.MotorsConstants;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

public class MoveRobotAction extends TeleOpAction {
    public MoveRobotAction(TeleOpBase opMode) {
        super(opMode);
    }

    @Override
    void run() {
        telemetry.addLine(Float.toString(Math.abs(gamepad1.left_stick_x)));
        telemetry.addLine(Boolean.toString(gamepad1.left_stick_x > 0.1));
        telemetry.update();
        if(Math.abs(gamepad1.left_stick_x) > 0.1 ||
                Math.abs(gamepad1.left_stick_y) > 0.1 ||
                gamepad1.left_trigger > 0.1 ||
                gamepad1.right_trigger > 0.1) {
            double drive, strafe, spin = 0.0;

            drive = gamepad1.left_stick_x;
            strafe = -gamepad1.left_stick_y;


            if(gamepad1.left_trigger > 0.0)
                spin = -gamepad1.left_trigger;
            if(gamepad1.right_trigger > 0.0)
                spin = gamepad1.right_trigger;


            drive *= MotorsConstants.robotMovement.MOVEMENT_SPEED;
            strafe *= MotorsConstants.robotMovement.MOVEMENT_SPEED;
            spin *= MotorsConstants.robotMovement.MOVEMENT_SPEED;

            movementController.move(drive, strafe, spin);
        } else if(movementController.isMoving()) movementController.stopAll();
    }
}
