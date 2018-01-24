package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Adrian on 2018-01-23.
 */

@TeleOp(name = "[UNIT] Drivetrain")
public class UNIT_DRIVETRAIN extends LinearOpMode
{
    DcMotor leftDriveMotor, rightDriveMotor;

    @Override public void runOpMode() throws InterruptedException
    {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        rightDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        float powerMultiplier = .1f;

        waitForStart();
        while (opModeIsActive())
        {
            leftDriveMotor.setPower(powerMultiplier * (-gamepad1.left_stick_y + gamepad2.left_stick_x));
            rightDriveMotor.setPower(powerMultiplier * (-gamepad1.left_stick_y - gamepad2.left_stick_x));

            if (gamepad1.dpad_up && Math.abs(powerMultiplier) != 1f)
                powerMultiplier += .05;
            else if (gamepad1.dpad_down && Math.abs(powerMultiplier) != 0.5f)
                powerMultiplier -= .05;

            if (gamepad1.a)
                powerMultiplier *= -1f;
        }
    }
}
