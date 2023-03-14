package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShoulderSubsystem extends SubsystemBase {
    
    private final TalonSRX m_Lead;
    private final TalonSRX m_Follow;
    Encoder encoder;
    double currentShoulderDistance; 
    PIDController m_pidController; 
    double kP = 0.004; 
    double kI = 0.00; 
    double kD = 0.0001; 
    double setpoint = 0.0; 
    double maxPower = 0.4;

  public ShoulderSubsystem(){
      TalonSRXConfiguration config = new TalonSRXConfiguration();
      config.peakCurrentLimit = 133;
      config.peakCurrentDuration = 1500;
      config.continuousCurrentLimit = 120;
      
      m_Lead = new TalonSRX(7);
      m_Lead.configAllSettings(config);
      m_Lead.setInverted(true);
     
      m_Follow =  new TalonSRX(8);
      m_Follow.configAllSettings(config);
      m_Follow.follow(m_Lead);
      m_Follow.setInverted(InvertType.FollowMaster); //might have to change

      m_Lead.setNeutralMode(NeutralMode.Brake);
      m_Follow.setNeutralMode(NeutralMode.Brake);

      encoder = new Encoder(0, 1, true, Encoder.EncodingType.k2X);
      encoder.setDistancePerPulse(1.0);
      encoder.reset();
      m_pidController = new PIDController(kP, kI, kD);
      m_pidController.setTolerance(5, 10);
      m_pidController.setSetpoint(0);
  }

  public double getDistance(){
    currentShoulderDistance = encoder.getDistance();
    SmartDashboard.putNumber("ShoulderDistance", currentShoulderDistance);
    return currentShoulderDistance;
  }
   
  public void resetEncoder(){
    encoder.reset();
  }

  public void motorZero(){
    m_Lead.set(TalonSRXControlMode.PercentOutput, 0);
    m_pidController.setSetpoint(0);
  }

  public void moveArm(double pose) {
    m_pidController.setSetpoint(pose);
    setpoint = pose;
}
   public void calculate(){
    double output;
    if(setpoint < encoder.getDistance()) 
        output = MathUtil.clamp(m_pidController.calculate(encoder.getDistance()), -maxPower, maxPower);
    else
        output = MathUtil.clamp(m_pidController.calculate(encoder.getDistance()), -maxPower, maxPower) * 0.2;
    
    m_Lead.set(TalonSRXControlMode.PercentOutput, output);
    SmartDashboard.putNumber("SHOULDER Output", output);
    SmartDashboard.putNumber("SHOULDER Setpoint", setpoint);
  }


   @Override
   public void periodic(){
    calculate();
   }



}
