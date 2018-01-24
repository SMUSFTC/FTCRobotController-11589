package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Adrian on 2017-11-19.
 */

@TeleOp(name = "[ACTIVE] Control")
public class MANUAL_CONTROL extends LinearOpMode
{
    DcMotor leftDriveMotor, rightDriveMotor, leftArmHingeMotor, rightArmHingeMotor;
    Servo leftGrabberHingeServo, rightGrabberHingeServo, leftGrabberClampServo, rightGrabberClampServo;

    @Override public void runOpMode() throws InterruptedException
    {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftArmHingeMotor = hardwareMap.dcMotor.get("leftArmHingeMotor");
        rightArmHingeMotor = hardwareMap.dcMotor.get("rightArmHingeMotor");

        rightArmHingeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftGrabberHingeServo = hardwareMap.servo.get("leftGrabberHingeServo");
        rightGrabberHingeServo = hardwareMap.servo.get("rightGrabberHingeServo");
        leftGrabberClampServo = hardwareMap.servo.get("leftGrabberClampServo");
        rightGrabberClampServo = hardwareMap.servo.get("rightGrabberClampServo");

        rightGrabberClampServo.setDirection(Servo.Direction.REVERSE);
        rightGrabberHingeServo.setDirection(Servo.Direction.REVERSE);

        float powerMultiplier = .3f;
        boolean previousAButtonValue = false;

        waitForStart();
        new Thread(() -> {
            while (opModeIsActive())
            {
                leftGrabberHingeServo.setPosition(leftGrabberHingeServo.getPosition() + gamepad2.left_stick_y * 0.01f);
                rightGrabberHingeServo.setPosition(leftGrabberHingeServo.getPosition());

                leftGrabberClampServo.setPosition(leftGrabberClampServo.getPosition() + gamepad2.right_stick_y * 0.01f);
                rightGrabberClampServo.setPosition(leftGrabberClampServo.getPosition());

                leftArmHingeMotor.setPower((gamepad2.right_trigger * 1.5 - gamepad2.left_trigger) * 0.1f);
                rightArmHingeMotor.setPower(leftArmHingeMotor.getPower());
            }
        }).start();
        while (opModeIsActive())
        {
            leftDriveMotor.setPower(powerMultiplier * (-gamepad1.left_stick_y + gamepad1.left_stick_x));
            rightDriveMotor.setPower(powerMultiplier * (-gamepad1.left_stick_y - gamepad1.left_stick_x));

            if (gamepad1.dpad_up && Math.abs(powerMultiplier) != 1f)
                powerMultiplier += .05;
            else if (gamepad1.dpad_down && Math.abs(powerMultiplier) <= 0.05f)
                powerMultiplier -= .05;

            if (gamepad1.a && !previousAButtonValue)
                powerMultiplier *= -1f;

            previousAButtonValue = gamepad1.a;
        }
    }
}
