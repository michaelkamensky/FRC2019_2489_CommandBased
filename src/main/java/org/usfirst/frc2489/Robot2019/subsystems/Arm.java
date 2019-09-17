package org.usfirst.frc2489.Robot2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc2489.Robot2019.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import org.usfirst.frc2489.Robot2019.commands.*;

/**
 *
 */
public class Arm extends Subsystem {
	   private final WPI_TalonSRX armTalonSRX = RobotMap.armTalonSRX;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new ArmWithJoystick());
        
    	
    }
    public void move(double power){
    	armTalonSRX.set(ControlMode.PercentOutput, power);  	
    }
}
