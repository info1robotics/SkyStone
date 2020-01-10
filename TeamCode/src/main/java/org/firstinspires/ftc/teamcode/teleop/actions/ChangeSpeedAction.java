package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.constants.MotorsConstants;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED_FAST;
import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED_SLOW;

public class ChangeSpeedAction extends TeleOpAction {
    public ChangeSpeedAction(TeleOpBase opMode, boolean useThread) {
        super(opMode, useThread);
    }

    @Override
    public void run() {
        if(gamepad1.x)
            MotorsConstants.robotMovement.MOVEMENT_SPEED = MOVEMENT_SPEED_SLOW;
        if(gamepad1.b)
            MotorsConstants.robotMovement.MOVEMENT_SPEED = MOVEMENT_SPEED_FAST;
    }

    @Override
    void onThreadDestruction() {

    }
}
