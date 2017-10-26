package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Juan Velasquez on 10/19/2017.
 */
@Autonomous
public class Gen3RedAuto extends  WiredcatsGen3LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    int Vision = 0;

    public void runOpMode() throws InterruptedException {

        initialize();

        //Vuforia Setup
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AXHWmFf/////AAAAGYm/iVzpc0Qou2UZpPq7fJ1XXwBXlhXFiEwyxAffMUsj6jEZZKQO+LedBSOpnsCLquKN6In9AYyLddZaTLhMhDKebAuN92n20opsdVb3gQjrbdlHDOF8YzsBnAe+pPDcwmC1z/Voft2SSJXhaSEC+HtIdtP+uLb9pAcEGpXSP/Y2bB11SIZE2g7rH13NTw0DclIY5fA/34KQVd9IidB0++RRLmCRMgcq50//XU+72IVimzDC3sHKwzHl7i+D9DNUsIV9HEYT9U/90REWVj8RIzfUNFSE3paeYJiumjoRP/KYnCzdas5QUpIoAb8PLM+e3j6MyPIc4JQ+BwbMQKKAdLUaZel0D9Or4bO3XaaZfZoJ";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();
        RelicRecoveryVuMark image = RelicRecoveryVuMark.from(relicTemplate);

        while (!isStarted()) {

            idle();

        }

        relicTrackables.activate();
        waitForStart();
        dropJewel();
        sleep(100);
        if(jewelcs.blue() < jewelcs.red()){

            turn(10);
            if (image == RelicRecoveryVuMark.RIGHT){

                Vision = 1;

            }
            else if(image == RelicRecoveryVuMark.LEFT){

                Vision = 2;

            }
            else{

                Vision = 3;

            }
            sleep(2000);
            turn(-10);
            sleep(50);
            idle();

        }
        else if (jewelcs.red() < jewelcs.blue()){

            turn(-10);
            if (image == RelicRecoveryVuMark.RIGHT){

                Vision = 1;

            }
            else if(image == RelicRecoveryVuMark.LEFT){

                Vision = 2;

            }
            else{

                Vision = 3;

            }
            sleep(2000);
            turn(10);
            sleep(50);

        }
        sleep(1000);
        raiseJewel();
        /*driveForward(0.3, 20, 20, 4);
        sleep(200);
        turn(90);
        sleep(50);
        driveForward(0.3, 10, 10, 3);
        sleep(300);*/

        //Left Column
        if(Vision == 1){

            //Enter Left Column Code

        }
        else if(Vision == 2){

            //Enter Right Column Code

        }
        else if (Vision == 3){

            //Enter Center Column Code

        }
        idle();

    }
}
