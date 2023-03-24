// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.moveExtend;
import frc.robot.commands.moveInExtend;
import frc.robot.commands.moveIntake;
import frc.robot.commands.stopExtension;
import frc.robot.commands.scoreCommands.ScoreCommandHolder;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExtensionSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.WristSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  CommandXboxController m_driverController= new CommandXboxController(0); 
  CommandXboxController m_coDriverController= new CommandXboxController(1); 

  // Subsystems
  DriveSubsystem m_robotDrive = new DriveSubsystem();
  ArmSubsystem ArmSubsystem = new ArmSubsystem(); 
  WristSubsystem WristSubsystem = new WristSubsystem(); 
  IntakeSubsystem IntakeSubsystem = new IntakeSubsystem(); 
  ExtensionSubsystem ExtensionSubsystem = new ExtensionSubsystem(); 
 

  // Commands
  ScoreCommandHolder commands = new ScoreCommandHolder(ArmSubsystem, WristSubsystem, IntakeSubsystem, ExtensionSubsystem); 

  // Triggers
  Trigger yButton = m_driverController.y();
  Trigger xButton = m_driverController.x();
  Trigger aButton = m_driverController.a();
  Trigger lBumper = m_driverController.leftBumper();
  Trigger rBumper = m_driverController.rightBumper();
  Trigger leftStick = m_driverController.leftStick(); 
  Trigger rightStick = m_driverController.rightStick(); 
  Trigger lShoulder = m_driverController.leftTrigger(); 

  
  Trigger dPadLeftco = m_coDriverController.povLeft(); 
  Trigger dPadRightco = m_coDriverController.povRight(); 
  Trigger aButtonco = m_coDriverController.a();
  Trigger bButtonco = m_coDriverController.b();
  Trigger yButtonco = m_coDriverController.y();
  Trigger xButtonco = m_coDriverController.x();
  

// testing section
  moveInExtend in = new moveInExtend(ExtensionSubsystem); 
  moveExtend out = new moveExtend(ExtensionSubsystem); 
  stopExtension stop = new stopExtension(ExtensionSubsystem); 
  Trigger dUp = m_driverController.povUp(); 
  Trigger dDown = m_driverController.povDown(); 
  Trigger bButton = m_driverController.b(); 
  moveIntake manIntake = new moveIntake(IntakeSubsystem); 
// testing section

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
    //DRIVER
    bButton.onTrue(commands.coneMiddle()); 
    yButton.onTrue(commands.coneHigh());
    aButton.onTrue(commands.cubeMiddle());
    xButton.onTrue(commands.cubeHigh());
    leftStick.onTrue(commands.getHumanPlayerShelf());
    rightStick.onTrue(commands.getHumanPlayerGround());
    lBumper.onTrue(commands.releaseScore());
    lShoulder.onTrue(commands.getGround());
    //right rightbumber fast/slow


    //CO-DRIVER
    dPadLeftco.toggleOnTrue(new RunCommand(() -> m_robotDrive.setX(), m_robotDrive)); 
    dPadRightco.onTrue(new InstantCommand(m_robotDrive::zeroHeading));
    aButtonco.onTrue(commands.compactPosition());


    //testing section
    dUp.whileTrue(out); 
    dDown.whileTrue(in); 
    bButton.whileTrue(stop); 
    // aButtonCo.onTrue(commands.testArm()); 
    // bButtonco.onTrue(manIntake); 
    yButtonco.onTrue(commands.coneMiddle());
    // xButton.onTrue(commands.coneMiddle());
    // aButtonCo.onTrue(commands.compactPosition());
   //testing section
    
  }
  //public Command getAutonomousCommand() {}
   
  }

