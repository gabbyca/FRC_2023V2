package frc.robot.commands;

import java.security.cert.Extension;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.ExtensionSubsystem;

public class stopExtension  extends CommandBase{

    private final ExtensionSubsystem extend; 


    public stopExtension(ExtensionSubsystem extend){
       this.extend = extend; 
        addRequirements(extend);
    }

    @Override 
    public void initialize(){
       extend.stop();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
} //TEST DELETE!!!