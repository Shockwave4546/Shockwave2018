  // RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4546.Shockwave2018;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AccumulatorResult;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc4546.Shockwave2018.commands.*;
import org.usfirst.frc4546.Shockwave2018.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static PDPSub pDPSub;
    public static ControlSub controlSub;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private AnalogInput ai; {
    	ai = new AnalogInput(0);
    	}
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        pDPSub = new PDPSub();
        SmartDashboard.putData(pDPSub);
        controlSub = new ControlSub();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        chooser.addDefault("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
        new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
            
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
            
            Mat source = new Mat();
            Mat output = new Mat();
            
            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                outputStream.putFrame(output);
            }
        }).start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void ai() {
		AnalogInput exampleAnalog = new AnalogInput(0);
		int bits;
		exampleAnalog.setOversampleBits(4);
		bits = exampleAnalog.getOversampleBits();
		exampleAnalog.setAverageBits(2);
		bits = exampleAnalog.getAverageBits();
		
		//Above details the method of obtaining the Average and OversampleBits
		
		AnalogInput.setGlobalSampleRate(62500);
		
		//Default value of samples per channel per second, causes all channels to sample at same rate
		
		AnalogInput example2Analog = new AnalogInput(0);
		int raw = example2Analog.getValue();
		double volts = example2Analog.getVoltage();
		int averageRaw = example2Analog.getAverageValue();
		double averageVolts = example2Analog.getAverageVoltage();
		
		/*
		 *-example2Analog (and so on) used to prevent errors caused by exampleAnalog
		 *-Raw value for bits without calibration
		 *-Volts value for bits after calibration
		*/
		
		AnalogInput example3Analog = new AnalogInput(0);
		example3Analog.setAccumulatorInitialValue(0);
		example3Analog.setAccumulatorCenter(2048);
		example3Analog.setAccumulatorDeadband(10);
		example3Analog.resetAccumulator();
		
		/*
		 * InitialValue: raw value when reset
		 * Center: raw value subtracted from sample before applied to accumulator
		 * Deadband: around center, treated as 0
		 */
		
		
		AnalogInput example4Analog = new AnalogInput(0);
		long count = example4Analog.getAccumulatorCount();
		long value = example4Analog.getAccumulatorValue();
		AccumulatorResult result = new AccumulatorResult();
		example4Analog.getAccumulatorOutput(result);
		count = result.count;
		value = result.value;
		
		/*
		 * Count: samples added since last reset
		 * Value: current value
		 */
	}
	
		Potentiometer pot; {
			pot = new AnalogPotentiometer(0, 360, 30);
			AnalogInput ai = new AnalogInput(1);
			pot = new AnalogPotentiometer(ai, 360, 30);
			Potentiometer pot = new AnalogPotentiometer(0, 360, 30);
			double degrees = pot.get();
		}

}