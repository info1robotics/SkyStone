package org.firstinspires.ftc.teamcode.odometry;

public class CurvePoint {
    public double x, y, moveSpeed, turnSpeed, followDistance,
            slowDownTurnRadians, slowDownTurnAmount;

    public CurvePoint(double x, double y, double moveSpeed, double turnSpeed, double followDistance, double slowDownTurnRadians, double slowDownTurnAmount) {
        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
        this.turnSpeed = turnSpeed;
        this.followDistance = followDistance;
        this.slowDownTurnRadians = slowDownTurnRadians;
        this.slowDownTurnAmount = slowDownTurnAmount;
    }

    public CurvePoint(CurvePoint other) {
        this.x = other.x;
        this.y = other.y;
        this.moveSpeed = other.moveSpeed;
        this.turnSpeed = other.turnSpeed;
        this.followDistance = other.followDistance;
        this.slowDownTurnRadians = other.slowDownTurnRadians;
        this.slowDownTurnAmount = other.slowDownTurnAmount;
    }

    public Point toPoint() {
        return new Point(x, y);
    }


    public void setPoint(Point point) {
        x = point.x;
        y = point.y;
    }
}
