package org.usfirst.frc2489.Robot2019.subsystems;

import org.usfirst.frc2489.Robot2019.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	 private final Compressor compressor = RobotMap.pneumaticsCompressor;
	 private final DoubleSolenoid doubleSolenoid1 = RobotMap.pneumaticsDoubleSolenoid1;
	 private final DigitalInput limitSwitch1 = RobotMap.pneumaticsLimitSwitch1;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    public void setSolenoid1(DoubleSolenoid.Value v){
    	doubleSolenoid1.set(v);
    	
    }
    public boolean getLimitSwitch() {
    	return limitSwitch1.get();
    }
}


