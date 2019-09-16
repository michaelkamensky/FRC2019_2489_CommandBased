package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFollowLine extends Command {
	
    private double m_power;
    private double m_adjustment;
    // 0 forward -1 left, 1 right directions 
    private int m_search = 0;

    
    
    public DriveFollowLine(double power, double adjustment, int search) {
        m_power = -power;
        m_adjustment = adjustment; 
        m_search = search; 
        
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if(Robot.driveTrain.getCenterLine()) {
    		Robot.driveTrain.drive(m_power, m_power);
    	} else {
    		if(Robot.driveTrain.getLeftLine()) {
    			Robot.driveTrain.drive(m_power + m_adjustment, m_power - m_adjustment);
    		} else {
    			if(Robot.driveTrain.getRightLine()) {
    				Robot.driveTrain.drive(m_power - m_adjustment, m_power + m_adjustment);
    			} else {
    				Robot.driveTrain.drive(m_power - m_search * m_adjustment,
    						m_power + m_search * m_adjustment);
    			}
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	boolean rl = Robot.driveTrain.getLeftRange();
    	boolean rr = Robot.driveTrain.getRightRange();
    	if((rl == false) && (rr == false) ) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	end();
    }
}
