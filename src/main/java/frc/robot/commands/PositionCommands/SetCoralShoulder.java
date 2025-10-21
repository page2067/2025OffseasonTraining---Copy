// commands/RunCoralShoulderElevator.java
package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.Constants.CoralShoulderConstants;
import frc.robot.subsystems.CoralShoulder;


public class SetCoralShoulder extends Command {

    private final CoralShoulder m_coralShoulder;
    private final double m_targetDegrees;
  
    // public SetCoralShoulder(CoralShoulder coralShoulder) {
    public SetCoralShoulder(CoralShoulder coralShoulder, double targetDegrees) {
        m_coralShoulder = coralShoulder;
    // next line added
        m_targetDegrees = targetDegrees;
        addRequirements(m_coralShoulder);
    }

    @Override
    public void initialize() {
        // m_coralShoulder.setCoralShoulder(CoralShoulderConstants.kCoralShoulderTargetAngle);
        m_coralShoulder.setCoralShoulder(m_targetDegrees);
    }

    @Override
    public void end(boolean interrupted) {
        m_coralShoulder.setCoralShoulder(0.0); // Return to initial position
    }

    @Override
    public boolean isFinished() {
        // This command will end when both the shoulder and elevator reach their targets
        // You may need to add logic here to check if the mechanisms have reached their targets
        // For example, by checking the error or position of the motors
        return false;
    }
}


