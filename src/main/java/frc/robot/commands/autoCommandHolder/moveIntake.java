package frc.robot.commands.autoCommandHolder;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class moveIntake extends CommandBase{ 

    public moveIntake(){
       
        addRequirements();
    }

    @Override 
    public void initialize(){
     
    }

    @Override 
    public boolean isFinished(){
        return false; 
    }
    
}