package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.constants.AppConstants;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

import static org.firstinspires.ftc.teamcode.constants.AppConstants.robotMovement.MOVEMENT_SPEED_FAST;
import static org.firstinspires.ftc.teamcode.constants.AppConstants.robotMovement.MOVEMENT_SPEED_SLOW;


/**
 * <i>Action</i> class which listens for the x and b buttons, which change the
 * robot's movement speed.
 */
public class ChangeSpeedAction extends TeleOpAction {
    public ChangeSpeedAction(TeleOpBase opMode) {
        super(opMode);
    }

    @Override
    public void run() {
        if(gamepad1.x)
            AppConstants.robotMovement.MOVEMENT_SPEED = MOVEMENT_SPEED_SLOW;
        if(gamepad1.b)
            AppConstants.robotMovement.MOVEMENT_SPEED = MOVEMENT_SPEED_FAST;
    }

    @Override
    void onThreadDestruction() {

    }
}
