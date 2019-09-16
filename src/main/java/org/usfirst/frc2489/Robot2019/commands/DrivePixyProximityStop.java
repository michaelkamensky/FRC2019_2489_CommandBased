package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePixyProximityStop extends Command {
	private double m_forward;
    private double m_power;
    private int m_error;
	private boolean m_rl ;
	private boolean m_rr;
    
    
    // x range from 0 to 315 default center 158
    private final int center_position = 200;


    public DrivePixyProximityStop(double forward, double power, int error ) {
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
    	m_rl = Robot.driveTrain.getLeftRange();
    	m_rr = Robot.driveTrain.getRightRange();
    	if(m_rl && m_rr ) {
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
    	} else {
    		if (m_rl == false) {
    			Robot.driveTrain.drive(0 , m_forward);	
    		}else {
    			Robot.driveTrain.drive(m_forward, 0);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if((m_rl == false) && (m_rr == false) ) {
    		return true;
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
