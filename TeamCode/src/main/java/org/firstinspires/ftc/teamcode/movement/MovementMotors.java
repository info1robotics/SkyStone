package org.firstinspires.ftc.teamcode.movement;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is a basic class which synchronises the movement of motors
 * Supports:
 *     -> Setting RunMode
 *     -> Setting target (in encoder ticks)
 *     -> Setting power of motors
 *     -> Checking if they are busy
 *     -> Easy telemetry output using the toString() method
 */
public class MovementMotors {
    public DcMotor fl, fr, bl, br;
    private Telemetry console;

    public MovementMotors(HardwareMap hardwareMap, Telemetry telemetry) {

        console = telemetry;
        try {

            fl = hardwareMap.get(DcMotor.class, "motorFL");
            fr = hardwareMap.get(DcMotor.class, "motorFR");
            bl = hardwareMap.get(DcMotor.class, "motorBL");
            br = hardwareMap.get(DcMotor.class, "motorBR");
            console.addData
                    ("MovementMotors class", "Done with set up.");
        } catch(Exception ex) {
            console.addLine(ex.toString());
            console.update();
        }

    }

    /**
     * Changes the powers of the wheels' motors.
     * @param power Power to supply to motor.
     */

    public void setPower(Power power) {
        fl.setPower(power.fl);
        fr.setPower(-1.0 * power.fr);
        bl.setPower(power.bl);
        br.setPower(-1.0 * power.br);
    }

    /**
     * Changes the RunMode of the wheels' motors.
     * @param runMode RunMode to be set.
     */
    public void setMode(DcMotor.RunMode runMode) {
        fl.setMode(runMode);
        fr.setMode(runMode);
        bl.setMode(runMode);
        br.setMode(runMode);
    }

    /**
     * Changes the target of the wheels' motors.
     * @param position No. of ticks to target.
     * @param direction Direction the robot must go in to reach the target.
     */
    public void setTargetPosition(int position, Power direction) {
        fl.setTargetPosition((int)(direction.fl * position));
        fr.setTargetPosition((int)(-1.0 * direction.fr * position));
        bl.setTargetPosition((int)(direction.bl * position));
        br.setTargetPosition((int)(-1.0 * direction.br * position));
    }

    /**
     * @return Returns true if at least a motor reached the target position.
     */
    public boolean isBusy() {
        return fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy();
    }


    /**
     * Stops the wheels' motors.
     */
    public void stopAll() {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }


    /**
     * @return Returns a string containing the current powers of
     * the wheels' motors.
     */
    public String toString() {
        String res = "";
        res += " fl: ";
        res += Double.toString(fl.getPower());
        res += " fr: ";
        res += Double.toString(-1.0 * fr.getPower());
        res += " bl: ";
        res += Double.toString(bl.getPower());
        res += " br: ";
        res += Double.toString(-1.0 * br.getPower());
        return res;
    }


}
