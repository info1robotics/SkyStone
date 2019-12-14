package org.firstinspires.ftc.teamcode.vision;

public class DetectedObject {
    public ObjectCodes objectCode;
    public int widthDelta;

    public DetectedObject(ObjectCodes objectCode, int widthDelta) {
        this.objectCode = objectCode;
        this.widthDelta = widthDelta;
    }
}
