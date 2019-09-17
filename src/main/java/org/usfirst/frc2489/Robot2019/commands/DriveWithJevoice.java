package org.usfirst.frc2489.Robot2019.commands;

import org.usfirst.frc2489.Robot2019.Robot;
import org.usfirst.frc2489.Robot2019.JeVoisInterface;
import org.usfirst.frc2489.Robot2019.VisionTarget;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJevoice extends Command {
    private double m_power;
    private double m_offset;
    private int m_error;

    // private final double visionPower = 0.25; // 0.25 0.35
    // private final double visionTurnOffset = 0.08; // 0.05 0.08
    // private final int visionRobotCenterError = 3 * JeVoisInterface.VIDEO_SCALE;

    // target
    private final int visionRobotCenterPosition = 185 * JeVoisInterface.VIDEO_SCALE; // 200//230 // 185  
    private final int visionRobotHorizontalCenter = 120 * JeVoisInterface.VIDEO_SCALE;
    private final int visionRobotHorizontalCenterError = 10 * JeVoisInterface.VIDEO_SCALE;


    public DriveWithJevoice(double power, double offset, int error) {
        m_power = power;
        m_offset = offset;
        m_error = error;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
    }
    private final double maxDistance = 110;
    private final double minDistance = 60;
    private final double gain = 0.3; //0.5 //0.4

    private double getSpeedRate(double distance){
        double ret = 1.0;
        if (distance > maxDistance){
            ret += gain;
        }else if (distance > minDistance){
            ret += ((distance - minDistance) / (maxDistance - minDistance)) * gain;
        } 
        
        return ret;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        VisionTarget vt = null;

        if (vt != null) {
            int center = vt.x;
            double distance = vt.distanceInches();
            double angle = vt.getAngle();
            double speedRate = getSpeedRate(distance);

            if (center < (visionRobotCenterPosition - m_error)) {
                    double error = visionRobotCenterPosition - center;
                    double offset = m_offset + 
                        3 * m_offset * (error / (JeVoisInterface.STREAM_WIDTH_PX * JeVoisInterface.VIDEO_SCALE) / 2);
                        Robot.driveTrain.drive((m_power - offset) * speedRate, (m_power + offset) * speedRate);
            } else if (center > (visionRobotCenterPosition + m_error)) {
                    double error = center - visionRobotCenterPosition;
                    double offset = m_offset + 
                        3 * m_offset * (error / (JeVoisInterface.STREAM_WIDTH_PX * JeVoisInterface.VIDEO_SCALE) / 2);
                        Robot.driveTrain.drive((m_power + offset) * speedRate, (m_power - offset) * speedRate);
            } else {
                    // we are right on target, drive forward
                    Robot.driveTrain.drive(m_power * speedRate, m_power * speedRate);
            }
            /*
            if (vtilt != null) {
                int vcenter = vt.y;
                if (vcenter > visionRobotHorizontalCenter + visionRobotHorizontalCenterError) {
                    vtilt.down();
                } else if (vcenter < visionRobotHorizontalCenter - visionRobotHorizontalCenterError) {
                    vtilt.up();
                }
            }
            */
        } else {
            // stop we are too close
            Robot.driveTrain.drive(0, 0);
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
