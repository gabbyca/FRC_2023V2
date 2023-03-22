package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;

public class SpeedCommand extends CommandBase{

    private final double speed; 


    public SpeedCommand(double speed){
        this.speed = speed; 

        addRequirements();
    }

    @Override 
    public void initialize(){
        DriveConstants.kMaxSpeedMetersPerSecond = speed;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}