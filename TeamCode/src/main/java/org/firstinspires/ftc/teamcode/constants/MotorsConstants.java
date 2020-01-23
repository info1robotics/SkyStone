package org.firstinspires.ftc.teamcode.constants;

import com.acmerobotics.dashboard.config.Config;

@Config
public class MotorsConstants {

    @Config
    public static class robotMovement {
        public static double MOVEMENT_SPEED = 1.0;
        public static double MOVEMENT_SPEED_FAST = 1.0;
        public static double MOVEMENT_SPEED_SLOW = 0.7;
    }

    @Config
    public static class intakeMotors {
        public static double INTAKE_SPEED = 1.0;
    }


    @Config
    public static class servoMotors {
        public static double CARLIG_HOLD = 0.5;
        public static double CARLIG_IDLE = 0.0;
    }

    @Config
    public static class ticks {
        public static double TICKS_PER_CENTIMETER = 21.5;
    }
}

