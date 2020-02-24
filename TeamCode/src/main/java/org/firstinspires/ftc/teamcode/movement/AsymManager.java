package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AsymManager {

    private Power targetPower;
    private LinearOpMode opMode;
    public Power activePower;
    private Thread runningThread;

    AsymManager(Power initPower, Power targetPower, LinearOpMode opMode) {
        this.activePower = initPower;
        this.targetPower = targetPower;
        this.opMode = opMode;

        createThread();
    }

    private void createThread() {
        runningThread = new Thread() {
            @Override
            public void run() {
                while(opMode.opModeIsActive()) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    activePower = activePower.add(
                            new Power((targetPower.fl - activePower.fl) / 2)
                    );
                }

            }
        };

        runningThread.start();
    }
}
