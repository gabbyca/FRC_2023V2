package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;

public class Pose extends CommandBase {

    private final DriveSubsystem swerveDrive;
    private final Limelight limelight; 
    
    PIDController m_pidController; 
    double kP = 0.1; 
    double kI = 0.00; 
    double kD = 0.001; 

    double currentX;
    double desiredX; 
    double maxPower = 0.5; 

    public Pose(DriveSubsystem swerveDrive, Limelight limelight) {
        this.swerveDrive = swerveDrive;
        this.limelight = limelight; 
        addRequirements(swerveDrive);
    }

    @Override
    public void initialize() {
        currentX = limelight.getTx(); 
    }

    @Override
    public void execute() {
        double output;
        if(desiredX < currentX) 
            output = MathUtil.clamp(m_pidController.calculate(currentX), -maxPower, maxPower);
        else
            output = MathUtil.clamp(m_pidController.calculate(currentX), -maxPower, maxPower) ;
        
        swerveDrive.drive(output, 0, 0, true, true);
    }

    @Override
    public boolean isFinished() {
        return desiredX - currentX < 3; 
    }
}
