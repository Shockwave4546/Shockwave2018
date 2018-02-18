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

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    private static final int kPDP = 0;
	private AnalogInput ai;
   
	DigitalInput limitswitch;

    private PowerDistributionPanel m_PDP;    
    String gameData;
    
    private static final int kMotorPort1 = 1;//Motor Controller 4
	private SpeedController FrontLeft;
	private double FrontLeftPos = .8;
	private double FrontLeftNeg = .8;
	private double FinalFrontLeft;
	
	private static final int kMotorPort3 = 3;//Motor Controller 6
	private SpeedController FrontRight;
	private double FrontRightPos = .8;
	private double FrontRightNeg = .8;
	private double FinalFrontRight;
	
	private static final int kMotorPort4= 4;//Motor Controller 2
	private SpeedController BackLeft;
	private double BackLeftPos = .8;
	private double BackLeftNeg = .8;
	private double FinalBackLeft;
	
	private static final int kMotorPort5 = 5;//Motor Controller 3
	private SpeedController BackRight;
	private double BackRightPos = .8;
	private double BackRightNeg = .8;
	private double FinalBackRight;
	
	private static final int kIntakePort2 = 2; //Motor Controller 10
	private SpeedController IntakeLeft;
	private double IntakeLeftPos = .5;
	private double IntakeLeftNeg = .5;
	private double FinalIntakeLeft;
	
	private static final int kIntakePort7 = 7; //Motor Controller 7
	private SpeedController IntakeRight;
	private double IntakeRightPos = .5;
	private double IntakeRightNeg = .5;
	private double FinalIntakeRight;
	
	private static final int kArmPort6 = 6;//Motor Controller 3
	private SpeedController ArmMotor;
	private double ArmPos = .5;
	private double ArmNeg = .5;
	private double FinalArm;
	
	private static final int kSlidePort8 = 8;//Motor Controller 1
	private SpeedController SlideMotor;
	private double SlidePos = .5;
	private double SlideNeg = .5;
	private double FinalSlide;
	
	public static Joystick Joystick;
	private double YAxis;
	private double Twist;
	private boolean Trigger;
	private double Slider;
	private boolean Button;
    
	public static XboxController Xbox;
    private double LeftY2;
    private double RightY2;
    
	private double auto = 0.5;
	private double delay1 = 0.1;
	private double delay2 = 0.2;
	private double delay3 = 0.3;
	private double delay4 = 0.4;
	private double delay5 = 0.5;
	private double delay6 = 0.6;
	private double delay7 = 0.7;
	private double delay8 = 0.8;
	private double delay9 = 0.9;
	private double delay0 = 1;
	
	private double Turn = .2;
	//private static final int kMotorPort9 = 9;

	//private SpeedController speedController9;

	//private SpeedController speedController9;
	
	//Slider Variables
	double FLSlideVal;
	double FRSlideVal;
	double BLSlideVal;
	double BRSlideVal;
	double ILSlideVal;
	double IRSlideVal;
	double AMSlideVal;
	double SMSlideVal;
	
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	
    	m_PDP = new PowerDistributionPanel(kPDP);
    	SmartDashboard.putData("Voltage/Current", m_PDP);
    	SmartDashboard.putNumber("Power", m_PDP.getTotalPower());
    	SmartDashboard.putNumber("Total", m_PDP.getTotalPower() * Timer.getMatchTime());

    	
    	ai = new AnalogInput(0);
    	limitswitch = new DigitalInput(1);


    	SmartDashboard.putData("Auto mode", chooser);
        /**new Thread(() -> {
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
*/
    	SmartDashboard.putNumber("Total", m_PDP.getTotalPower() * Timer.getMatchTime());
    	
    	FrontLeft = new Talon(kMotorPort1);
    	FrontLeft.setInverted(false);
    	
    	FrontRight = new Talon(kMotorPort3);
    	FrontRight.setInverted(true);
    	
    	BackLeft = new Talon(kMotorPort4);
    	BackLeft.setInverted(false);
    	
    	BackRight = new Talon(kMotorPort5);
    	BackRight.setInverted(true);
    	
    	IntakeLeft = new Talon(kIntakePort2);//Victor SPX
    	IntakeLeft.setInverted(false);
    	
    	IntakeRight = new Talon(kIntakePort7);//Victor SPX
    	IntakeRight.setInverted(true);
    	
     	ArmMotor = new Talon(kArmPort6);
     	ArmMotor.setInverted(false);
     	
    	SlideMotor = new Talon(kSlidePort8);
    	SlideMotor.setInverted(false);
    	Joystick = new Joystick(0);
    	Xbox = new XboxController(1);
    	
    	//speedController9 = new VictorSP(kMotorPort9);
        SmartDashboard.putData("Auto mode", chooser);
    	SmartDashboard.putData("Auto mode", chooser);
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
   	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.length() > 0){
    		if(gameData.charAt(0) == 'L'){
    			FrontRight.set(auto);
    			FrontLeft.set(auto);
    			BackRight.set(auto);
    			BackLeft.set(auto);
    			Timer.delay(delay0);
    			FrontRight.set(-auto);
    			FrontLeft.set(auto);
    			BackRight.set(-auto);
    			BackLeft.set(auto);
    			Timer.delay(delay1);
    			ArmMotor.set(1);
    			Timer.delay(delay5);
    			IntakeLeft.set(auto);
    			IntakeRight.set(auto);
    		}else{
    			FrontRight.set(auto);
    			FrontLeft.set(auto);
    			BackRight.set(auto);
    			BackLeft.set(auto);
    			Timer.delay(delay0*2);
    			FrontRight.set(-auto);
    			FrontLeft.set(auto);
    			BackRight.set(-auto);
    			BackLeft.set(auto);
    			Timer.delay(delay1);
    			FrontRight.set(auto);
    			FrontLeft.set(auto);
    			BackRight.set(auto);
    			BackLeft.set(auto);
    			Timer.delay(delay0*4);
    			FrontRight.set(-auto);
    			FrontLeft.set(auto);
    			BackRight.set(-auto);
    			BackLeft.set(auto);
    			Timer.delay(delay1);
    			FrontRight.set(auto);
    			FrontLeft.set(auto);
    			BackRight.set(auto);
    			BackLeft.set(auto);
    			Timer.delay(delay5);
    			FrontRight.set(-auto);
    			FrontLeft.set(auto);
    			BackRight.set(-auto);
    			BackLeft.set(auto);
    			Timer.delay(delay1);
    			ArmMotor.set(1);
    			Timer.delay(delay5);
    			IntakeLeft.set(auto);
    			IntakeRight.set(auto);}
    }
   }
    @Override
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        
      //Inserts a blank textbox with true or false value (set to false first from Iterative function)
      		SmartDashboard.putBoolean("ToggleSliderControl",true);
      		
      //Inserts Slider for Each Individual Motor (Simulates the Joystick)
    		SmartDashboard.putNumber("FrontL_Slider",0);
    		SmartDashboard.putNumber("FrontR_Slider",0);
    		SmartDashboard.putNumber("BackL_Slider",0);
    		SmartDashboard.putNumber("BackR_Slider",0);
    		SmartDashboard.putNumber("IntakeL_Slider",0);
    		SmartDashboard.putNumber("IntakeR_Slider",0);
    		SmartDashboard.putNumber("Arm_Slider",0);
    		SmartDashboard.putNumber("SlideMotor_Slider",0);
    		
    	//Inserts Scaler Modifiers for Each Motor and their +/- on Shuffleboard (Number Input)
    		SmartDashboard.putNumber("+FL_Scale",FrontLeftPos);
    		SmartDashboard.putNumber("-FL_Scale",FrontLeftNeg);
    		SmartDashboard.putNumber("+FR_Scale",FrontRightPos);
    		SmartDashboard.putNumber("-FR_Scale",FrontRightNeg);
    		SmartDashboard.putNumber("+BL_Scale",BackLeftPos);
    		SmartDashboard.putNumber("-BL_Scale",BackLeftNeg);
    		SmartDashboard.putNumber("+BR_Scale",BackRightPos);
    		SmartDashboard.putNumber("-BR_Scale",BackRightNeg);
    		
    		SmartDashboard.putNumber("+IL_Scale",IntakeLeftPos);
    		SmartDashboard.putNumber("-IL_Scale",IntakeLeftNeg);
    		SmartDashboard.putNumber("+IR_Scale",IntakeRightPos);
    		SmartDashboard.putNumber("-IR_Scale",IntakeRightNeg);
    		SmartDashboard.putNumber("+AM_Scale",ArmPos);
    		SmartDashboard.putNumber("-AM_Scale",ArmNeg);
    		SmartDashboard.putNumber("+SM_Scale",SlidePos);
    		SmartDashboard.putNumber("-SM_Scale",SlideNeg);
    		
    	//Inserts Final Motor Value from (Slider/Joystick * Scaler)
  			SmartDashboard.putNumber("FLMotor",0);
  			SmartDashboard.putNumber("FRMotor",0);
  			SmartDashboard.putNumber("BLMotor",0);
  			SmartDashboard.putNumber("BRMotor",0);
  			
  			SmartDashboard.putNumber("ILMotor",0);
  			SmartDashboard.putNumber("IRMotor",0);
  			SmartDashboard.putNumber("AMMotor",0);
  			SmartDashboard.putNumber("SMMotor",0);
   
    		
    	//Intake Motor Direction Indicator is Inserted.
    		SmartDashboard.putString("LeftIntakeDirection", "Neutral");
    		SmartDashboard.putString("RightIntakeDirection", "Neutral");
    		
    	//Drive Direction Indicator is Inserted.
    		SmartDashboard.putString("Main Direction", "Idle");
    		SmartDashboard.putString("Turning", "None");
    }

    /**
     * This function is called periodically during operator control
     */
    @Override

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    
       /* if(ai.getValue() > 3500){
			ArmMotor.set(1);
		}
        if(ai.getValue() > 3000 && ai.getValue() < 3450){
			ArmMotor.set(0.5);
		}
		if(ai.getValue() < 2950 && ai.getValue() > 1450){
			ArmMotor.set(0.1);
		}
		if(ai.getValue() < 1000){
			ArmMotor.set(0);
		}
<<<<<<< HEAD
		if(limitswitch.get() == true){	
		}
    
    	AnalogInput.setGlobalSampleRate(62500);
		
		//Default value of samples per channel per second, causes all channels to sample at same rate
		
		AnalogInput exampleAnalog = new AnalogInput(0);
		int raw = exampleAnalog.getValue();
		double volts = exampleAnalog.getVoltage();
		int averageRaw = exampleAnalog.getAverageValue();
		double averageVolts = exampleAnalog.getAverageVoltage();
		
		
		 //-Raw value for bits without calibration
		 //-Volts value for bits after calibration
		
    
		SmartDashboard.putNumber("Analog Value", exampleAnalog.getValue());
		SmartDashboard.putNumber("Analog Voltage", exampleAnalog.getVoltage()); 
		
		//Just putting values on dashboard
		
		*/


		if(limitswitch.get() == true){
	
		}
        Scheduler.getInstance().run();

 
        	//Toggle Button for Boolean on ShuffleBoard
      		boolean ToggleSliderValue = SmartDashboard.getBoolean("ToggleSliderControl", true);
      		
      		if(ai.getValue() > 3500){
    			ArmMotor.set(1);
    		}
            if(ai.getValue() > 3000 && ai.getValue() < 3450){
    			ArmMotor.set(0.5);
    		}
    		if(ai.getValue() < 2950 && ai.getValue() > 1450){
    			ArmMotor.set(0.1);
    		}
    		if(ai.getValue() < 1000){
    			ArmMotor.set(0);
    		}
    		if(limitswitch.get() == true){	
    		}
      		
    		SmartDashboard.putNumber("Analog Value", ai.getValue());
    		SmartDashboard.putNumber("Analog Voltage", ai.getVoltage());
      		
 //ShuffleBoard Control Code is below this line.     	
      	if(ToggleSliderValue == false) {
      		// Slider for the Left Motor and Right Motor (Gets value from slider)
      			FLSlideVal = SmartDashboard.getNumber("FrontL_Slider",0);
      			FRSlideVal = SmartDashboard.getNumber("FrontR_Slider",0);
      			BLSlideVal = SmartDashboard.getNumber("BackL_Slider",0);
      			BRSlideVal = SmartDashboard.getNumber("BackR_Slider",0);
      			
      			ILSlideVal = SmartDashboard.getNumber("IntakeL_Slider",0);
      			IRSlideVal = SmartDashboard.getNumber("IntakeR_Slider",0);
      			AMSlideVal = SmartDashboard.getNumber("Arm_Slider",0);
      			SMSlideVal = SmartDashboard.getNumber("SlideMotor_Slider",0);
      			
      		// Retrieves Scaler set for Drive Train Motors on ShuffleBoard
      			FrontLeftPos = SmartDashboard.getNumber("+FL_Scale",FrontLeftPos);
        		FrontLeftNeg = SmartDashboard.getNumber("-FL_Scale",FrontLeftNeg);
        		FrontRightPos = SmartDashboard.getNumber("+FR_Scale",FrontRightPos);
        		FrontRightNeg = SmartDashboard.getNumber("-FR_Scale",FrontRightNeg);
        		BackLeftPos = SmartDashboard.getNumber("+BL_Scale",BackLeftPos);
        		BackLeftNeg = SmartDashboard.getNumber("-BL_Scale",BackLeftNeg);
        		BackRightPos = SmartDashboard.getNumber("+BR_Scale",BackRightPos);
        		BackRightNeg = SmartDashboard.getNumber("-BR_Scale",BackRightNeg);
     		
        	//Retrieves Intake (Left & Right), Arm Motor, Slide Motor Scalers from ShuffleBoard.
        		IntakeLeftPos = SmartDashboard.getNumber("+IL_Scale",IntakeLeftPos);
        		IntakeLeftNeg = SmartDashboard.getNumber("-IL_Scale",IntakeLeftNeg);
        		IntakeRightPos = SmartDashboard.getNumber("+IR_Scale",IntakeRightPos);
        		IntakeRightNeg = SmartDashboard.getNumber("-IR_Scale",IntakeRightNeg);
        		ArmPos = SmartDashboard.getNumber("+AM_Scale",ArmPos);
        		ArmNeg = SmartDashboard.getNumber("-AM_Scale",ArmNeg);
        		SlidePos = SmartDashboard.getNumber("+SM_Scale",SlidePos);
        		SlideNeg = SmartDashboard.getNumber("-SM_Scale",SlideNeg);
        		
        		
      		//(Front Left Motor Final Value) Displayed on ShuffleBoard Depending on Scaler
      			if(FLSlideVal > 0){
      				FinalFrontLeft = FLSlideVal*FrontLeftNeg;
      			}else if(FLSlideVal < 0){
      				FinalFrontLeft = FLSlideVal*FrontLeftPos;
      			}
     		
      		//(Front Right Motor Final Value) Displayed on ShuffleBoard Depending on Scaler
      			if(FRSlideVal > 0){
      				FinalFrontRight = FRSlideVal*FrontRightNeg;
      			}else if(FRSlideVal < 0){
      				FinalFrontRight = FRSlideVal*FrontRightPos;
      			}
			
      		//(Back Left Motor Final Value) Displayed on ShuffleBoard Depending on Scaler
      			if(BLSlideVal > 0){
      				FinalBackLeft = BLSlideVal*BackLeftNeg;
      			}else if(BLSlideVal < 0){
      				FinalBackLeft = BLSlideVal*BackLeftPos;
      			}
			
      		//(Back Right Motor Final Value) Displayed on ShuffleBoard Depending on Scaler
      			if(BRSlideVal > 0){
      				FinalBackRight = BRSlideVal*BackRightNeg;
      			}else if(BRSlideVal < 0){
      				FinalBackRight = BRSlideVal*BackRightPos;
      			}
      			
      		//(Intake Left Motor Final Value) Displayed on ShuffleBoard Depending on Scaler
      			if(ILSlideVal > 0){
      				FinalIntakeLeft = ILSlideVal*IntakeLeftNeg;
      			}else if(ILSlideVal < 0){
      				FinalIntakeLeft = ILSlideVal*IntakeLeftPos;
      			}
      			
      		//(Intake Right Motor Final Value) Displayed on ShuffleBoard Depending on Scaler
      			if(IRSlideVal > 0){
      				FinalIntakeRight = IRSlideVal*IntakeRightPos;
      			}else if(IRSlideVal < 0){
      				FinalIntakeRight = IRSlideVal*IntakeRightNeg;
      			}
      			
      		//(Arm Motor Final Value) Displayed on ShuffleBoard Depending on Scaler
      			if(AMSlideVal > 0){
      				FinalArm = AMSlideVal*ArmPos;
      			}else if(ILSlideVal < 0){
      				FinalArm = AMSlideVal*ArmNeg;
      			}
      			
      		//(Slide Motor Final Value) Displayed on ShuffleBoard Depending on Scaler
      			if(SMSlideVal > 0){
      				FinalSlide = SMSlideVal*SlidePos;
      			}else if(ILSlideVal < 0){
      				FinalSlide = SMSlideVal*SlideNeg;
      			}
      			
      			
      		//Final Motor Value Output Displayed on Shuffleboard
      			SmartDashboard.putNumber("FLMotor",FinalFrontLeft);
      			SmartDashboard.putNumber("FRMotor",FinalFrontRight);
      			SmartDashboard.putNumber("BLMotor",FinalBackLeft);
      			SmartDashboard.putNumber("BRMotor",FinalBackRight);
      			
      			SmartDashboard.putNumber("ILMotor",FinalIntakeLeft);
      			SmartDashboard.putNumber("IRMotor",FinalIntakeRight);
      			SmartDashboard.putNumber("AMMotor",FinalArm);
      			SmartDashboard.putNumber("SMMotor",FinalSlide);
      			
      			
      			FrontLeft.set(FinalFrontLeft);
      			FrontRight.set(FinalFrontRight);
      			BackLeft.set(FinalBackLeft);
      			BackRight.set(FinalBackRight);
      			
      			IntakeLeft.set(FinalIntakeLeft);
      			IntakeRight.set(FinalIntakeRight);
      			ArmMotor.set(FinalArm);
      			SlideMotor.set(FinalSlide);
      			
      	} 
      	
      	
      	
      	
