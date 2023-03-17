package frc.robot.commands.scoreCommands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autoCommandHolder.moveWrist;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShoulderSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class ScoreCommandHolder extends CommandBase {

    private final WristSubsystem wrist;
    private final ShoulderSubsystem shoulder;
    private final IntakeSubsystem intake; 

    public ScoreCommandHolder(ShoulderSubsystem shoulder, WristSubsystem wrist, IntakeSubsystem intake){
        this.shoulder = shoulder;
        this.wrist = wrist; 
        this.intake = intake; 
    }

    public SequentialCommandGroup scoreConeLow(){
        return new SequentialCommandGroup(new MoveShoulder(shoulder, 1000), new moveWrist(wrist, 10000));
    }

    public SequentialCommandGroup scoreConeMiddle(){
        return new SequentialCommandGroup(new MoveShoulder(shoulder, 1000), new moveWrist(wrist, 10000));
    }

    public SequentialCommandGroup scoreConeHigh(){
        return new SequentialCommandGroup(new MoveShoulder(shoulder, 1000), new moveWrist(wrist, 10000));
    }

    public SequentialCommandGroup scoreCubeLow(){
        return new SequentialCommandGroup(new MoveShoulder(shoulder, 1000), new moveWrist(wrist, 10000));
    }

    public SequentialCommandGroup scoreCubeMiddle(){
        return new SequentialCommandGroup(new MoveShoulder(shoulder, 1000), new moveWrist(wrist, 10000));
    }

    public SequentialCommandGroup scoreCubeHigh(){
        return new SequentialCommandGroup(new MoveShoulder(shoulder, 1000), new moveWrist(wrist, 10000));
    }

    public SequentialCommandGroup scoreCone(){
        return new SequentialCommandGroup(new Intake(intake, 0.6, true), new MoveShoulder(shoulder, 1000), new moveWrist(wrist, 10000), new Intake(intake, 0.2, false));
    }

    public SequentialCommandGroup getHumanPlayerGround(){
        return new SequentialCommandGroup(new Intake(intake, 1, true), new MoveShoulder(shoulder, 1000), new moveWrist(wrist, 10000), new Intake(intake, 0.2, false));
    }

    public SequentialCommandGroup getHumanPlayerShelf(){
        return new SequentialCommandGroup();
    }
    
    public SequentialCommandGroup getGround(){
        return new SequentialCommandGroup();
    }
}