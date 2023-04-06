package frc.robot.commands.scoreCommands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ExtensionSubsystem;
import frc.robot.subsystems.WristSubsystem;

import static edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior.kCancelSelf;

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
    public SequentialCommandGroup compactPosition() {
        return new SequentialCommandGroup(
                new MoveWrist(wrist, -0.2),
                new moveExtension(extension, 0),
                new MoveArm(shoulder, 0));
    }

     //good
     public SequentialCommandGroup coneMiddle() {
        return new SequentialCommandGroup(
                new MoveWrist(wrist, -0.2),
                new MoveArm(shoulder, 31),
                new moveExtension(extension, -49),
                new MoveWrist(wrist, -.6));
    }

    //good
    public SequentialCommandGroup coneHigh() {
        return new SequentialCommandGroup(
                new MoveWrist(wrist, -0.2),
                new MoveArm(shoulder, 39),
                new moveExtension(extension, -187.3),
                new MoveWrist(wrist, -0.7));
    }

    public SequentialCommandGroup cubeMiddle() {
        return new SequentialCommandGroup(
                new MoveWrist(wrist, 0),
                new MoveArm(shoulder, 29),
                new moveExtension(extension, -0.5),
                new MoveWrist(wrist, -.8));
    }
    public SequentialCommandGroup cubeHigh() {
       return new SequentialCommandGroup(
                new MoveWrist(wrist, -0.2),
                new MoveArm(shoulder, 36),
                new moveExtension(extension, -187.3),
                new MoveWrist(wrist, -0.7));
    }
   
    // position to score

    // release cargo
    public SequentialCommandGroup releaseScore() {
        return new SequentialCommandGroup(
                new Outtake(intake, 0.6, true),
                new MoveWrist(wrist, -.2),
                new moveExtension(extension, 0),
                new MoveArm(shoulder, 0));

    }
    // release cargo 

    // retrieve cargo
    public SequentialCommandGroup getHumanPlayerGround() {
         return new SequentialCommandGroup(
                 new MoveArm(shoulder, 27.93),
                 new MoveWrist(wrist, -0.6),
                 new Intake(intake, 0.5)
                 .withInterruptBehavior(kCancelSelf));
    }
    public SequentialCommandGroup getHumanPlayerShelf() {
          return new SequentialCommandGroup(
                  new MoveArm(shoulder, 39),
                  new MoveWrist(wrist, -.0983),
                  new Intake(intake, 0.5)
                  .withInterruptBehavior(kCancelSelf), compactPosition());
    }
    public SequentialCommandGroup getGround() {
         return new SequentialCommandGroup(
                 new MoveArm(shoulder, 6),
                 new moveExtension(extension, -18.5),
                 new MoveWrist(wrist, -0.5),
                 new Intake(intake, 0.7)
                 .withInterruptBehavior(kCancelSelf), compactPosition());
    }
    // retrieve cargo
}