package frc.robot.commands.AutoCommandHolder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class MoveArm extends CommandBase{

    private final ArmSubsystem shoulderSub;
    private final double setPoint;
    private final double TOLERANCE = 2; 

    public MoveArm(ArmSubsystem shoulderSub, double setpoint){
        this.shoulderSub = shoulderSub; 
        this.setPoint = setpoint;
        addRequirements(shoulderSub);
    }

    @Override 
    public void initialize(){
    }

    @Override 
    public void execute(){
       shoulderSub.moveArm(setPoint);
    }

    @Override 
    public boolean isFinished(){
       return shoulderSub.getDistance() < setPoint + TOLERANCE && shoulderSub.getDistance() > setPoint - TOLERANCE;
    }
    
}