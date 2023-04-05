// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.NavigateToTarget;
import frc.robot.commands.RunIntakeCommand;
import frc.robot.commands.WristDown;
import frc.robot.commands.WristUp;
import frc.robot.commands.AutoCommandHolder.AutoCommandHolder;
import frc.robot.commands.scoreCommands.ScoreCommandHolder;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExtensionSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.WristSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  CommandXboxController m_driverController= new CommandXboxController(0); 
  CommandXboxController m_coDriverController= new CommandXboxController(1); 


  SendableChooser<Command> AutoChooser = new SendableChooser<>();

  // Subsystems
  DriveSubsystem m_robotDrive = new DriveSubsystem();
  ArmSubsystem ArmSubsystem = new ArmSubsystem(); 
  WristSubsystem WristSubsystem = new WristSubsystem(); 
  IntakeSubsystem IntakeSubsystem = new IntakeSubsystem(); 
  ExtensionSubsystem ExtensionSubsystem = new ExtensionSubsystem(); 
 
  // Commands
  ScoreCommandHolder commands = new ScoreCommandHolder(ArmSubsystem, WristSubsystem, IntakeSubsystem, ExtensionSubsystem); 
  WristDown wristDown = new WristDown(WristSubsystem); 
  WristUp wristUp = new WristUp(WristSubsystem); 
  NavigateToTarget navigateToTarget = new NavigateToTarget(m_robotDrive); 

  // Triggers
  Trigger yButton = m_driverController.y();
  Trigger xButton = m_driverController.x();
  Trigger bButton = m_driverController.b(); 
  Trigger aButton = m_driverController.a();
  Trigger lBumper = m_driverController.leftBumper();
  Trigger rBumper = m_driverController.rightBumper();
  Trigger leftStick = m_driverController.leftStick(); 
  Trigger rightStick = m_driverController.rightStick(); 
  Trigger lShoulder = m_driverController.leftTrigger(); 
  Trigger rShoulder = m_driverController.rightTrigger(); 
  Trigger dPadLeft = m_driverController.povLeft(); 
  Trigger dPadRight = m_driverController.povRight(); 
  Trigger dPadUp = m_driverController.povUp(); 
  Trigger dPadDown = m_driverController.povDown(); 

  
  Trigger yButtonco = m_coDriverController.y();
  Trigger xButtonco = m_coDriverController.x();
  Trigger bButtonco = m_coDriverController.b(); 
  Trigger aButtonco = m_coDriverController.a();
  Trigger lBumperco = m_coDriverController.leftBumper();
  Trigger rBumperco = m_coDriverController.rightBumper();
  Trigger leftStickco = m_coDriverController.leftStick(); 
  Trigger rightStickco = m_coDriverController.rightStick(); 
  Trigger lShoulderco = m_coDriverController.leftTrigger(); 
  Trigger rShoulderco = m_coDriverController.rightTrigger();
  Trigger dPadLeftco = m_coDriverController.povLeft(); 
  Trigger dPadRightco = m_coDriverController.povRight(); 
  Trigger dPadUpco = m_coDriverController.povUp(); 
  Trigger dPadDownco = m_coDriverController.povDown(); 

  
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
    setAutoCommands();
    SmartDashboard.putData("Autos", AutoChooser);
    IntakeSubsystem.setDefaultCommand(new RunIntakeCommand(IntakeSubsystem, 0.3, false));
  }

  private void configureButtonBindings() {
    
    //DRIVER
    bButton.onTrue(commands.coneMiddle()); 
    yButton.onTrue(commands.coneHigh());
    aButton.onTrue(commands.cubeMiddle());
    xButton.onTrue(commands.cubeHigh());
    leftStick.onTrue(commands.getHumanPlayerGround());
    rightStick.onTrue(commands.getHumanPlayerShelf());
    lBumper.onTrue(commands.releaseScore());
    lShoulder.onTrue(commands.getGround());
    rShoulder.onTrue(commands.compactPosition());
    
    //CO-DRIVER
    dPadUpco.onTrue(wristUp);
    dPadDownco.onTrue(wristDown);
    lBumperco.toggleOnTrue(new RunCommand(() -> m_robotDrive.setX(), m_robotDrive)); 
    rBumperco.onTrue(new InstantCommand(m_robotDrive::zeroHeading));
    aButtonco.onTrue(navigateToTarget);

  }

  public void setAutoCommands(){
    AutoCommandHolder autos = new AutoCommandHolder(ArmSubsystem, WristSubsystem, IntakeSubsystem, ExtensionSubsystem, m_robotDrive); 
    AutoChooser.addOption("Cone High", autos.auto1());
    AutoChooser.addOption("Cone Middle", autos.auto2());
  }

  public Command getAutonomousCommand() {
    return AutoChooser.getSelected(); 
  }
   
}

