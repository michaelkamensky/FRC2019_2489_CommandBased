package org.usfirst.frc2489.Robot2019.subsystems;
import org.usfirst.frc2489.Robot2019.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc2489.Robot2019.commands.*;

public class BallDispenser extends Subsystem {

    private DoubleSolenoid ballDispenser = RobotMap.ballDispenserDoubleSolenoid;
    boolean extended = false;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BallRetract());           	
    }

    public void push() {
        ballDispenser.set(DoubleSolenoid.Value.kReverse);
        extended = true;
    }

    public void retract() {
        ballDispenser.set(DoubleSolenoid.Value.kForward);
        extended = false;
    }
}