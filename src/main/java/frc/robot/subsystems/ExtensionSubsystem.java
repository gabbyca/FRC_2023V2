package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ExtensionSubsystem extends SubsystemBase {
    
    private final CANSparkMax m_motor;
  
    RelativeEncoder encoder;
    double currentExtensionDistance; 
    PIDController m_pidController; 

    double kP = 0.004; 
    double kI = 0.00; 
    double kD = 0.0001; 
    double setpoint = 0.0; 
    
    double maxPower = 0.4;

  public ExtensionSubsystem(){
      
      m_motor = new CANSparkMax(11, MotorType.kBrushless);
      m_motor.setIdleMode(IdleMode.kBrake);

      encoder = m_motor.getEncoder();
      encoder.setPosition(0);

      m_pidController = new PIDController(kP, kI, kD);
      m_pidController.setTolerance(5, 10);
      m_pidController.setSetpoint(0);
  }

  public double getDistance(){
    currentExtensionDistance = encoder.getPosition();
    SmartDashboard.putNumber("ExtensionDist", currentExtensionDistance);
    return currentExtensionDistance;
  }
   
  public void resetEncoder(){
    encoder.setPosition(0);
  }

  public void motorZero(){
    m_motor.set(0);
    m_pidController.setSetpoint(0);
  }

  public void moveExtension(double pose) {
    m_pidController.setSetpoint(pose);
    setpoint = pose;
 }

  public void calculate(){
    double output;
    if(setpoint < encoder.getPosition()) 
        output = MathUtil.clamp(m_pidController.calculate(encoder.getPosition()), -maxPower, maxPower);
    else
        output = MathUtil.clamp(m_pidController.calculate(encoder.getPosition()), -maxPower, maxPower) * 0.2;
    
    m_motor.set(output);

   // SmartDashboard.putNumber("Extension Output", output);
   // SmartDashboard.putNumber("Extension Setpoint", setpoint);
  }

   @Override
  public void periodic(){
    calculate();
   }

}
