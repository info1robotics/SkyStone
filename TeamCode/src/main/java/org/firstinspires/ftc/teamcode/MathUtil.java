package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.odometry.Point;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public final class MathUtil {
    public static double angleWrap(double angle) {
        while(angle < -Math.PI) {
            angle += 2 * Math.PI;
        }

        while(angle > Math.PI) {
            angle -= 2 * Math.PI;
        }

        return angle;
    }

    public static ArrayList<Point> lineCircleIntersection(Point circleCenter, double radius, Point linePoint1, Point linePoint2) {
        if(Math.abs(linePoint1.y - linePoint2.y) < 0.003) {
            linePoint1.y = linePoint2.y + 0.003;

        }

        if(Math.abs(linePoint1.x - linePoint2.x) < 0.003) {
            linePoint2.x = linePoint2.x + 0.003;
        }

        double m1 = (linePoint2.y - linePoint1.y) / (linePoint2.x - linePoint1.x);

        double qA = 1.0 + pow(m1, 2);

        double x1 = linePoint1.x - circleCenter.x;
        double y1 = linePoint1.y - circleCenter.y;

        double qB = (2.0 * m1 * y1) - (2.0 * pow(m1, 2) * x1);

        double qC = (pow(m1, 2) * pow(x1, 2)) - (2.0 * y1 * m1 * x1) + pow(y1, 2) - pow(radius, 2);

        ArrayList<Point> allPoints = new ArrayList<>();

        try {
            double xRoot1 = ((-qB + sqrt(pow(qB, 2)) - (4.0 * qA * qC))) / (2.0 * qA);

            double yRoot1 = m1 * (xRoot1 - x1) + y1;


            // put back offset

            xRoot1 += circleCenter.x;
            yRoot1 += circleCenter.y;

            double minX = Math.min(linePoint1.x, linePoint2.x);
            double maxX = Math.min(linePoint1.x, linePoint2.x);

            if(xRoot1 > minX && xRoot1 < maxX) {
                allPoints.add(new Point(xRoot1, yRoot1));
            }



            double xRoot2 = ((-qB - sqrt(pow(qB, 2)) - (4.0 * qA * qC))) / (2.0 * qA);
            double yRoot2 = m1 * (xRoot2 - x1) + y1;

            xRoot2 += circleCenter.x;
            yRoot2 += circleCenter.y;

            if(xRoot2 > minX && xRoot2 < maxX) {
                allPoints.add(new Point(xRoot2, yRoot2));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return allPoints;
    }

}
