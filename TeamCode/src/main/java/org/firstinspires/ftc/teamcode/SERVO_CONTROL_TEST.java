package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by tianne.lee on 10/25/2017.
 */

@TeleOp(name = "Servo_Control_Test", group = "Linear OpMode")
public class SERVO_CONTROL_TEST extends LinearOpMode
{
    Servo leftArmServo;
    Servo rightArmServo;

    @Override public void runOpMode() throws InterruptedException
    {
        rightArmServo = hardwareMap.servo.get("rightArmServo");
        leftArmServo = hardwareMap.servo.get("leftArmServo");
        leftArmServo.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        rightArmServo.setPosition(0.6);
        leftArmServo.setPosition(0.6);
        while (opModeIsActive())
        {
            leftArmServo.setPosition(leftArmServo.getPosition());
            rightArmServo.setPosition(leftArmServo.getPosition());
            if (gamepad1.dpad_up) leftArmServo.setPosition(1);
            else if (gamepad1.dpad_down) leftArmServo.setPosition(0.6);
        }
    }
}