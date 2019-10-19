package org.firstinspires.ftc.teamcode.movement;

public class Power {
    public double fl, fr, bl, br;

    public Power() {

    }

    public Power(double initialPower) {
        fl = fr = bl = br = initialPower;
    }

    public Power(double fl, double fr, double bl, double br) {
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
    }

    public void setPower(Power power) {
        fl = power.fl;
        fr = power.fr;
        bl = power.br;
        br = power.br;
    }

    public void setPower(double speed) {
        fl = fr = bl = br = speed;
    }
    public Power multiply(Power otherPower) {
        return new Power(fl * otherPower.fl, fr * otherPower.fr,
                bl * otherPower.bl, br * otherPower.br);

    }

}
