package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.vision.DetectedObject;
import org.firstinspires.ftc.teamcode.vision.ObjectCodes;
import org.firstinspires.ftc.teamcode.vision.WebcamVision;
import org.firstinspires.ftc.teamcode.movement.Signs;

/*
 * This sample demonstrates how to stream frames from Vuforia to the dashboard. Make sure to fill in
 * your Vuforia key below and select the 'Camera' preset on top right of the dashboard. This sample
 * also works for UVCs with slight adjustments.
 */
@Autonomous
public class AutonomousVision extends AutonomousBasic {

    @Override
    public void runCommands() throws InterruptedException {

        movementController.moveRight(MOVEMENT_SPEED, 18.0);
        movementController.moveBackward(MOVEMENT_SPEED, 47.0);

        int skystonesFound = 0;
        boolean canDetectSkystone = true;
        while(skystonesFound < 2) {
            DetectedObject obj = visionController.getDetection();
            if(obj.objectCode == ObjectCodes.SKYSTONE && obj.widthDelta < 60) {
                armController.closeBarrier();
                Thread.sleep(500);
                armController.openBarrier();
                skystonesFound++;
                canDetectSkystone = false;
                moveStone();
            } else if(obj.objectCode == ObjectCodes.STONE) {
                canDetectSkystone = true;
            }
            movementController.moveLeft(MOVEMENT_SPEED, 10);
        }
        movementController.stopAll();

    }

    private  void moveStone() {
        movementController.moveBackward(MOVEMENT_SPEED, 80);
        movementController.moveForward(MOVEMENT_SPEED, 80);
    }
}