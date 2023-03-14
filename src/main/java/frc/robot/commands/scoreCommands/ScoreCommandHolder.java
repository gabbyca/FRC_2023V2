package frc.robot.commands.scoreCommands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ShoulderSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class ScoreCommandHolder extends CommandBase {

    private final WristSubsystem wrist;
    private final ShoulderSubsystem shoulder;

    public ScoreCommandHolder(ShoulderSubsystem shoulder, WristSubsystem wrist){
        this.shoulder = shoulder;
        this.wrist = wrist; 
    }
   
    public SequentialCommandGroup scoreConeMiddle(){
        return new SequentialCommandGroup();
    }
}