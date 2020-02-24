package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.actions.ArmsAction;
import org.firstinspires.ftc.teamcode.teleop.actions.ChangeSpeedAction;
import org.firstinspires.ftc.teamcode.teleop.actions.IntakeAction;
import org.firstinspires.ftc.teamcode.teleop.actions.MoveRobotAction;
import org.firstinspires.ftc.teamcode.teleop.actions.TeleOpAction;

@TeleOp(name = "TeleOp No MultiThreading")
public class TeleOpNoMultiThreading extends TeleOpBase {
    @Override
    void run() {
        TeleOpAction[] actions = new TeleOpAction[]{
                new ChangeSpeedAction(this, false),
                new MoveRobotAction.MoveRobotMobilityDriver(this, false),
                new MoveRobotAction.MoveRobotArmsDriver(this, false),
                new IntakeAction(this, false),
                new ArmsAction.ArmsElevation(this, false),
                new ArmsAction.ArmsMacaraAndPickup(this, false)
        };
        telemetry.addData("TeleOp", gamepad1.left_stick_x);
        telemetry.update();
        for (TeleOpAction action : actions) {
            action.run();
            idle();
        }
    }
}
