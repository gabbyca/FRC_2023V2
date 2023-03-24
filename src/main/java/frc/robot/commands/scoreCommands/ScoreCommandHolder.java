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
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 34.0), new moveExtension(extension, -370), new MoveWrist(wrist, -1));
    }
    // public ParallelCommandGroup cubeMiddle(){
    //     return new ParallelCommandGroup(new MoveArm(shoulder, 100), new MoveWrist(wrist, 100), new moveExtension(extension, 100));
    // }
    // public ParallelCommandGroup cubeHigh(){
    //     return new ParallelCommandGroup(new MoveArm(shoulder, 1000), new MoveWrist(wrist, 1000), new moveExtension(extension, 1000));
    // }
    // position to score


    // release cargo
    public SequentialCommandGroup releaseScore(){
        return new SequentialCommandGroup(new Intake(intake, 0.6, true), new moveExtension(extension, 0), new MoveArm(shoulder, 1), new MoveWrist(wrist, 1));
    }
    // release cargo 


    // retrieve cargo
    public SequentialCommandGroup getHumanPlayerGround(){
        return new SequentialCommandGroup(new moveExtension(extension, 10), new MoveArm(shoulder, 10), new MoveWrist(wrist, 10), new Intake(intake, 1, false));
    }
    public SequentialCommandGroup getHumanPlayerShelf(){
          return new SequentialCommandGroup(new moveExtension(extension, 100), new MoveArm(shoulder, 100), new MoveWrist(wrist, 100), new Intake(intake, 1, false));
    }
    public SequentialCommandGroup getGround(){
         return new SequentialCommandGroup(new MoveArm(shoulder, 1), new MoveWrist(wrist, 1), new Intake(intake, 1, true));
    }
    // retrieve cargo

}