package org.firstinspires.ftc.teamcode.constants;

import com.acmerobotics.dashboard.config.Config;

@Config
public class MotorsConstants {

    @Config
    public static class robotMovement {
        public static double MOVEMENT_SPEED = 1.0;
        public static double MOVEMENT_SPEED_FAST = 1.0;
        public static double MOVEMENT_SPEED_SLOW = 0.3;
    }

    @Config
    public static class barrierMotors {
        public static double LEFT_IDLE = 0.675;
        public static double RIGHT_IDLE = 0.8;

        public static double LEFT_HOLD = 0.575;
        public static double RIGHT_HOLD = 0.9;


    }

    @Config
    public static class ticks {
        public static double TICKS_PER_CENTIMETER = 21.5;
    }
}
