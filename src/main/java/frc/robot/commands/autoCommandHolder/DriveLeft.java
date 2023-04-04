package frc.robot.commands.AutoCommandHolder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveLeft extends CommandBase{

    private DriveSubsystem drive; 
    private double startTime;
    
    public DriveLeft(DriveSubsystem drive){
        this.drive = drive;
        addRequirements(drive);
    }

    @Override 
    public void initialize(){
        startTime = Timer.getFPGATimestamp();
        drive.rotateLeft();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }

    @Override 
    public boolean isFinished(){
       return Timer.getFPGATimestamp() - startTime > 2.1;
    }
  
}