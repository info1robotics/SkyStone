package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.actions.ArmsAction;
import org.firstinspires.ftc.teamcode.teleop.actions.FoundationHookAction;
import org.firstinspires.ftc.teamcode.teleop.actions.IntakeAction;
import org.firstinspires.ftc.teamcode.teleop.actions.MoveRobotAction;

@TeleOp(name = "TeleOp Default", group = "Default")
public class TeleOpDefault extends TeleOpBase {

    @Override
    public void initActions() {
        //new ChangeSpeedAction(this);
        new FoundationHookAction(this);
        new MoveRobotAction.MoveRobotMobilityDriver(this, true);
        new MoveRobotAction.MoveRobotArmsDriver(this);
        new IntakeAction(this);
        new ArmsAction.ArmsElevation(this);
        new ArmsAction.ArmsMacaraAndPickup(this);
    }

    @Override
    public void runLoop() {

//        TelemetryPacket packet = new TelemetryPacket();
//
//        ArrayList<DetectedObject> detectedObjects = webcamVision.getAllDetections();
//        for(int i = 0; i < detectedObjects.size(); i++) {
//            packet.put(Integer.toString(i), detectedObjects.get(i));
//        }
//
//        dashboard.sendTelemetryPacket(packet);
    }

}
