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
        return new SequentialCommandGroup(new MoveWrist(wrist, 0),new moveExtension(extension, 0), new MoveArm(shoulder, 0));
    }
    public SequentialCommandGroup coneMiddle(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 34.0), new moveExtension(extension, -370), new MoveWrist(wrist, -1));
    }
    public SequentialCommandGroup coneHigh(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 37.0), new moveExtension(extension, -340), new MoveWrist(wrist, -1));
    }

    public SequentialCommandGroup cubeMiddle(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 34.0), new moveExtension(extension, 0), new MoveWrist(wrist, -1));
    }
    public SequentialCommandGroup cubeHigh(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 37.0), new moveExtension(extension, 0), new MoveWrist(wrist, -1));
    }
   
    // position to score

    // release cargo
    // public SequentialCommandGroup releaseScore(){
    //     return new SequentialCommandGroup( new Intake(intake, 0.6, true),  new MoveWrist(wrist, 0),  new moveExtension(extension, 0), new MoveArm(shoulder, 0));
    // }
    public SequentialCommandGroup releaseScore(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0),  new moveExtension(extension, 0), new MoveArm(shoulder, 0));
    }
    // release cargo 


    // retrieve cargo
    public SequentialCommandGroup getHumanPlayerGround(){
        return new SequentialCommandGroup(new moveExtension(extension, -370), new MoveArm(shoulder, 34), new MoveWrist(wrist, 0), new Intake(intake, 1, false), new MoveWrist(wrist, 0),new moveExtension(extension, 0), new MoveArm(shoulder, 0));
    }
    public SequentialCommandGroup getHumanPlayerShelf(){
          return new SequentialCommandGroup(new moveExtension(extension, -370), new MoveArm(shoulder, 34), new MoveWrist(wrist, 0), new Intake(intake, 1, false), new MoveWrist(wrist, 0),new moveExtension(extension, 0), new MoveArm(shoulder, 0));
    }
    public SequentialCommandGroup getGround(){
         return new SequentialCommandGroup(new MoveArm(shoulder, 34), new MoveWrist(wrist, 1), new Intake(intake, 1, true), new MoveWrist(wrist, 0),new moveExtension(extension, 0), new MoveArm(shoulder, 0));
    }
    // retrieve cargo

}