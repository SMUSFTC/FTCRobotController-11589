package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Adrian on 2018-01-24.
 */

@Autonomous(name = "[ACTIVE] Auto A")
public class ACTIVE_AUTO_A extends LinearOpMode
{
    DcMotor leftDriveMotor, rightDriveMotor;

    @Override public void runOpMode() throws InterruptedException
    {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        leftDriveMotor.setPower(1);
        rightDriveMotor.setPower(1);
        Thread.sleep(522);
    }
}
