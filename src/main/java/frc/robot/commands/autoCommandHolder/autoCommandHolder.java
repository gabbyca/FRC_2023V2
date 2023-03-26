package frc.robot.commands.AutoCommandHolder;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ExtensionSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class AutoCommandHolder extends CommandBase {

    private final WristSubsystem wrist;
    private final ArmSubsystem shoulder;
    private final IntakeSubsystem intake; 
    private final ExtensionSubsystem extension; 

    public AutoCommandHolder(ArmSubsystem shoulder, WristSubsystem wrist, IntakeSubsystem intake, ExtensionSubsystem extension){
        this.shoulder = shoulder;
        this.wrist = wrist; 
        this.intake = intake; 
        this.extension = extension; 
    }

    //HighScore
    public SequentialCommandGroup auto1(){
        return new SequentialCommandGroup( new MoveWrist(wrist, 0), new MoveArm(shoulder, 37.5), new moveExtension(extension, -274), new MoveWrist(wrist, -.8), new Intake(intake, 0.3, false), new MoveWrist(wrist, 0),  new moveExtension(extension, 0), new MoveArm(shoulder, 0), new Intake(intake, 0, true), new MoveWrist(wrist, 0));
    }

    //MiddleScore
    public SequentialCommandGroup auto2(){
        return new SequentialCommandGroup(new MoveWrist(wrist, 0), new MoveArm(shoulder, 31.4), new moveExtension(extension, -56.8), new MoveWrist(wrist, -.7), new Intake(intake, 0.3, false), new MoveWrist(wrist, 0),  new moveExtension(extension, 0), new MoveArm(shoulder, 0),new MoveWrist(wrist, 0),  new Intake(intake, 0, true));
    }

}