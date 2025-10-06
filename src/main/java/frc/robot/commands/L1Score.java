

// package frc.robot.commands;

// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
// import frc.robot.commands.PositionCommands.SetCoralShoulder;
// import frc.robot.commands.VelocityCommands.RunCoralHand;
// import frc.robot.subsystems.CoralHand;
// import frc.robot.subsystems.CoralShoulder;

// public class L1Score extends SequentialCommandGroup {
   
//     //CoralShoulder coralShoulder = CoralShoulder.getInstance();
//     //CoralHand coralHand = CoralHand.getInstance();
  
//     private final CoralShoulder m_coralShoulder = CoralShoulder.getInstance();
//     private final CoralHand m_coralHand = CoralHand.getInstance();
//     // private final DigitalInput m_coralSensor;
  

//     public L1Score() {
//         addCommands(
//         new SetCoralShoulder(m_coralShoulder,-30.0),
//         new WaitCommand(0.2),
//         new RunCoralHand(m_coralHand,1200.0)
//         );
//     }    
   
// }


package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PositionCommands.SetCoralShoulder;
import frc.robot.commands.VelocityCommands.RunCoralHand;
import frc.robot.subsystems.CoralHand;
import frc.robot.subsystems.CoralShoulder;


public class L1Score extends SequentialCommandGroup {
   
    CoralShoulder coralShoulder = CoralShoulder.getInstance();
    CoralHand coralHand = CoralHand.getInstance();
  
    
    public L1Score() {
        addCommands(
        new SetCoralShoulder(coralShoulder,-30.0),
        new WaitCommand(0.2),
        new RunCoralHand(coralHand,1200.0)
        );
    }    
   
}

