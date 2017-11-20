package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Adrian on 2017-11-19.
 */

@Disabled
public class AUTONOMOUS_GAMEPLAN_A extends LinearOpMode
{
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    @Override public void runOpMode() throws InterruptedException
    {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

    }
}
