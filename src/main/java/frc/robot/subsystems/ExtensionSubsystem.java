package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ExtensionSubsystem extends SubsystemBase {
    
    private final CANSparkMax m_motor;
  
    Encoder encoder;
    double currentExtensionDistance; 
    PIDController m_pidController; 
    double kP = 0.004; 
    double kI = 0.00; 
    double kD = 0.0001; 
    double setpoint = 0.0; 
    double maxPower = 0.4;

  public ExtensionSubsystem(){
      
      m_motor = new CANSparkMax(0, MotorType.kBrushed);
      m_motor.setIdleMode(IdleMode.kBrake);

      encoder = new Encoder(0, 1, true, Encoder.EncodingType.k2X);
      encoder.setDistancePerPulse(1.0);
      encoder.reset();
      m_pidController = new PIDController(kP, kI, kD);
      m_pidController.setTolerance(5, 10);
      m_pidController.setSetpoint(0);
  }

  public double getDistance(){
    currentExtensionDistance = encoder.getDistance();
    SmartDashboard.putNumber("ExtensionDist", currentExtensionDistance);
    return currentExtensionDistance;
  }
   
  public void resetEncoder(){
    encoder.reset();
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
    if(setpoint < encoder.getDistance()) 
        output = MathUtil.clamp(m_pidController.calculate(encoder.getDistance()), -maxPower, maxPower);
    else
        output = MathUtil.clamp(m_pidController.calculate(encoder.getDistance()), -maxPower, maxPower) * 0.2;
    
    m_motor.set(output);
    // SmartDashboard.putNumber("Extension Output", output);
    // SmartDashboard.putNumber("Extension Setpoint", setpoint);
  }

   @Override
  public void periodic(){
    calculate();
   }

}
