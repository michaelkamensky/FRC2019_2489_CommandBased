package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnAngle2 extends Command {
    private double m_power;
    private double m_angle;
    private double m_target;
    
    private final double minimal_speed = 0.5;
    private final double threshold_slowdown = 90;
    
    
    public DriveTurnAngle2(double power, double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_power = -power;
        m_angle = angle;
        requires(Robot.driveTrain);
    }

    private double power() {
    	double power = m_power;
    	double angle = Robot.driveTrain.getAngle();
    	double diff = m_target - angle;
    	double power_abs = m_power;
    	if(power_abs < 0) {
    		power_abs = -power_abs;
    	} 
    	if(diff < 0) {
    		diff = -diff;
    	}
    	if(diff < threshold_slowdown) {
    		power = minimal_speed + (diff*(power_abs -minimal_speed)*(power_abs -minimal_speed)/threshold_slowdown);
    		if(m_power < 0) {
    			power = -power;
    		} 
    			
    		
    	}
    	return power;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	double current = Robot.driveTrain.getAngle();
    	m_target = current + m_angle;
    	System.out.println("intialize angle = " + current);
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
    			System.out.println("isfinished angle = " + angle + " , diff = " + (angle - m_target));
    			
    			return true;
    			
    		}

    	} else { 
    		if(angle < m_target) {
    			System.out.println("isfinished angle = " + angle + " , diff = " + (m_target - angle));
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
