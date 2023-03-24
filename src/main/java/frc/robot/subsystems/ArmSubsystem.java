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

    double kP = 0.1; 
    double kI = 0.00; 
    double kD = 0.001; 
    double setpoint = 0.0; 
    
    double maxPower = 0.5;

  public ArmSubsystem(){
      
      m_Lead = new CANSparkMax(9, MotorType.kBrushless);     
      m_Follow =  new CANSparkMax(10, MotorType.kBrushless);
      
      m_Follow.follow(m_Lead, true);

      m_Lead.setIdleMode(IdleMode.kCoast);
      m_Follow.setIdleMode(IdleMode.kCoast);

      encoder = m_Lead.getEncoder();
      encoder.setPosition(0);

      m_pidController = new PIDController(kP, kI, kD);
      m_pidController.setTolerance(5, 10);
      m_pidController.setSetpoint(0);
      
      m_Lead.burnFlash(); 
      m_Follow.burnFlash(); 
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
        output = MathUtil.clamp(m_pidController.calculate(encoder.getPosition()), -maxPower, maxPower) ;
    
    m_Lead.set(output);
   
   SmartDashboard.putNumber("Arm Output", output);
   SmartDashboard.putNumber("Arm Setpoint", setpoint);
  }

   @Override
  public void periodic(){
    calculate();
    getDistance(); 
   }

}
