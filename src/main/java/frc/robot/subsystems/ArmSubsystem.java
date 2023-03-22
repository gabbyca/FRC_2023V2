package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ArmSubsystem extends SubsystemBase {
    
    private final CANSparkMax m_Lead;
    private final CANSparkMax m_Follow;
    RelativeEncoder encoder;
    double currentShoulderDistance; 
    PIDController m_pidController; 

    double kP = 0.004; 
    double kI = 0.00; 
    double kD = 0.0001; 
    double setpoint = 0.0; 
    
    double maxPower = 0.4;

  public ArmSubsystem(){
      
      m_Lead = new CANSparkMax(9, MotorType.kBrushless);
      m_Lead.setInverted(true);
     
      m_Follow =  new CANSparkMax(10, MotorType.kBrushless);
      m_Follow.setInverted(false);
      m_Follow.follow(m_Lead);

      m_Lead.setIdleMode(IdleMode.kBrake);
      m_Follow.setIdleMode(IdleMode.kBrake);

      encoder = m_Lead.getEncoder();
      encoder.setPosition(0);

      m_pidController = new PIDController(kP, kI, kD);
      m_pidController.setTolerance(5, 10);
      m_pidController.setSetpoint(0);
  }

  public double getDistance(){
    currentShoulderDistance = encoder.getPosition();
    SmartDashboard.putNumber("ArmDist", currentShoulderDistance);
    return currentShoulderDistance;
  }
   
  public void resetEncoder(){
    encoder.setPosition(0);
  }

  public void motorZero(){
    m_Lead.set(0);
    m_pidController.setSetpoint(0);
  }

  public void moveArm(double pose) {
    m_pidController.setSetpoint(pose);
    setpoint = pose;
 }

  public void calculate(){
    double output;
    if(setpoint < encoder.getPosition()) 
        output = MathUtil.clamp(m_pidController.calculate(encoder.getPosition()), -maxPower, maxPower);
    else
        output = MathUtil.clamp(m_pidController.calculate(encoder.getPosition()), -maxPower, maxPower) * 0.2;
    
    m_Lead.set(output);
   
   // SmartDashboard.putNumber("Arm Output", output);
   // SmartDashboard.putNumber("Arm Setpoint", setpoint);
  }

   @Override
  public void periodic(){
    calculate();
    getDistance(); 
   }

}
