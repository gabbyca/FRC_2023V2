package frc.robot.commands.scoreCommands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ExtensionSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class ScoreCommandHolder extends CommandBase {

    private final WristSubsystem wrist;
    private final ArmSubsystem shoulder;
    private final IntakeSubsystem intake; 
    private final ExtensionSubsystem extension; 

    public ScoreCommandHolder(ArmSubsystem shoulder, WristSubsystem wrist, IntakeSubsystem intake, ExtensionSubsystem extension){
        this.shoulder = shoulder;
        this.wrist = wrist; 
        this.intake = intake; 
        this.extension = extension; 
    }

    // position to score
    public SequentialCommandGroup compactPosition(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0),new moveExtension(extension, 0), new MoveArm(shoulder, 0), new Intake(intake, 0.05, true));
    }
    public SequentialCommandGroup coneMiddle(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 34), new moveExtension(extension, -56.8), new MoveWrist(wrist, -.7));
    }
    public SequentialCommandGroup coneHigh(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 40), new moveExtension(extension, -274), new MoveWrist(wrist, -.8));
    }
    public SequentialCommandGroup cubeMiddle(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 29), new moveExtension(extension, -0.5), new MoveWrist(wrist, -.8));
    }
    public SequentialCommandGroup cubeHigh(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 37), new moveExtension(extension, -200), new MoveWrist(wrist, -0.9));
    }
   
    // position to score

    // release cargo
    public SequentialCommandGroup releaseScore(){
        return new SequentialCommandGroup( new Intake(intake, 0.3, false), new MoveWrist(wrist, 0),  new moveExtension(extension, 0), new MoveArm(shoulder, 0), new Intake(intake, 0, true));
    }
    // release cargo 


    // retrieve cargo
    public SequentialCommandGroup getHumanPlayerGround(){
         return new SequentialCommandGroup(new Intake(intake, 0.5, true), new MoveArm(shoulder, 24), new MoveWrist(wrist, -0.5));
    }
    public SequentialCommandGroup getHumanPlayerShelf(){
          return new SequentialCommandGroup(new Intake(intake, 0.5, true), new MoveArm(shoulder, 24), new MoveWrist(wrist, -.08));
    }
    public SequentialCommandGroup getGround(){
         return new SequentialCommandGroup(new Intake(intake, 0.5, true), new MoveArm(shoulder, 6),new moveExtension(extension, -7), new MoveWrist(wrist, -0.38));
    }
    // retrieve cargo

}