package org.firstinspires.ftc.teamcode.odometry;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MathUtil;
import org.firstinspires.ftc.teamcode.movement.MovementController;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.constants.AppConstants.odometry.ALLOWABLE_DISTANCE_ERROR_CENTIMETER;
import static org.firstinspires.ftc.teamcode.constants.AppConstants.odometry.COUNTS_PER_CENTIMETER;

public class OdometryMovement {


    public OdometryGlobalCoordinatePosition globalPositionUpdate;
    private MovementController movementController;
    Thread positionThread;

    public OdometryMovement(MovementController movementController) {

        this.movementController = movementController;


        globalPositionUpdate = new OdometryGlobalCoordinatePosition(
                movementController.movementMotors.br,
                movementController.movementMotors.fl,
                movementController.movementMotors.fr,
                COUNTS_PER_CENTIMETER,
                75
        );
        this.movementController = movementController;

        globalPositionUpdate.reverseRightEncoder();

        positionThread = new Thread(globalPositionUpdate);
        positionThread.start();

    }

    public void followCurve(ArrayList<CurvePoint> allPoints, double followAngle) {
        for(CurvePoint p : allPoints) {
            p.x *= COUNTS_PER_CENTIMETER;
            p.y *= COUNTS_PER_CENTIMETER;
            p.followDistance *= COUNTS_PER_CENTIMETER;
        }
        CurvePoint followMe = getFollowPointPath(allPoints, new Point(globalPositionUpdate.worldXPosition, globalPositionUpdate.worldYPosition), allPoints.get(0).followDistance);

        goToPosition(followMe.x, followMe.y, followMe.moveSpeed, followMe.turnSpeed, followAngle);
    }
    public CurvePoint getFollowPointPath(ArrayList<CurvePoint> pathPoints, Point robotLocation, double followRadius) {
        CurvePoint followMe = new CurvePoint(pathPoints.get(0));

        for(int i = 0; i < pathPoints.size() - 1; i++) {
            CurvePoint startLine = pathPoints.get(i);
            CurvePoint endLine = pathPoints.get(i + 1);

            ArrayList<Point> intersections = MathUtil.lineCircleIntersection(
                    robotLocation, followRadius,
                    startLine.toPoint(), endLine.toPoint()
            );

            double closestAngle = 3000; // INT_MAX

            for(Point thisIntersection : intersections) {
                double angle = Math.atan2(
                        thisIntersection.y - globalPositionUpdate.worldYPosition,
                        thisIntersection.x - globalPositionUpdate.worldXPosition
                );

                double deltaAngle = Math.abs(
                        MathUtil.angleWrap(angle - globalPositionUpdate.worldAngleRadians)
                );

                if(deltaAngle < closestAngle) {
                    closestAngle = deltaAngle;

                    followMe.setPoint(thisIntersection);
                }


            }
        }
        return followMe;
    }

    public void goToPosition(double x, double y, double speed, double turnSpeed, double preferredAngle) {

        double worldXPosition = globalPositionUpdate.worldXPosition;
        double worldYPosition = globalPositionUpdate.worldYPosition;
        double worldAngleRadians = globalPositionUpdate.worldAngleRadians;

        double distanceToTarget = Math.hypot(x - worldXPosition,
                y - worldYPosition);

        double absoluteAngleToTarget = Math.atan2(y - worldYPosition, x - worldXPosition);
        double relativeAngleToPoint = MathUtil.angleWrap(
                absoluteAngleToTarget - (worldAngleRadians - Math.toRadians(90))
        );

        double relativeXToPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;
        double relativeYToPoint = Math.sin(relativeAngleToPoint) * distanceToTarget;

        double movementXPower = relativeXToPoint / (Math.abs(relativeXToPoint) + Math.abs(relativeYToPoint));
        double movementYPower = relativeYToPoint / (Math.abs(relativeXToPoint) + Math.abs(relativeYToPoint));

        movementXPower *= speed;
        movementYPower *= speed;

        double relativeTurnAngle = relativeAngleToPoint - Math.toRadians(180) + preferredAngle;

        double movementTurnPower = Range.clip(relativeTurnAngle/Math.toRadians(30), -1, 1);

        movementTurnPower *= turnSpeed;

        if(distanceToTarget < ALLOWABLE_DISTANCE_ERROR_CENTIMETER)
            movementTurnPower = 0;

        movementController.moveTeleOp(movementXPower, movementYPower, 0);

        movementController.console.addData("worldX", worldXPosition / COUNTS_PER_CENTIMETER);
        movementController.console.addData("worldY", worldYPosition / COUNTS_PER_CENTIMETER);
        movementController.console.addData("compX", movementXPower);
        movementController.console.addData("compY", movementYPower);
        movementController.console.addData("targetX", x / COUNTS_PER_CENTIMETER);
        movementController.console.addData("targetY", y / COUNTS_PER_CENTIMETER);
        movementController.console.update();
    }



}
