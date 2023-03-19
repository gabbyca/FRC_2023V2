package frc.robot.commands.scoreCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class MoveArm extends CommandBase{

    private final ArmSubsystem shoulderSub;
    private final double setPoint;
    private final double TOLERANCE = 100; //might change

    public MoveArm(ArmSubsystem shoulderSub, double setpoint){
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