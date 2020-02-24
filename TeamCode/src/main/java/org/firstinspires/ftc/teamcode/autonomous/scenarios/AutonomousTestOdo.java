package org.firstinspires.ftc.teamcode.autonomous.scenarios;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.AppConstants.odometry.COUNTS_PER_CENTIMETER;
import static org.firstinspires.ftc.teamcode.constants.AppConstants.robotMovement.MOVEMENT_SPEED;

@Autonomous(name = "ODO TEST", group = "ODOmetry")
public class AutonomousTestOdo extends AutonomousBase {
    @Override
    public void runCommands() throws InterruptedException {
//
//        ArrayList<CurvePoint> allPoints = new ArrayList<>();
//        allPoints.add(new CurvePoint(10.0, 10.0, 0.5, 0.5, 50.0, Math.toRadians(50), 1.0));
//        allPoints.add(new CurvePoint(20.0, 10.0, 0.5, 0.5, 50.0, Math.toRadians(50), 1.0));
//        movementController.odometryMovement.followCurve(allPoints, Math.toRadians(90));

        while(opModeIsActive()) {
            movementController.odometryMovement.goToPosition(0 * COUNTS_PER_CENTIMETER, -53 * COUNTS_PER_CENTIMETER, MOVEMENT_SPEED, 0.3, 90);

        }
        movementController.stopAll();
    }
}
