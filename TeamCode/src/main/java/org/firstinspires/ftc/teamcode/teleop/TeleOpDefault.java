package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.actions.ArmsAction;
import org.firstinspires.ftc.teamcode.teleop.actions.ChangeSpeedAction;
import org.firstinspires.ftc.teamcode.teleop.actions.IntakeAction;
import org.firstinspires.ftc.teamcode.teleop.actions.MoveRobotAction;

@TeleOp(name = "TeleOp Default", group = "Default")
public class TeleOpDefault extends TeleOpBase {

    @Override
    void run() {
        new ChangeSpeedAction(this, true);
        new MoveRobotAction.MoveRobotMobilityDriver(this, true);
        new MoveRobotAction.MoveRobotArmsDriver(this, true);
        new IntakeAction(this, true);
        new ArmsAction.ArmsElevation(this, true);
        new ArmsAction.ArmsMacaraAndPickup(this, true);
    }

}
