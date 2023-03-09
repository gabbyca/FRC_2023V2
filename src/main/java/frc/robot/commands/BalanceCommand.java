package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class BalanceCommand extends CommandBase {
    
    private final DriveSubsystem driveSubsystem; 
    private final PIDController pidController= new PIDController(1, 0, 0);
    double setPoint; 
    double lastAtSetpoint = 0; 
    double TOLERANCE_TIME = 1.0; 

    public BalanceCommand(DriveSubsystem driveSubsystem)
    {   
        this.driveSubsystem = driveSubsystem; 
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        pidController.setSetpoint(0);
    }

    @Override
    public void execute() {
       driveSubsystem.drive(pidController.calculate(driveSubsystem.getRoll()), 0, 0, false, false);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.drive(0, 0, 0, false, false);
    }

    @Override
    public boolean isFinished() {
        if(!pidController.atSetpoint()){
            lastAtSetpoint =   Timer.getFPGATimestamp();
        } else if(Timer.getFPGATimestamp() - lastAtSetpoint > TOLERANCE_TIME){
            return true; 
        }
        return false; 
    }
    
}