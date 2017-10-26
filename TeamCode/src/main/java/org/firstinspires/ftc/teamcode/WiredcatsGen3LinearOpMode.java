package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Juan Velasquez on 10/19/2017.
 */

public abstract class WiredcatsGen3LinearOpMode extends LinearOpMode {

    //Drive Train
    public DcMotor left;
    public DcMotor right;

    //Glyphter
    public DcMotor glyphter;

    //Glyph Claw
    public Servo clawL;
    public Servo clawR;

    //Jewel Servo
    public Servo jewel;

    //Jewel Color Sensor
    public ColorSensor jewelcs;

    public ElapsedTime runtime = new ElapsedTime();

    //Gyro
    public ModernRoboticsI2cGyro gyro;

    public void initialize(){

        //Drive Train
        left = hardwareMap.dcMotor.get("l");
        right =  hardwareMap.dcMotor.get("r");

        left.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Glyphter
        glyphter = hardwareMap.dcMotor.get("g");

        //Glyph Claw
        clawL = hardwareMap.servo.get("cl");
        clawR = hardwareMap.servo.get("cr");

        //Jewel Servo
        jewel = hardwareMap.servo.get("j");

        //Jewel Color Sensor
        jewelcs = hardwareMap.colorSensor.get("cs");

        //Gyro
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("g");

        sleep(2000);
        telemetry.addData("Initialization ", "complete");
        telemetry.update();

    }

    public void turn(int angle) throws InterruptedException {



        int Heading = gyro.getHeading();

        if ((angle * 2) - Heading * 2 >= 10) {

            left.setPower(0.2);
            right.setPower(-0.2);

        }
        else if ((angle * 2) - Heading * 2 <= -10){

            left.setPower(-0.2);
            right.setPower(0.2);

        }
        else if (((angle*2) - Heading * 2) <= 10 || ((angle*2) - Heading * 2 >= -10)){

            stopDrive();

        }

    }

    public void driveForward(double speed, double leftInches, double rightInches, double timeoutS) throws InterruptedException{

        resetEncoders();

        if (opModeIsActive()){

            runtime.reset();
            left.setPower(speed);
            right.setPower(speed);

            while (opModeIsActive() && (runtime.seconds() < timeoutS)){

                if (Math.abs(left.getCurrentPosition()) > Math.abs(leftInches) * 7/8 || Math.abs(right.getCurrentPosition()) > Math.abs(rightInches) * 7/8){

                    left.setPower(speed * 0.5);
                    right.setPower(speed * 0.5);

                }
                else if (Math.abs(left.getCurrentPosition()) > Math.abs(leftInches) * 0.5 || Math.abs(right.getCurrentPosition()) > Math.abs(rightInches) * 0.5){

                    left.setPower(speed * 0.75);
                    right.setPower(speed * 0.75);

                }
                else {
                    telemetry.addData("speed",1);
                }
                if (Math.abs(left.getCurrentPosition()) > Math.abs(leftInches) || Math.abs(right.getCurrentPosition()) > Math.abs(rightInches)){

                    stopDrive();
                    break;

                }
                idle();

            }
            stopDrive();

            resetEncoders();

        }

    }

    public void stopDrive() throws InterruptedException{

        left.setPower(0);
        right.setPower(0);

    }

    public void resetEncoders() {

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void openClaw() throws InterruptedException{

        clawL.setPosition(0.0);
        clawR.setPosition(1.0);

    }
    public void closeClaw() throws InterruptedException{

        clawL.setPosition(0.5);
        clawR.setPosition(0.5);

    }
    public void dropJewel() throws InterruptedException{

        jewel.setPosition(1.0);

    }
    public void raiseJewel() throws InterruptedException{

        jewel.setPosition(0.0);

    }

}
