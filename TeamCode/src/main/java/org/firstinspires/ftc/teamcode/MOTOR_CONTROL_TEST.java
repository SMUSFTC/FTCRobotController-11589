package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by tianne.lee on 11/6/2017.
 */

@TeleOp(name = "MOTOR_CONTROL", group = "Test")
public class MOTOR_CONTROL_TEST extends LinearOpMode
{
    DcMotor rightDriveMotor;
    DcMotor leftDriveMotor;
    DcMotor rightForkliftMotor;
    DcMotor leftForkliftMotor;

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

        rightDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightForkliftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        new Thread(() -> { while (opModeIsActive()) {
            if (gamepad2.start)
            {
                float time = 60*59*((-gamepad2.left_stick_y - cachedForkliftPosition)/6080);
                setCachedForkliftPosition(-gamepad2.left_stick_y);
                leftForkliftMotor.setPower(Math.signum(time)*1);
                rightForkliftMotor.setPower(leftForkliftMotor.getPower());
                try { Thread.sleep((long)(1000*Math.abs(time))); }
                catch (InterruptedException exception) { }
                leftForkliftMotor.setPower(0);
                rightForkliftMotor.setPower(0);
            }
            else if (gamepad2.a) setCachedForkliftPosition(gamepad2.left_stick_y);
            else if (gamepad2.right_trigger == 1)
            {
                leftForkliftMotor.setPower(-gamepad2.left_stick_y);
                rightForkliftMotor.setPower(leftForkliftMotor.getPower());
            }
        }}).start();
        while (opModeIsActive())
        {
            leftDriveMotor.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x);
            rightDriveMotor.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x);
        }
    }
}
