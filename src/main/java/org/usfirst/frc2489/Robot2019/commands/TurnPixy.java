package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnPixy extends Command {
    private double m_power;
    private int m_error;


    public TurnPixy(double power, int error) {
        m_power = power;
        m_error = error;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
 
    	int center = Robot.driveTrain.aI2CRead();
    	//System.out.println(center);
    	if (center == -1) {
    		Robot.driveTrain.drive(0, 0); 
    	}else {
    		if (center > 320 + m_error) {
    			Robot.driveTrain.drive(-m_power, m_power); 
    		}else if(center < 320 - m_error) {
    			Robot.driveTrain.drive(m_power, -m_power); 
    		}
    		else {
    			Robot.driveTrain.drive(0, 0); 	
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
