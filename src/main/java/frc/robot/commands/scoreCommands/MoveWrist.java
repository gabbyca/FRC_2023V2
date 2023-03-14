package frc.robot.commands.scoreCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;


public class MoveWrist extends CommandBase{

    private final WristSubsystem wristSub;
    private final double setPoint;
    private final double TOLERANCE = 3000;
    
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
    return wristSub.getWristEncoderDistance() < setPoint + TOLERANCE && wristSub.getWristEncoderDistance() > setPoint - TOLERANCE;
    }
    
}