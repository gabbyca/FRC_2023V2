package frc.robot.commands;

import java.security.cert.Extension;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.ExtensionSubsystem;

public class moveInExtend  extends CommandBase{

    private final ExtensionSubsystem extend; 


    public moveInExtend(ExtensionSubsystem extend){
       this.extend = extend; 

        addRequirements(extend);
    }

    @Override 
    public void initialize(){
       extend.manIn();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
} //TEST DELETE!!!