package org.firstinspires.ftc.teamcode.teleop.actions;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.arm.ArmsController;
import org.firstinspires.ftc.teamcode.arm.IntakeController;
import org.firstinspires.ftc.teamcode.movement.MovementController;
import org.firstinspires.ftc.teamcode.teleop.TeleOpBase;

/**
 * Classes extended from here listen for gamepad input, each on a separate
 * thread, and trigger the corresponding actions. I'll call them <i>action</i>
 * classes.
 */
public abstract class TeleOpAction {

    TeleOpBase opMode;
    Thread attachedThread;
    ArmsController armsController;
    MovementController movementController;
    IntakeController intakeController;
    Gamepad gamepad1, gamepad2;
    Telemetry telemetry;


    public TeleOpAction(TeleOpBase opMode) {
        this.opMode = opMode;

        armsController = opMode.armsController;
        movementController = opMode.movementController;
        intakeController = opMode.intakeController;
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
        telemetry = opMode.telemetry;

        createThread();
    }

    /**
     * Abstract method where we check for gamepad input.
     */
    public abstract void run();


    /**
     * Abstract method for cleanup on exiting the opMode.
     * e.g.: Stopping the intake's motors.
     */
    abstract void onThreadDestruction();

    /**
     * Creates the thread where we listen for gamepad input.
     */
    private void createThread() {
        attachedThread = new Thread() {
            @Override
            public void run() {
                while(opMode.opModeIsActive()) {
                    TeleOpAction.this.run();
                    opMode.idle();
                }
                onThreadDestruction();
            }
        };

        attachedThread.setPriority(10);
        attachedThread.setName(getClass().getSimpleName());
        attachedThread.start();
    }
}
