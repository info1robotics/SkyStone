package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.constants.MotorsConstants;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

public class IntakeAction extends TeleOpAction {

    public IntakeAction(TeleOpBase opMode, boolean useThread) {
        super(opMode, useThread);

    }

    @Override
    public void run() {
        if((gamepad2.y && !gamepad2.start) || (gamepad1.y && !gamepad1.start)) {
            intakeController.runIn(MotorsConstants.intakeMotors.INTAKE_SPEED);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if((gamepad2.a  && !gamepad2.start) || (    gamepad1.a  && !gamepad1.start)) {
            intakeController.runOut(MotorsConstants.intakeMotors.INTAKE_SPEED);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void onThreadDestruction() {
        intakeController.stopAll();
    }
}
