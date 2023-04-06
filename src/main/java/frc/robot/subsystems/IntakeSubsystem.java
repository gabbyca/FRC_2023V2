package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.IntakeConstants.*;


public class IntakeSubsystem extends SubsystemBase {
    
  private final CANSparkMax m_intake;
  public boolean inverted = false;
  public double speed = 0;
  //double current = 0;
  public static double filtered_current = 0; // always keep track of filtered current
  private LinearFilter filter = LinearFilter.movingAverage(SAMPLE_SIZE);
  public IntakeSubsystem(){
    m_intake = new CANSparkMax(13, MotorType.kBrushless);
  }

  public void setIntakeSpeedDirection(double speed, boolean inverted){
    this.speed = speed; 
    this.inverted = inverted;
    m_intake.set(speed);
    m_intake.setInverted(inverted);
  }

  @Override
  public void periodic(){
    filtered_current = filter.calculate(m_intake.getOutputCurrent());
    System.out.println("Filtered Current: " + filtered_current);
  }
}
