package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Adrian on 2018-01-22.
 */

public class UNIT_ARM_GRABBER extends LinearOpMode
{
    Servo leftGrabberHingeServo, rightGrabberHingeServo, leftGrabberClampServo, rightGrabberClampServo;

    @Override public void runOpMode() throws InterruptedException
    {
        leftGrabberHingeServo = hardwareMap.servo.get("leftGrabberHingeServo");
        rightGrabberHingeServo = hardwareMap.servo.get("rightGrabberHingeServo");
        leftGrabberClampServo = hardwareMap.servo.get("leftGrabberClampServo");
        rightGrabberClampServo = hardwareMap.servo.get("rightGrabberClampServo");

        rightGrabberClampServo.setDirection(Servo.Direction.REVERSE);
        rightGrabberHingeServo.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive())
        {
            leftGrabberHingeServo.setPosition(leftGrabberHingeServo.getPosition() + gamepad1.left_stick_y);
            rightGrabberHingeServo.setPosition(leftGrabberHingeServo.getPosition());

            leftGrabberClampServo.setPosition(leftGrabberClampServo.getPosition() + gamepad1.right_stick_y);
            rightGrabberClampServo.setPosition(leftGrabberClampServo.getPosition());
        }
    }
}
