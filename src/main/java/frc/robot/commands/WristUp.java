package frc.robot.commands; 

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class WristUp extends CommandBase{

    private final WristSubsystem wristSub;
    private double setPoint;
    
    public WristUp(WristSubsystem wristSub){
        this.wristSub = wristSub; 
        addRequirements(wristSub);
    }

    @Override 
    public void initialize(){
       setPoint = wristSub.getDistance();
    }

    @Override 
    public void execute() {
        setPoint += 0.1; 
        wristSub.moveWrist(setPoint);
    }

    @Override
    public void end(boolean interrupted) {
        wristSub.moveWrist(setPoint); 
    }
}
