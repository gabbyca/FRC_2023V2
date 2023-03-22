package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IntakeSubsystem extends SubsystemBase {
    
  private final CANSparkMax m_intake;
  boolean inverted = false;
  double speed = 0; 
  double current = 0; 

  double coneInCurrent = 1000; //limit when a cone is in
  double cubeInCurrent = 1000; //limit when a cube is in 
  

  public IntakeSubsystem(){
     m_intake = new CANSparkMax(12, MotorType.kBrushless);
     m_intake.setIdleMode(IdleMode.kBrake);
  }

  public void motorZero(){
    m_intake.set(0);
  }

  public void intake(double speed, boolean inverted){ //true is release
    this.speed = speed; 
    this.inverted = inverted; 
    if(isCargo() && !inverted){
      speed = .1; 
      inverted = false; 
    }
    m_intake.setInverted(inverted);
    m_intake.set(speed);
  }

  public boolean isCargo(){
    if(current >= coneInCurrent || current >= cubeInCurrent){
      return true; 
    }

    return false; 
  }

  public double getCurrent(){
    current = m_intake.getOutputCurrent(); 
    SmartDashboard.putNumber("IntakeCurrent", current);
    return current; 
  }
 
  @Override
  public void periodic(){
    getCurrent();
    isCargo(); 
   }

}
