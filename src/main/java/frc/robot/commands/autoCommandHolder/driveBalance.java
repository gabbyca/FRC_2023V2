package frc.robot.commands.autoCommandHolder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class driveBalance extends CommandBase{

   private final DriveSubsystem driveSub; 
   private double startTime; //change threshold 

    public driveBalance(DriveSubsystem driveSub){
       this.driveSub = driveSub; 
       addRequirements(driveSub);
    }
    
    @Override 
    public void initialize(){
       startTime = Timer.getFPGATimestamp();
       driveSub.drive(0.5,0.5,0.5, true, true);
    }

    @Override
    public void end(boolean interrupted) {
        driveSub.drive(0,0,0, true, true);
    }

    @Override 
    public boolean isFinished(){
       return Timer.getFPGATimestamp() - startTime > 2.1;
    }
    
}