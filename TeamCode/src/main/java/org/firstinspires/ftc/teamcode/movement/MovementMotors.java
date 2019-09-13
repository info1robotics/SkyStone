package org.firstinspires.ftc.teamcode.movement;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MovementMotors {
    private DcMotor fl, fr, bl, br;
    private Telemetry console;

    public MovementMotors(HardwareMap hardwareMap, Telemetry telemetry) {

        console = telemetry;
        console.addData("MovementMotors class", "Setting Up.");
        console.update();

        fl = hardwareMap.get(DcMotor.class, "motorFL");
        fr = hardwareMap.get(DcMotor.class, "motorFR");
        bl = hardwareMap.get(DcMotor.class, "motorBL");
        br = hardwareMap.get(DcMotor.class, "motorBR");

        console.addData
                ("MovementMotors class", "Done with set up.");
    }

    public void setPower(Power power) {
        fl.setPower(power.fl);
        fr.setPower(power.fr);
        bl.setPower(power.bl);
        br.setPower(power.br);

        //console.addData("DEBUG", fl.toString() + " " +
                //fr.toString() + " " + bl.toString() + " " + br.toString());
        //console.update();
    }



}
