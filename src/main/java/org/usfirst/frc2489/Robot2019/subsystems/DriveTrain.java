// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2489.Robot2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;

import org.usfirst.frc2489.Robot2019.ArduinoI2C;
import org.usfirst.frc2489.Robot2019.JeVoisInterface;
import org.usfirst.frc2489.Robot2019.RobotMap;
import org.usfirst.frc2489.Robot2019.Sonar;
import org.usfirst.frc2489.Robot2019.VisionTarget;
import org.usfirst.frc2489.Robot2019.commands.*;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX leftFrontTalonSRX = RobotMap.driveTrainLeftFrontTalonSRX;
    private final WPI_TalonSRX leftRearTalonSRX = RobotMap.driveTrainLeftRearTalonSRX;
    private final SpeedControllerGroup leftSpeedControllerGroup = RobotMap.driveTrainLeftSpeedControllerGroup;
    private final WPI_TalonSRX rightFrontTalonSRX = RobotMap.driveTrainRightFrontTalonSRX;
    private final WPI_TalonSRX rightRearTalonSRX = RobotMap.driveTrainRightRearTalonSRX;
    private final SpeedControllerGroup rightSpeedControllerGroup = RobotMap.driveTrainRightSpeedControllerGroup;
    private final DifferentialDrive differentialDrive = RobotMap.driveTrainDifferentialDrive;
    private final AHRS ahrs = RobotMap.ahrs;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final double increment = 0.1;
    private double curLeft; 
    private double curRight; 
    
   // private final ArduinoI2C aI2C = RobotMap.aI2C;
    
	/*
    private final Sonar leftSonar = RobotMap.leftSonar;
    private final Sonar rightSonar = RobotMap.rightSonar;    
    
    private final DigitalInput leftRange = RobotMap.leftRange;
    private final DigitalInput rightRange = RobotMap.rightRange;

    private final DigitalInput leftLine = RobotMap.leftLine;
    private final DigitalInput centerLine = RobotMap.centerLine;
    private final DigitalInput rightLine = RobotMap.rightLine;
    
    private final Gyro gyro = RobotMap.gyro;
    */
    private final JeVoisInterface ji = RobotMap.ji;
    
    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoysticks());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        curLeft = 0;
        curRight = 0;
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }
    
    public double rampUp(double cur, double target) {
    	if (target > 0) {
    		if(cur <0) {
    			cur = 0;
    		} else {
    			if(cur < target) {
    				cur = cur + increment;
    				if (cur > target) {
    					cur = target;
    				}
    			} else {
    				cur = target;
    			}
    		}
    	} else {
    		if(cur > 0) {
    			cur = 0;
    		}else {
    			if(cur > target) {
    				cur = cur - increment;
    				if (cur < target) {
    					cur = target;
    				}
    			} else {
    				cur = target;
    			}
    		}
    	}
    	
    	return cur;
    }
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void drive(double leftSpeed, double rightSpeed) {
    	// System.out.println("left  speed = " + leftSpeed + " right speed = " + rightSpeed);

    	curLeft = rampUp(curLeft, leftSpeed);
    	curRight = rampUp(curRight, rightSpeed);

    	// System.out.println("left cur speed = " + curLeft + " right cur speed = " + curRight);
    	
    	double angle = ahrs.getAngle();
    	SmartDashboard.putNumber("ahrs.getAngle", angle); 
    	//System.out.println("1angle = "+ angle);
    	//double angle2 = gyro.getAngle();
    	//SmartDashboard.putNumber("gyro.getAngle", angle2);
    	
    	//String output = aI2C.read() ; 
    	//SmartDashboard.putString("aI2C", output);
    	//System.out.println(output);
    	
    	//double leftSonarD = getLeftSonar();
    	//double rightSonarD = getRightSonar();
    	//SmartDashboard.putNumber("leftSonarD", leftSonarD);
    	//SmartDashboard.putNumber("rightSonarD", rightSonarD);

    	//System.out.println("left sonar = "+ leftSonarD + " right sonar = "+ rightSonarD);
    	//int leftOversampleBits = leftSonar.m_analog.getOversampleBits();
    	//int rightOversampleBits = rightSonar.m_analog.getOversampleBits();
    	//System.out.println("left sonar oversample = "+ leftOversampleBits + 
    		//	" right sonar oversample = "+ rightOversampleBits);
    //	int leftAverageBits = leftSonar.m_analog.getAverageBits();
    //	int rightAverageBits = rightSonar.m_analog.getAverageBits();
    //	System.out.println("left sonar average = "+ leftAverageBits + 
    	//		" right sonar average = "+ rightAverageBits);
    	
    	//boolean rl = leftRange.get();
    	//System.out.println("left range finder = " + rl);
    	//boolean rr = rightRange.get();
    	//System.out.println("right range finder = " + rr);
    	
    	differentialDrive.tankDrive(curLeft, curRight);
    }
    public void stop() {
    	drive(0, 0);
    }
	public double getAngle(){
    	double angle = ahrs.getAngle();
    	//double angle = gyro.getAngle();
    	return angle;
    }
    public int aI2CRead() {
    	/*
    	String output = aI2C.read() ; 
    	int center = -1;
    	try {
    		
    	
    		center = Integer.parseInt(output);
    	} catch (NumberFormatException nfe) {
    		
    	}
    	return center;
    	*/
    	ArrayList<VisionTarget> vt = ji.getVisionTargets();
    	if (vt != null) {
    		VisionTarget t = vt.get(0);
    		int center = t.x;
    		return center;
    	} else {
    		return -1;
    	}
    }
    
	public VisionTarget getVisionTarget() {
        ArrayList<VisionTarget> vt = ji.getVisionTargets();
        if (vt != null) {
            return vt.get(0);
        } else {
            return null;
        }
    }

	public double getLeftSonar() {
    	// return leftSonar.getDistance();
		return 0;
    } 
    public double getRightSonar() {
    	// return rightSonar.getDistance();
		return 0;
    }
    public boolean getLeftRange() {
    	// return leftRange.get();
		return false;
    }
    public boolean getRightRange() {
    	// return rightRange.get();
		return false;
    }
    public boolean getLeftLine() {
    	// return !leftLine.get();
		return false;
    }
    public boolean getCenterLine() {
    	// return !centerLine.get();
		return false;
    }
    public boolean getRightLine() {
    	// return !rightLine.get();
		return false;    
    }
}

