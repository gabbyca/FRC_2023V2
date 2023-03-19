// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  // Subsystems
  DriveSubsystem m_robotDrive = new DriveSubsystem();

  // Commands
  

  // Triggers
  Trigger yButton = new JoystickButton(m_driverController, XboxController.Button.kY.value); //swerve: setX()
  Trigger xButton = new JoystickButton(m_driverController, XboxController.Button.kX.value);
  Trigger aButton = new JoystickButton(m_driverController, XboxController.Button.kA.value); //swerve: zeroheading

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
   
  }

  // public Command getAutonomousCommand() {}

}
