package org.firstinspires.ftc.teamcode.teleop.actions;

import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

public class ArmsAction {

    public static class ArmsElevation extends IntakeAction {
        public ArmsElevation(TeleOpBase opMode) {
            super(opMode);
        }

        @Override
        public void run() {
//            if(gamepad2.left_trigger > 0)
//                armsController.arm1Up(gamepad2.left_trigger);
//            else if(gamepad2.right_trigger > 0)
//                armsController.arm1Down(gamepad2.right_trigger);
//            else armsController.stopDc();
        }

        @Override
        void onThreadDestruction() {

        }
    }

    public static class ArmsMacaraAndPickup extends TeleOpAction {

        public ArmsMacaraAndPickup(TeleOpBase opMode) {
            super(opMode);
        }

        @Override
        public void run() {

//            if(gamepad2.right_bumper)
//                armsController.servoMacaraForward();
//            else if(gamepad2.left_bumper)
//                armsController.servoMacaraBackward();
//            else armsController.stopServo();
//
//            if(gamepad2.x) {
//                armsController.servoCarligToggle();
//                try {
//                    Thread.sleep((long)721.5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }

        @Override
        void onThreadDestruction() {

        }
    }
}
