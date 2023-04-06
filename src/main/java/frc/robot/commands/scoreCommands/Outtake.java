package frc.robot.commands.scoreCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

//command for running the intake our at certain speed
public class Outtake extends CommandBase {
    private final IntakeSubsystem intakeSub;
    private final double speed;
    private final boolean inverted; 
    double startTime; 

    public Outtake(IntakeSubsystem intakeSub, double speed, boolean inverted){
        this.intakeSub = intakeSub;
        this.speed = speed;
        this.inverted = inverted; 
        addRequirements(intakeSub);
    }

    @Override
    public void initialize(){
        startTime = Timer.getFPGATimestamp();
        intakeSub.setIntakeSpeedDirection(speed, inverted);
    }

    @Override
    public boolean isFinished(){
        return Timer.getFPGATimestamp() - startTime > 1.0;
    }
}
