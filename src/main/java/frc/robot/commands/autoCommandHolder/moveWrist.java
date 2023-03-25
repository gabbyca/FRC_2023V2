package frc.robot.commands.AutoCommandHolder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;


public class MoveWrist extends CommandBase{

    private final WristSubsystem wristSub;
    private final double setPoint;
    private final double TOLERANCE = 0.3;
    
    public MoveWrist(WristSubsystem wristSub, double setpoint){
        this.wristSub = wristSub; 
        this.setPoint = setpoint;
        addRequirements(wristSub);
    }

    @Override 
    public void initialize(){
       wristSub.moveWrist(setPoint);
    }

    @Override 
    public boolean isFinished(){
    return wristSub.getDistance() < setPoint + TOLERANCE && wristSub.getDistance() > setPoint - TOLERANCE;
    }
    
}