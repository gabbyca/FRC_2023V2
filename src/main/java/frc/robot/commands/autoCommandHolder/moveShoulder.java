package frc.robot.commands.autoCommandHolder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class moveShoulder extends CommandBase{

    private final ArmSubsystem shoulderSub;
    private final double shoulderSetpoint;
    private final double TOLERANCE = 100; //might change

    public moveShoulder(ArmSubsystem shoulderSub, double shoulderSetpoint){
        this.shoulderSub = shoulderSub; 
        this.shoulderSetpoint = shoulderSetpoint;
        addRequirements(shoulderSub);
    }

    @Override 
    public void initialize(){
       shoulderSub.moveArm(shoulderSetpoint);
    }

    @Override 
    public boolean isFinished(){
       return shoulderSub.getDistance() < shoulderSetpoint + TOLERANCE && shoulderSub.getDistance() > shoulderSetpoint - TOLERANCE;
    }
    
}