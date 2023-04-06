// The individual swerve motor control subsystem, with addition features
// to run the "Omnidirectional Front Wheel Drive" traction control system
/* todo:
 * tune mode settings
 * remove low power debug mode
 * talk to anthony about tread wear from spinning while driving
 * suggest zero heading relative to control stick relative to first heading
*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants.DriveConstants;

public class TCSwerveSubsystem extends SwerveSubsystem {
    
    private static boolean debug_lowpower = false;

    private static int m_tractionControlMode = 0;

    double m_xPosition = 0;
    double m_yPosition = 0;

    double m_balancedStrength = 1.0;

    public static void debug_togglelowpower() {
        if (debug_lowpower) {
            debug_lowpower = false;
        } else {
            debug_lowpower = true;
        }
    }

    public static void cycleTractionControlMode() {
        m_tractionControlMode++;
        if (m_tractionControlMode > DriveConstants.kTractionControlModes.length) m_tractionControlMode = 0;
        // this would be the place to print the current traction control mode, if printing is possible.
    }

    public static int getTractionControlMode() {
        return m_tractionControlMode;
    }

    public static void setTractionControlMode(int mode) {
        m_tractionControlMode = mode;
    }

    public TCSwerveSubsystem(int drivingCANId, int turningCANId, double chassisAngularOffset, double positionX, double positionY) {
        super(drivingCANId, turningCANId, chassisAngularOffset);
        m_xPosition = positionX;
        m_yPosition = positionY;
    }

    public void calculateStrength(Translation2d driveDirVector, double xReciprocal, double yReciprocal) {
        double scale = DriveConstants.kTractionControlModes[m_tractionControlMode-1];
        // if this does not work, comment the first formula out and use the second
        m_balancedStrength = (xReciprocal * driveDirVector.getX() * m_xPosition +
                              yReciprocal * driveDirVector.getY() * m_yPosition + 0.5) * scale + (1 - scale);
        //m_balancedStrength = ((driveDirVector.getX() * m_xPosition) / DriveConstants.kWheelTrainHypotenuse +
        //                     (driveDirVector.getY() * m_yPosition) / DriveConstants.kWheelTrainHypotenuse + 0.5) * scale + (1 - scale);
    }

    // this is nearly identical to setDesiredState() in superclass, but utilizes arbff if traction control is enabled
    @Override
    public void setDesiredState(SwerveModuleState desiredState) {
        SwerveModuleState correctedDesiredState = new SwerveModuleState();
        correctedDesiredState.speedMetersPerSecond = desiredState.speedMetersPerSecond;
        correctedDesiredState.angle = desiredState.angle.plus(Rotation2d.fromRadians(m_chassisAngularOffset));

        SwerveModuleState optimizedDesiredState = SwerveModuleState.optimize(correctedDesiredState,
            new Rotation2d(m_turningEncoder.getPosition()));
    
        if (!debug_lowpower) {
            if (m_tractionControlMode != 0) {
                m_drivingPIDController.setReference(optimizedDesiredState.speedMetersPerSecond, ControlType.kVelocity, 0, m_balancedStrength, ArbFFUnits.kPercentOut);
            } else {
                m_drivingPIDController.setReference(optimizedDesiredState.speedMetersPerSecond, ControlType.kVelocity);
            }
        } else {
            // debug
            if (m_tractionControlMode != 0) {
                m_drivingPIDController.setReference(optimizedDesiredState.speedMetersPerSecond, ControlType.kVelocity, 0, m_balancedStrength * 0.02, ArbFFUnits.kPercentOut);
            } else {
                m_drivingPIDController.setReference(optimizedDesiredState.speedMetersPerSecond, ControlType.kVelocity, 0, 0.02, ArbFFUnits.kPercentOut);
            }
            // end debug
        }
        m_turningPIDController.setReference(optimizedDesiredState.angle.getRadians(), ControlType.kPosition);
    
        m_desiredState = desiredState;
    }
    
}
