package frc.robot.commands.AutoCommandHolder;

import java.util.HashMap;
import java.util.List;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExtensionSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.Constants.DriveConstants;

public class AutoCommandHolder extends CommandBase {

    private final WristSubsystem wrist;
    private final ArmSubsystem shoulder;
    private final IntakeSubsystem intake; 
    private final ExtensionSubsystem extension; 
    private final DriveSubsystem drive; 

    public AutoCommandHolder(ArmSubsystem shoulder, WristSubsystem wrist, IntakeSubsystem intake, ExtensionSubsystem extension, DriveSubsystem drive){
        this.shoulder = shoulder;
        this.wrist = wrist; 
        this.intake = intake; 
        this.extension = extension; 
        this.drive = drive;   
       
    }

    //HighScore
    public SequentialCommandGroup auto1(SwerveAutoBuilder autoBuilder){
        List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup("coneScoreAutoBalance", 
        new PathConstraints(2, 3));
        return new SequentialCommandGroup( 
            new MoveWrist(wrist, 0), 
            new MoveArm(shoulder, 37.5), 
            new moveExtension(extension, -274), 
            new MoveWrist(wrist, -.8), 
            new Intake(intake, 0.3, false), 
            new MoveWrist(wrist, 0),  
            new moveExtension(extension, 0), 
            new MoveArm(shoulder, 0), 
            new Intake(intake, 0, true), 
            new MoveWrist(wrist, -0.2),  
            autoBuilder.fullAuto(pathGroup)); 
    }

    // HighScore
     public SequentialCommandGroup auto3(SwerveAutoBuilder autoBuilder){
        List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup("redConeScoreAutoBalance", 
        new PathConstraints(2, 3));
        return new SequentialCommandGroup( 
           coneHigh(), 
            new MoveWrist(wrist, 0),  
            new moveExtension(extension, 0), 
            new MoveArm(shoulder, 0), 
            new Intake(intake, 0, true), 
            new MoveWrist(wrist, -0.2),  
            autoBuilder.fullAuto(pathGroup)); 
    }

    public SequentialCommandGroup coneHigh() {
        return new SequentialCommandGroup(
                new MoveWrist(wrist, -0.2),
                new MoveArm(shoulder, 39),
                new moveExtension(extension, -187.3),
                new MoveWrist(wrist, -0.7));
    }

    //MiddleScore
    public SequentialCommandGroup auto2(){
        return new SequentialCommandGroup(
        new MoveWrist(wrist, 0), 
        new MoveArm(shoulder, 31.4), 
        new moveExtension(extension, -56.8), 
        new MoveWrist(wrist, -.7), 
        new Intake(intake, 0.5, true), 
        new MoveWrist(wrist, 0),  
        new moveExtension(extension, 0), 
        new MoveArm(shoulder, 0), 
        new MoveWrist(wrist, -0.2),  
        new Intake(intake, 0, false));
    }
  
  


    



}