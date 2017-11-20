package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by tianne.lee on 10/25/2017.
 */

@TeleOp
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
        leftArmServo.setPosition(0);
        rightArmServo.setPosition(0);
        while (opModeIsActive())
        {
            leftArmServo.setPosition(leftArmServo.getPosition());
            rightArmServo.setPosition(rightArmServo.getPosition());
            if (gamepad2.x)
            {
                leftArmServo.setPosition(-gamepad2.left_stick_y-gamepad2.left_stick_x);
                rightArmServo.setPosition(-gamepad2.left_stick_y+gamepad2.left_stick_x);
            }
        }
    }
}