package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IntakeSubsystem extends SubsystemBase {
    
  private final CANSparkMax m_intake;
  boolean inverted = false;
  double speed = 0; 
  


  public IntakeSubsystem(){
     m_intake = new CANSparkMax(0, MotorType.kBrushless); //ensure brushless
     m_intake.setIdleMode(IdleMode.kBrake);
  }

  public void motorZero(){
    m_intake.set(0);
  }

  public void intake(double speed, boolean inverted){
    this.speed = speed; 
    this.inverted = inverted; 
    m_intake.setInverted(inverted);
    m_intake.set(speed);
  }

  @Override
  public void periodic(){
    
   }

}
