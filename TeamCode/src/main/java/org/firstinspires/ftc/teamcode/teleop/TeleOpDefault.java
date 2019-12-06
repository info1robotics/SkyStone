package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.actions.ChangeSpeedAction;
import org.firstinspires.ftc.teamcode.teleop.actions.MoveArmBarriersAction;
import org.firstinspires.ftc.teamcode.teleop.actions.MoveRobotAction;

@TeleOp(name = "TeleOp Default", group = "Default")
public class TeleOpDefault extends TeleOpBase {

    @Override
    void run() {
        new ChangeSpeedAction(this);
        new MoveArmBarriersAction(this);
        new MoveRobotAction(this);
    }

}
