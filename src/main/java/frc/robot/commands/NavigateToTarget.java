package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class NavigateToTarget extends CommandBase {

    private final DriveSubsystem swerveDrive;
    private final double Kp = 0.05;
    private final double Kd = 0.05;
    private double x, y;

    public NavigateToTarget(DriveSubsystem swerveDrive) {
        this.swerveDrive = swerveDrive;
        addRequirements(swerveDrive);
    }

    @Override
    public void initialize() {
        SmartDashboard.putString("good", "sign"); 
    }

    @Override
    public void execute() {
        x = swerveDrive.getTargetX();
        y = swerveDrive.getTargetY();

        double steerAdjust = Kp * x + Kd * y;
        double forwardAdjust = Kp * y;

        swerveDrive.drive(forwardAdjust, 0, steerAdjust, true, true);
    }

    @Override
    public boolean isFinished() {
        return true; 
    }
}
