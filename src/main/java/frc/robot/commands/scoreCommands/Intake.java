package frc.robot.commands.scoreCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class Intake extends CommandBase{

    private final IntakeSubsystem intakeSub;
    private final double speed; 
    private final boolean inverted; 
    
    public Intake(IntakeSubsystem intakeSub, double speed, boolean inverted){
        this.intakeSub = intakeSub; 
        this.speed = speed; 
        this.inverted = inverted; 
        addRequirements(intakeSub);
    }

    @Override 
    public void initialize(){
      intakeSub.intake(speed, inverted);
    }

    @Override 
    public boolean isFinished(){
      return !intakeSub.isCargo(); 
    }
  
}