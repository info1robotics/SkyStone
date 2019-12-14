package org.firstinspires.ftc.teamcode.autonomous.interpolation;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;

import static org.firstinspires.ftc.teamcode.constants.AutonomyPoints.best;
import static org.firstinspires.ftc.teamcode.constants.MotorsConstants.robotMovement.MOVEMENT_SPEED;

public class AutonomousPointGenerator extends AutonomousBase {

    int locationIndex = 0;

    @Override
    public void runCommands() throws InterruptedException {
        while(opModeIsActive()) {
            goToNextLocation();
        }
        movementController.stopAll();
    }

    public boolean goToNextLocation() {
        if(locationIndex + 1 >= best.length) {
            return false;
        }
        movementController.spinAutonomous(MOVEMENT_SPEED, getAngle(best[locationIndex], best[locationIndex + 1]));
        movementController.moveForwardAutonomous(MOVEMENT_SPEED, new Segment(
                best[locationIndex], best[locationIndex + 1]

        ).getLength());
        locationIndex++;
        return true;
    }

    static double getAngle(Point a, Point b) {

        //Bug: a si b se transmit prin referinta si tu le modifici, deci se modifica si valorile din best[]
        b.x = b.x - a.x;
        b.y = b.y - a.y;

        a = new Point(0, 0);
        Point c = new Point(0, b.y);
        double sB = new Segment(a, c).getLength();
        double sA = new Segment(b, c).getLength();


        double degrees = Math.atan(sA / sB) * 180 / Math.PI;

        if(cad(b) == 1) {
            return degrees;
        }
        if(cad(b) == 2) {
            return -degrees;
        }
        if(cad(b) == 3) {
            return degrees - 180;
        }
        if(cad(b) == 4) {
            return 90 + degrees;
        }
        return Double.NaN;

    }



    static int cad(Point a) {
        if (a.x > 0 && a.y >= 0)
            return 1;
        if (a.x > 0 && a.y < 0)
            return 4;
        if (a.y > 0 && a.x <= 0)
            return 2;
        return 3;
    }
}
