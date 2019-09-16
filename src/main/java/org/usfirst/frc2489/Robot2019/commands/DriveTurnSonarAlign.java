package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnSonarAlign extends Command {
	double m_power;
	double m_error;
	double m_leftSonarD;
	double m_rightSonarD;
	

    public DriveTurnSonarAlign(double power, double error) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_power = power;
    	m_error = error;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_leftSonarD = Robot.driveTrain.getLeftSonar();
    	m_rightSonarD = Robot.driveTrain.getRightSonar(); 
    	if (m_leftSonarD > m_rightSonarD) {
    		
    		Robot.driveTrain.drive(m_power, -m_power);  
    	} else {
    		Robot.driveTrain.drive(-m_power, m_power); 
    	}    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double diff = m_leftSonarD - m_rightSonarD ;
    	if (diff < 0) {
    		diff = - diff;
    	}
    	if (diff <= m_error) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
