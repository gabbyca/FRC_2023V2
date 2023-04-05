package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class RunIntakeCommand extends CommandBase {
  private final IntakeSubsystem m_intakeSubsystem;
  private final double m_speed;
  private final boolean m_inverted;

  public RunIntakeCommand(IntakeSubsystem intakeSubsystem, double speed, boolean inverted) {
    m_intakeSubsystem = intakeSubsystem;
    m_speed = speed;
    m_inverted = inverted;
    addRequirements(intakeSubsystem);
  }

  @Override
  public void execute() {
    m_intakeSubsystem.intake(m_speed, m_inverted);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
