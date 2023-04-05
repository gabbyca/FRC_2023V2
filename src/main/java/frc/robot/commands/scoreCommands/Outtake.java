package frc.robot.commands.scoreCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

//command for running the intake our at certain speed
public class Outtake extends CommandBase {
    private final IntakeSubsystem intakeSub;
    private final double speed;

    public Outtake(IntakeSubsystem intakeSub, double speed){
        this.intakeSub = intakeSub;
        this.speed = speed;
        addRequirements(intakeSub);
    }

    @Override
    public void initialize(){
        intakeSub.setIntakeSpeedDirection(speed, false);
    }

    @Override
    public boolean isFinished(){
        return true; // the command is finished when it is initialized, since it just sets the speed
    }
}
