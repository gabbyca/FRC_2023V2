package frc.robot.commands.scoreCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShoulderSubsystem;

public class MoveShoulder extends CommandBase{

    private final ShoulderSubsystem shoulderSub;
    private final double setPoint;
    private final double TOLERANCE = 100; //might change

    public MoveShoulder(ShoulderSubsystem shoulderSub, double setpoint){
        this.shoulderSub = shoulderSub; 
        this.setPoint = setpoint;
        addRequirements(shoulderSub);
    }

    @Override 
    public void initialize(){
       shoulderSub.moveArm(setPoint);
    }

    @Override 
    public boolean isFinished(){
       return shoulderSub.getDistance() < setPoint + TOLERANCE && shoulderSub.getDistance() > setPoint - TOLERANCE;
    }
    
}