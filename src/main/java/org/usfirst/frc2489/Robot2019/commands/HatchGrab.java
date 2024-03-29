package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HatchGrab extends Command {
    public HatchGrab() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.hatchGrabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hatchGrabber.grab();
    }


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
