package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

public class OdometryCoordinateTestAction extends TeleOpAction {

    int x = 0, y = 0;
    public OdometryCoordinateTestAction(TeleOpBase opMode) {
        super(opMode);
    }

    @Override
    public void run() {
        if(gamepad1.dpad_up) {
            x++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(gamepad1.dpad_down) {
            x--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(gamepad1.dpad_right) {
            y++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(gamepad1.dpad_left) {
            y--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        telemetry.addData("x", x);
        telemetry.addData("y", y);

        if(gamepad1.back) {
            telemetry.addData("MOVE STARTED", "");
//            movementController.moveToCoordinates(x, y, 0, 1.0);
        }

        telemetry.update();

    }

    @Override
    void onThreadDestruction() {

    }
}
