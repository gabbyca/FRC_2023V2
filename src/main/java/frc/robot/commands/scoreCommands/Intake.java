package frc.robot.commands.scoreCommands;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

import static frc.robot.Constants.IntakeConstants.*;

// command for intaking a cone/cube at set speed until motor stalls, then runs at a lower speed to hold it in
public class Intake extends CommandBase{

    private final IntakeSubsystem intakeSub;
    private final double speed;
    private Debouncer filter_debouncer = new Debouncer(DEBOUNCE_TIME, Debouncer.DebounceType.kBoth);
    public Intake(IntakeSubsystem intakeSub, double speed){
        this.intakeSub = intakeSub; 
        this.speed = speed;
        addRequirements(intakeSub);
    }

    @Override 
    public void initialize(){
      intakeSub.setIntakeSpeedDirection(speed, false);
    }

    @Override 
    public boolean isFinished(){ // command finishes when cube is in the intake (current is high)
        //in case we want to disable debounce
        if(DEBOUNCE) {
            return filter_debouncer.calculate(IntakeSubsystem.filtered_current>CURRENT_THRESHOLD);
        } else {
            return IntakeSubsystem.filtered_current>CURRENT_THRESHOLD;
        }
    }
    @Override
    public void end(boolean interrupted){
        if(interrupted) { //TODO:diff interrupt behavior??, does the same thing right now
            intakeSub.setIntakeSpeedDirection(0.05, false); 
        }
        else {
            intakeSub.setIntakeSpeedDirection(0.05, false);
        }
    }
}