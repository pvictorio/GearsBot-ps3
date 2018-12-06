package org.usfirst.frc.team2890.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The main autonomous command to pickup and deliver the soda to the box.
 */
public class Autonomous extends CommandGroup {
	public Autonomous() {
		addParallel(new SetElevatorSetpoint(0.3));	// 11/10/2018 - top
		addParallel(new SetWristSetpoint(0));		// 11/10/2018
		addSequential(new OpenClaw());				// 11/10/2018
		addSequential(new SetElevatorSetpoint(0));	// 11/10/2018 - bottom
		
//		addSequential(new PrepareToPickup());
//		addSequential(new Pickup());
//		addSequential(new SetDistanceToBox(0.10));
//		 addSequential(new DriveStraight(4)); // Use Encoders if ultrasonic is broken
//		addSequential(new Place());
//		addSequential(new SetDistanceToBox(0.60));
//		 addSequential(new DriveStraight(-2)); 		// Use Encoders if ultrasonic is broken
		addSequential(new SetWristSetpoint(-45));		// Raise Wrist
		addSequential(new SetWristSetpoint(0));  	// Wrist horizontal - 11/12/2018
		
		addSequential(new CloseClaw());
//		addSequential(new OpenClaw());				// 11/10/2018
		addSequential(new DriveStraight(2));		// 11/10/2018-pv

//		addSequential(new SetElevatorSetpoint(0.5));	// 11/10/2018 - up
	}
}
