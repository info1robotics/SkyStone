package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TELEOP FIELD TEST", group = "DEBUG")
public class TeleOpFieldTest extends TeleOpBase {


    @Override
    public void initActions() {

        double[] xPoints = new double[]{0, 75, 5}, yPoints = new double[]{0, 75, 3};

        TelemetryPacket packet = new TelemetryPacket();
        packet.fieldOverlay()
                .setStrokeWidth(1)
                .setStroke("goldenrod")
                .setFill("red")
                .strokePolyline(xPoints, yPoints);
        dashboard.sendTelemetryPacket(packet);
    }

    @Override
    public void runLoop() {

    }
}
