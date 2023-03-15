package frc.robot.commands.autoCommandHolder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class moveWrist extends CommandBase{

    private final WristSubsystem wristSub;
    private final double wristSetpoint;
    private final double TOLERANCE = 100; //might change

    public moveWrist(WristSubsystem wristSub, double wristSetpoint){
        this.wristSub = wristSub; 
        this.wristSetpoint = wristSetpoint;
        addRequirements(wristSub);
    }

    @Override 
    public void initialize(){
       wristSub.moveWrist(wristSetpoint);
    }

    @Override 
    public boolean isFinished(){
       return wristSub.getWristEncoderDistance() < wristSetpoint + TOLERANCE && wristSub.getWristEncoderDistance() > wristSetpoint - TOLERANCE;
    }
    
}