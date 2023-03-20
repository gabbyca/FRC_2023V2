package frc.robot.commands.scoreCommands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autoCommandHolder.moveWrist;
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
    public ParallelCommandGroup compactPosition(){
        return new ParallelCommandGroup(new MoveArm(shoulder, 10), new moveWrist(wrist, 10), new Extension(extension, 0));
    }
    public ParallelCommandGroup coneMiddle(){
        return new ParallelCommandGroup(new MoveArm(shoulder, 100), new moveWrist(wrist, 100), new Extension(extension, 100));
    }
    public ParallelCommandGroup coneHigh(){
        return new ParallelCommandGroup(new MoveArm(shoulder, 1000), new moveWrist(wrist, 1000), new Extension(extension, 1000));
    }
    public ParallelCommandGroup cubeMiddle(){
        return new ParallelCommandGroup(new MoveArm(shoulder, 100), new moveWrist(wrist, 100), new Extension(extension, 100));
    }
    public ParallelCommandGroup cubeHigh(){
        return new ParallelCommandGroup(new MoveArm(shoulder, 1000), new moveWrist(wrist, 1000), new Extension(extension, 1000));
    }
    // position to score


    // release cargo
    public SequentialCommandGroup releaseScore(){
        return new SequentialCommandGroup(new Intake(intake, 0.6, true), new Extension(extension, 0), new MoveArm(shoulder, 1), new moveWrist(wrist, 1));
    }
    // release cargo 


    // retrieve cargo
    // public SequentialCommandGroup getHumanPlayerGround(){
    //     return new SequentialCommandGroup(new Extension(extension, 10), new MoveArm(shoulder, 10), new moveWrist(wrist, 10), new Intake(intake, 1, false), new IntakeHold(intake, .1, false));
    // }
    // public SequentialCommandGroup getHumanPlayerShelf(){
    //       return new SequentialCommandGroup(new Extension(extension, 100), new MoveArm(shoulder, 100), new moveWrist(wrist, 100), new Intake(intake, 1, false), new IntakeHold(intake, 0.1, false));
    // }
    // public SequentialCommandGroup getGround(){
    //      return new SequentialCommandGroup(new MoveArm(shoulder, 1), new moveWrist(wrist, 1), new Intake(intake, 1, true), new IntakeHold(intake, 0.1, false));
    // }
    // retrieve cargo

}