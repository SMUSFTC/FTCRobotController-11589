package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Adrian on 2017-11-19.
 */

@TeleOp
public class MANUAL_CONTROL extends LinearOpMode
{
    DcMotor rightDriveMotor;
    DcMotor leftDriveMotor;
    DcMotor rightForkliftMotor;
    DcMotor leftForkliftMotor;
    Servo leftArmServo;
    Servo rightArmServo;
    public float cachedForkliftPosition = -1;

    public void setCachedForkliftPosition(float newCachedForkliftPosition)
    {
        cachedForkliftPosition = newCachedForkliftPosition;
    }
    @Override public void runOpMode() throws InterruptedException
    {
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightForkliftMotor = hardwareMap.dcMotor.get("rightForkliftMotor");
        leftForkliftMotor = hardwareMap.dcMotor.get("leftForkliftMotor");
        rightArmServo = hardwareMap.servo.get("rightArmServo");
        leftArmServo = hardwareMap.servo.get("leftArmServo");

        leftArmServo.setDirection(Servo.Direction.REVERSE);
        leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightForkliftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        leftArmServo.setPosition(0.6);
        rightArmServo.setPosition(0.6);
        new Thread(() -> { while (opModeIsActive()) {
            leftArmServo.setPosition(leftArmServo.getPosition());
            rightArmServo.setPosition(rightArmServo.getPosition());
            if (gamepad2.start)
            {
                float time = 60*59*((-gamepad2.left_stick_y - cachedForkliftPosition)/6080);
                setCachedForkliftPosition(-gamepad2.left_stick_y);
                leftForkliftMotor.setPower(-Math.signum(time)*1);
                rightForkliftMotor.setPower(leftForkliftMotor.getPower());
                try { Thread.sleep((long)(1000*Math.abs(time))); }
                catch (InterruptedException exception) { }
                leftForkliftMotor.setPower(0);
                rightForkliftMotor.setPower(0);
            }
            else if (gamepad2.a) setCachedForkliftPosition(gamepad2.left_stick_y);
            else if (gamepad2.right_trigger == 1)
            {
                leftForkliftMotor.setPower(-gamepad2.left_stick_y*.6);
                rightForkliftMotor.setPower(leftForkliftMotor.getPower());
            }
            else if (gamepad2.x)
            {
                leftArmServo.setPosition(-gamepad2.left_stick_y-gamepad2.left_stick_x);
                rightArmServo.setPosition(-gamepad2.left_stick_y+gamepad2.left_stick_x);
            }
        }}).start();
        while (opModeIsActive())
        {
            leftDriveMotor.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x);
            rightDriveMotor.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x);
        }

    }
}
