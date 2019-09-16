package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Punch extends Command {
	
    public Punch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumatics.setSolenoid1(DoubleSolenoid.Value.kReverse);
    	//System.out.println("This works");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean ret = Robot.pneumatics.getLimitSwitch();
        System.out.println("limit switch value " + Robot.pneumatics.getLimitSwitch());
        if(ret == false) {
        	Robot.pneumatics.setSolenoid1(DoubleSolenoid.Value.kForward);
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
