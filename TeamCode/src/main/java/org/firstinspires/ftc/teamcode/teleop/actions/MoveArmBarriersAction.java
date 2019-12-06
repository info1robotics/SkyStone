package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

public class MoveArmBarriersAction extends TeleOpAction {

    public MoveArmBarriersAction(TeleOpBase opMode) {
        super(opMode);
    }

    @Override
    void run() {
        while(opMode.opModeIsActive()) {
            if(gamepad1.a)
                armsController.closeBarrier();

            if(gamepad1.b)
                armsController.openBarrier();
        }
    }
}
