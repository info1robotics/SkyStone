package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.MotorsConstants;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

public class ChangeSpeedAction extends TeleOpAction {
    public ChangeSpeedAction(TeleOpBase opMode) {
        super(opMode);
    }

    @Override
    void run() {
        if(gamepad1.x)
            MotorsConstants.robotMovement.MOVEMENT_SPEED = 0.5;
        if(gamepad1.y)
            MotorsConstants.robotMovement.MOVEMENT_SPEED = 1.0;
    }
}
