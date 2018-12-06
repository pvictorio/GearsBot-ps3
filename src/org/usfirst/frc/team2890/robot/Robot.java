package org.usfirst.frc.team2890.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2890.robot.commands.Autonomous;
import org.usfirst.frc.team2890.robot.commands.TankDriveWithJoystick;
import org.usfirst.frc.team2890.robot.subsystems.Claw;
import org.usfirst.frc.team2890.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2890.robot.subsystems.Elevator;
import org.usfirst.frc.team2890.robot.subsystems.Wrist;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autonomousCommand;
	Command tankDrive;

	public static DriveTrain drivetrain;
	public static Elevator elevator;
	public static Wrist wrist;
	public static Claw claw;
	public static OI oi;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.print("robotInit");  // 11/2018
		// Initialize all subsystems
		drivetrain = new DriveTrain();
		elevator = new Elevator();
		wrist = new Wrist();
		claw = new Claw();
		oi = new OI();

		// instantiate the command used for the autonomous period
		autonomousCommand = new Autonomous();
		tankDrive = new TankDriveWithJoystick();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(wrist);
		SmartDashboard.putData(claw);
	}

	@Override
	public void autonomousInit() {
		System.out.println("Autonomous enabled!");	// 11/10/2018
		cancelAutonomous();
//		autonomousCommand = null;					// 11/10/2018
//		autonomousCommand = new Autonomous();		// 11/10/2018				
		autonomousCommand.start(); // schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//System.out.println("autonomousPeriodic");	// 11/10/2018 - lots of msgs. too many.
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		cancelAutonomous();
		System.out.println("Start tankDrive");
		tankDrive.start();
	}

	private void cancelAutonomous() {
		if (autonomousCommand != null) {
			System.out.println("cancelAutonomous");
			autonomousCommand.cancel();
		}
	}
	
	/**
	 * @since 11/10/2018
	 */
	@Override
	public void testInit() {
		cancelAutonomous();
		tankDrive.cancel();
		System.out.println("testInit()");
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
		wrist.log();
		elevator.log();
		drivetrain.log();
		claw.log();
	}
	
	/**
	 * disabledInit
	 * @since 11/10/2018
	 * @author robot
	 */
	@Override
	public void disabledInit() {
		autonomousCommand.cancel();
		System.out.println("disabledInit() method");
	}
}
