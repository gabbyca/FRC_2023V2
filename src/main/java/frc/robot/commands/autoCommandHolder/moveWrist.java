// package frc.robot.commands.autoCommandHolder;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.WristSubsystem;

// public class moveWrist extends CommandBase{

//     private final WristSubsystem wristSub;
//     private final double setpoint;
//     private final double TOLERANCE = 100; //might change

//     public moveWrist(WristSubsystem wristSub, double setpoint){
//         this.wristSub = wristSub; 
//         this.setpoint = setpoint;
//         addRequirements(wristSub);
//     }

//     @Override 
//     public void initialize(){
//        wristSub.moveWrist(setpoint);
//     }

//     @Override 
//     public boolean isFinished(){
//        return wristSub.getDistance() < setpoint + TOLERANCE && wristSub.getDistance() > setpoint - TOLERANCE;
//     }
    
// }