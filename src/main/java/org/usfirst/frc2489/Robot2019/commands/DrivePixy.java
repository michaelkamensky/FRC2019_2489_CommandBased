package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePixy extends Command {
	private double m_forward;
    private double m_power;
    private int m_error;
    
    // x range from 0 to 315
    private final int center_position = 320;


    public DrivePixy(double forward, double power, int error) {
    	m_forward = -forward;
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
    		if (center > center_position + m_error) {
    			Robot.driveTrain.drive(m_forward  - m_power,m_forward + m_power); 
    		}else if(center < center_position - m_error) {
    			Robot.driveTrain.drive(m_forward + m_power, m_forward - m_power); 
    		}
    		else {
    			Robot.driveTrain.drive(m_forward, m_forward); 	
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