//Controller (Xbox and Joystick) Code is below this line.
      	if(ToggleSliderValue == true) {
      	// Retrieves Scaler set for Drive Train Motors on ShuffleBoard
  			FrontLeftPos = SmartDashboard.getNumber("+FL_Scale",FrontLeftPos);
    		FrontLeftNeg = SmartDashboard.getNumber("-FL_Scale",FrontLeftNeg);
    		FrontRightPos = SmartDashboard.getNumber("+FR_Scale",FrontRightPos);
    		FrontRightNeg = SmartDashboard.getNumber("-FR_Scale",FrontRightNeg);
    		BackLeftPos = SmartDashboard.getNumber("+BL_Scale",BackLeftPos);
    		BackLeftNeg = SmartDashboard.getNumber("-BL_Scale",BackLeftNeg);
    		BackRightPos = SmartDashboard.getNumber("+BR_Scale",BackRightPos);
    		BackRightNeg = SmartDashboard.getNumber("-BR_Scale",BackRightNeg);
 		
    	//Retrieves Intake (Left & Right), Arm Motor, Slide Motor Scalers from ShuffleBoard.
    		IntakeLeftPos = SmartDashboard.getNumber("+IL_Scale",IntakeLeftPos);
    		IntakeLeftNeg = SmartDashboard.getNumber("-IL_Scale",IntakeLeftNeg);
    		IntakeRightPos = SmartDashboard.getNumber("+IR_Scale",IntakeRightPos);
    		IntakeRightNeg = SmartDashboard.getNumber("-IR_Scale",IntakeRightNeg);
    		ArmPos = SmartDashboard.getNumber("+AM_Scale",ArmPos);
    		ArmNeg = SmartDashboard.getNumber("-AM_Scale",ArmNeg);
    		SlidePos = SmartDashboard.getNumber("+SM_Scale",SlidePos);
    		SlideNeg = SmartDashboard.getNumber("-SM_Scale",SlideNeg);
    		      	
      		LeftY2 = Xbox.getY(Hand.kLeft);
	    	RightY2 = Xbox.getY(Hand.kRight);
	    	YAxis = Joystick.getY();
	    	Twist = Joystick.getTwist();
	    	Slider = (-(Joystick.getThrottle()-1)/2);
	    	Trigger = Joystick.getTrigger();
	    	Button = Joystick.getTop();
	    
	    	if(Button==false && Trigger==false){
	        	FinalIntakeLeft = 0;
	        	IntakeLeft.set(FinalIntakeLeft);
	        	FinalIntakeRight = 0;
	        	IntakeRight.set(FinalIntakeRight);
	        }else if(Button==true && Trigger==false){
	        	FinalIntakeLeft = (IntakeLeftPos);
	        	IntakeLeft.set(FinalIntakeLeft);
	        	FinalIntakeRight = (-IntakeRightNeg);
	        	IntakeRight.set(FinalIntakeRight);
	        }else if(Button==false && Trigger==true){
	        	FinalIntakeLeft = (-IntakeLeftNeg);
	        	IntakeLeft.set(FinalIntakeLeft);
	        	FinalIntakeRight = (IntakeRightPos);
	        	IntakeRight.set(FinalIntakeRight);

	        }//intake motors
	        
	        
	        if(LeftY2<.1 && LeftY2>-.1){
	        	SlideMotor.set(0);
	        	FinalSlide = 0;
	        }else if(LeftY2<.1 && LeftY2<=-.1){
	        	FinalSlide = (LeftY2*SlideNeg);
	        	SlideMotor.set(FinalSlide);
	        }else if(LeftY2>=.1 && LeftY2>-.1){
	        	FinalSlide = (LeftY2*SlidePos);
	        	SlideMotor.set(FinalSlide);
	        }//Slide motor
	        
	        if(RightY2<.1 && RightY2>-.1){
	        	ArmMotor.set(0);
	        	FinalArm = 0;
	        }else if(RightY2<.1 && RightY2<=-.1){
	        	FinalArm = (RightY2*ArmNeg);
	        	ArmMotor.set(FinalArm);
	        }else if(RightY2>=.1 && RightY2>-.1){
	        	FinalArm = (RightY2*ArmPos);
	        	ArmMotor.set(FinalArm);
	        }//Arm motor
	        
	        
	        if(YAxis<=.15 && YAxis>=-.15 && Twist<=.3 && Twist>=-.3){
	        	FinalFrontLeft = (0);
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = (0);
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = (0);
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = (0);
	        	BackRight.set(FinalBackRight);
	        	//Idle
	        	
	        }else if(YAxis>.15 && Twist<=.3 && Twist>=-.3){

	        	FinalFrontLeft = ((YAxis*FrontLeftPos)*Slider);
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = ((YAxis*BackLeftPos)*Slider);
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = ((YAxis*FrontRightPos)*Slider);
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = ((YAxis*BackRightPos)*Slider);
	        	BackRight.set(FinalBackRight);
	        	//Motor Back
	        	
	        }else if(YAxis<-.15 && Twist<=.3 && Twist>=-.3){
	        	FinalFrontLeft = ((YAxis*FrontLeftNeg)*Slider);
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = ((YAxis*BackLeftNeg)*Slider);
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = ((YAxis*FrontRightNeg)*Slider);
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = ((YAxis*BackRightNeg)*Slider);
	        	BackRight.set(FinalBackRight);
	        	//Motor Forward
	        	
	        }else if(YAxis<-.15 && Twist>.3){
	        	FinalFrontLeft = (((YAxis*FrontLeftNeg)*Slider)*(1-Twist+Turn));
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = (((YAxis*BackLeftNeg)*Slider)*(1-Twist+Turn));
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = ((YAxis*FrontLeftNeg)*Slider);
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = ((YAxis*BackLeftNeg)*Slider);
	        	BackRight.set(FinalBackRight);
	        	//Forward Right
	        	
	        }else if(YAxis<-.15 && Twist<-.3){
	        	FinalFrontLeft = ((YAxis*FrontLeftPos)*Slider);
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = ((YAxis*BackLeftPos)*Slider);
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = (((YAxis*FrontRightPos)*Slider)*(1+Twist+Turn));
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = (((YAxis*BackRightPos)*Slider)*(1+Twist+Turn));
	        	BackRight.set(FinalBackRight);
	        	//Forward Left
	        	
	        }else if(YAxis>.15 && Twist>.3){
	        	FinalFrontLeft = ((YAxis*FrontLeftPos)*Slider);
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = ((YAxis*BackLeftPos)*Slider);
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = (((YAxis*FrontLeftPos)*Slider)*(1-Twist+Turn));
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = (((YAxis*BackLeftPos)*Slider)*(1-Twist+Turn));
	        	BackRight.set(FinalBackRight);
	        	//Back Right
	        	
	        }else if(YAxis>.15 && Twist<-.3){ 
	        	FinalFrontLeft = (((YAxis*FrontLeftPos)*Slider)*(1+Twist+Turn));
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = (((YAxis*BackLeftPos)*Slider)*(1+Twist+Turn));
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = ((YAxis*FrontRightPos)*Slider);
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = ((YAxis*BackRightPos)*Slider);
	        	BackRight.set(FinalBackRight);
	        	//Back Left
	        	
	        }else if(YAxis<=.15 && YAxis>=-.15 && Twist<-.3){
	        	FinalFrontLeft = ((Twist*FrontLeftNeg)*Slider);
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = ((Twist*BackLeftNeg)*Slider);
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = ((Twist*FrontRightPos)*-Slider);
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = ((Twist*BackRightPos)*-Slider);
	        	BackRight.set(FinalBackRight);
	        	//Spin Left
	        	
	        }else if(YAxis<=.15 && YAxis>=-.15 && Twist>.3){
	        	FinalFrontLeft = ((Twist*FrontLeftPos)*Slider);
	        	FrontLeft.set(FinalFrontLeft);
	        	
	        	FinalBackLeft = ((Twist*BackLeftPos)*Slider);
	        	BackLeft.set(FinalBackLeft);
	        	
	        	FinalFrontRight = ((Twist*FrontRightNeg)*-Slider);
	        	FrontRight.set(FinalFrontRight);
	        	
	        	FinalBackRight = ((Twist*BackRightNeg)*-Slider);
	        	BackRight.set(FinalBackRight);
	        	//Spin Right
	        	
	        }      
	    	//Final Motor Value Output Displayed on Shuffleboard (from Controller)
  				SmartDashboard.putNumber("FLMotor",FinalFrontLeft);
  				SmartDashboard.putNumber("FRMotor",FinalFrontRight);
  				SmartDashboard.putNumber("BLMotor",FinalBackLeft);
  				SmartDashboard.putNumber("BRMotor",FinalBackRight);
  			
  				SmartDashboard.putNumber("ILMotor",FinalIntakeLeft);
  				SmartDashboard.putNumber("IRMotor",FinalIntakeRight);
  				SmartDashboard.putNumber("AMMotor",FinalArm);
  				SmartDashboard.putNumber("SMMotor",FinalSlide);
      	}
    }
}
