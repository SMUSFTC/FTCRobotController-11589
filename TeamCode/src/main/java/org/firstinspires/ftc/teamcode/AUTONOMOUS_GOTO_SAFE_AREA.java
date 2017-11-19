package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by tianne.lee on 11/18/2017.
 */

@Autonomous
public class AUTONOMOUS_GOTO_SAFE_AREA extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        leftDriveMotor.setPower(1);
        rightDriveMotor.setPower(1);
        Thread.sleep(782);
    }
}
