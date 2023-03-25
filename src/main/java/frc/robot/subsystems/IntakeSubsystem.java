package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IntakeSubsystem extends SubsystemBase {
    
  private final CANSparkMax m_intake;
  boolean inverted = false;
  double speed = 0; 
  double current = 0; 
  
  
  public IntakeSubsystem(){
    m_intake = new CANSparkMax(13, MotorType.kBrushless);
  }
  

  public void intake(double speed, boolean inverted){
    this.speed = speed; 
    this.inverted = inverted; 
    m_intake.set(speed);
    m_intake.setInverted(inverted);
  }
 

 
  @Override
  public void periodic(){}

}
