

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PositionCommands.SetCoralShoulder;
import frc.robot.commands.VelocityCommands.RunCoralHand;
import frc.robot.subsystems.CoralHand;
import frc.robot.subsystems.CoralShoulder;


public class RunL1Score extends ParallelCommandGroup {

    public RunL1Score(CoralShoulder coralShoulder, CoralHand coralHand) {
        addCommands(
            new SetCoralShoulder(coralShoulder, -25.0), // Move the CoralShoulder to -25 degrees
            new WaitCommand(0.3).andThen(new RunCoralHand(coralHand, 1200.0)         // Run the CoralHand at 1200.0 speed
        ));
    }
       
}

