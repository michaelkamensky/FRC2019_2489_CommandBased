package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnAngle extends Command {
    private double m_power;
    private double m_angle;
    private double m_target;
    
    public DriveTurnAngle(double power, double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_power = power;
        m_angle = angle;
        requires(Robot.driveTrain);
    }
    private double power() {
    	double power = m_power;
    	double angle = Robot.driveTrain.getAngle();
    	double diff = m_target - angle;
    	if(diff < 0) {
    		diff = -diff;
    	}
    	if(diff < 45) {
    		if(m_power > 0) {
    			power = 0.4;
    		} else {
    			power = -0.4;
    		}
    	}
    	return power;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	m_target = Robot.driveTrain.getAngle()+ m_angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double p = power();
    	Robot.driveTrain.drive(p, -p);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double angle = Robot.driveTrain.getAngle();
    	if(m_angle > 0) {
    		if(angle > m_target) {
    			return true;
    		}

    	} else { 
    		if(angle < m_target) {
    			return true;
    		}

    	}
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
