package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

public class FoundationHookAction extends TeleOpAction {
    public FoundationHookAction(TeleOpBase opMode) {
        super(opMode);
    }

    @Override
    public void run() {
        if(gamepad1.x) armsController.grabFoundation();
        if(gamepad1.b) armsController.releaseFoundation();
    }

    @Override
    void onThreadDestruction() {

    }
}
