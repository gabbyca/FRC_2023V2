package frc.robot.commands.scoreCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExtensionSubsystem;

public class moveExtension extends CommandBase{

    private final ExtensionSubsystem extensionSub; 
    private final double setPoint;
    private final double TOLERANCE = 100; //might change

    public moveExtension(ExtensionSubsystem extensionSub, double setpoint){
        this.extensionSub = extensionSub; 
        this.setPoint = setpoint;
        addRequirements(extensionSub);
    }

    @Override 
    public void initialize(){
       extensionSub.moveExtension(setPoint);
    }

    @Override 
    public boolean isFinished(){
       return extensionSub.getDistance() < setPoint + TOLERANCE && extensionSub.getDistance() > setPoint - TOLERANCE;
    }
    
}