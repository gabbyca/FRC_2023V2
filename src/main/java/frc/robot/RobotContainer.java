// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SpeedCommand;
import frc.robot.commands.scoreCommands.ScoreCommandHolder;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExtensionSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.WristSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  XboxController m_driverController = new XboxController(0);
  XboxController m_coDriverController = new XboxController(1);

  // Subsystems
  DriveSubsystem m_robotDrive = new DriveSubsystem();
  ArmSubsystem ArmSubsystem = new ArmSubsystem(); 
  WristSubsystem WristSubsystem = new WristSubsystem(); 
  IntakeSubsystem IntakeSubsystem = new IntakeSubsystem(); 
  ExtensionSubsystem ExtensionSubsystem = new ExtensionSubsystem(); 
 

  // Commands
  ScoreCommandHolder commands = new ScoreCommandHolder(ArmSubsystem, WristSubsystem, IntakeSubsystem, ExtensionSubsystem); 
  SpeedCommand slow = new SpeedCommand(2.0); 
  SpeedCommand fast = new SpeedCommand(4.8); 

  // Triggers
  Trigger yButton = new JoystickButton(m_driverController, XboxController.Button.kY.value); //swerve: setX()
  Trigger aButton = new JoystickButton(m_driverController, XboxController.Button.kA.value); //swerve: zeroheading
  Trigger lBumper = new JoystickButton(m_driverController, XboxController.Button.kLeftBumper.value); //slow speed
  Trigger rBumper = new JoystickButton(m_driverController, XboxController.Button.kRightBumper.value); //fast speed

  Trigger aButtonCo = new JoystickButton(m_coDriverController, XboxController.Button.kA.value); //
  Trigger bButtonCo = new JoystickButton(m_coDriverController, XboxController.Button.kB.value); //
  Trigger yButtonCo = new JoystickButton(m_coDriverController, XboxController.Button.kY.value); //
  Trigger xButtonCo = new JoystickButton(m_coDriverController, XboxController.Button.kX.value); //
  

  public RobotContainer() {
    configureButtonBindings();

    m_robotDrive.setDefaultCommand(
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            m_robotDrive));
  }

  private void configureButtonBindings() {
    yButton.toggleOnTrue(new RunCommand(() -> m_robotDrive.setX(), m_robotDrive));
    aButton.onTrue(new InstantCommand(m_robotDrive::zeroHeading));
    lBumper.onTrue(slow); 
    rBumper.onTrue(fast); 

    // aButtonCo.onTrue(commands.testArm());
    // bButtonCo.onTrue(commands.testWrist());
    // yButtonCo.onTrue(commands.testExtension());
  
   
  }

  // public Command getAutonomousCommand() {}

}
