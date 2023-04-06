package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    
     
  private final CANSparkMax m_intake;
  public boolean inverted = false;
  public double speed = 0;

  
  private static final double CURRENT_SPIKE_THRESHOLD = 38.0; // expected spike 
  private static final int DEBOUNCE_DELAY = 25; // required debounce time (ms)
  private long lastSpikeTime;

  public IntakeSubsystem(){
    m_intake = new CANSparkMax(13, MotorType.kBrushless);
    lastSpikeTime = System.currentTimeMillis();
  }
  
  public void intake(double speed, boolean inverted){
    this.speed = speed; 
    this.inverted = inverted; 
      if (isDebounced() && checkCurrentSpike()) 
        stopIntake();
      else {
        m_intake.set(speed);
        m_intake.setInverted(inverted);
      }
  }

  public void releaseIntake(double speed, boolean inverted){
    this.speed = speed; 
    this.inverted = inverted; 
    m_intake.set(speed);
    m_intake.setInverted(inverted);
  }

  public void stopIntake() {
      m_intake.set(0);
  }

  private boolean checkCurrentSpike() {
    double current = m_intake.getOutputCurrent();
    System.out.println(current);
    return current >= CURRENT_SPIKE_THRESHOLD;
  }

  private boolean isDebounced() {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastSpikeTime >= DEBOUNCE_DELAY) {
        lastSpikeTime = currentTime;
        return true;
    }
    return false;
  }

  @Override
  public void periodic(){}

}
