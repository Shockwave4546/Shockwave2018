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

import org.usfirst.frc4546.Shockwave2018.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

import org.usfirst.frc4546.Shockwave2018.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static XboxController Xbox1;
    public static XboxController Xbox2;
    
    public OI() {
        Xbox2 = new XboxController(1);  
        Xbox1 = new XboxController(0);
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    }

    public XboxController getXbox1() {
        return Xbox1;
    }

    public XboxController getXbox2() {
        return Xbox2;
    }

}

