package frc.robot.commands;

import java.security.cert.Extension;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.ExtensionSubsystem;

public class moveExtend  extends CommandBase{

    private final ExtensionSubsystem extend; 


    public moveExtend(ExtensionSubsystem extend){
       this.extend = extend; 

        addRequirements(extend);
    }

    @Override 
    public void initialize(){
       extend.manOut();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
} //TEST DELTE!!!!