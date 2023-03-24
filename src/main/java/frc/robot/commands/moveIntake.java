package frc.robot.commands;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class moveIntake  extends CommandBase{

    private final IntakeSubsystem intake; 


    public moveIntake(IntakeSubsystem intake){
       this.intake = intake; 

        addRequirements(intake);
    }

    @Override 
    public void initialize(){
    }

    @Override 
    public void execute(){
       intake.moveMan(); 
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
} //TEST DELETE!!!