package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class WristSubsystem extends SubsystemBase {
    
  private final CANSparkMax m_motor; 
  PIDController m_pidController; 
  RelativeEncoder encoder; 
  double currentWristDistance; 
  double kP = 0.00001; 
  double kI = 0.00; 
  double kD = 0.0;  
  double setpoint = 0.0; 
  double maxPower = 0.4;

  public WristSubsystem(){
    m_motor =  new CANSparkMax(0, MotorType.kBrushed); 
    m_motor.set(0);

    m_pidController = new PIDController(kP, kI, kD);
    m_pidController.setTolerance(5, 10);
    m_pidController.setSetpoint(0);

    encoder = m_motor.getEncoder(); 
    encoder.setPosition(0);    
  }

  public double getDistance(){
    currentWristDistance = encoder.getPosition();
    SmartDashboard.putNumber("WristDistance", currentWristDistance);
    return currentWristDistance; 
  }

  public void resetEncoder(){
    encoder.setPosition(0);    
  }

  public void moveWrist(double pose){
    m_pidController.setSetpoint(pose);
    setpoint = pose;
  }

  public void calculate(){
    double output;
    if(setpoint > encoder.getPosition())
        output = MathUtil.clamp(m_pidController.calculate(encoder.getPosition()), -maxPower, maxPower);
    else
        output = MathUtil.clamp(m_pidController.calculate(encoder.getPosition()), -maxPower, maxPower) * 0.6;
    
        m_motor.set(output);

    // SmartDashboard.putNumber("WRIST Output", output);
    // SmartDashboard.putNumber("WRIST Setpoint", setpoint);
  }

  @Override
  public void periodic(){
   calculate();
  }

}
