package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by tianne.lee on 11/6/2017.
 */

@TeleOp(name = "[UNIT] Arm Hinge")
public class UNIT_ARM_HINGE extends LinearOpMode
{
    DcMotor leftArmHingeMotor, rightArmHingeMotor;

    @Override public void runOpMode() throws InterruptedException
    {
        leftArmHingeMotor = hardwareMap.dcMotor.get("leftArmHingeMotor");
        rightArmHingeMotor = hardwareMap.dcMotor.get("rightArmHingeMotor");

        rightArmHingeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive())
        {
            leftArmHingeMotor.setPower(-gamepad1.left_stick_y * .1);
            rightArmHingeMotor.setPower(leftArmHingeMotor.getPower());

        }
    }
}
