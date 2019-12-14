package org.firstinspires.ftc.teamcode.teleop.actions;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.arm.ArmsController;
import org.firstinspires.ftc.teamcode.movement.MovementController;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

public abstract class TeleOpAction {

    TeleOpBase opMode;
    Thread attachedThread;
    ArmsController armsController;
    MovementController movementController;
    Gamepad gamepad1, gamepad2;
    Telemetry telemetry;

    public TeleOpAction(TeleOpBase opMode) {
        this.opMode = opMode;

        armsController = opMode.armsController;
        movementController = opMode.movementController;
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
        telemetry = opMode.telemetry;

        createThread();
    }

    abstract void run();

    private void createThread() {
        telemetry.addLine("HI");
        telemetry.update();
        attachedThread = new Thread() {
            @Override
            public void run() {
                while(opMode.opModeIsActive()) {
                    TeleOpAction.this.run();
                }
            }
        };
        attachedThread.start();
    }
}
