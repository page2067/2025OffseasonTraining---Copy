// commands/RunCoralHand.java

package frc.robot.commands.VelocityCommands;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralHand;
public class RunCoralHand extends Command {

    private final CoralHand m_coralHand;
    private final double m_coralHandTargetRPM;
  

    public RunCoralHand(CoralHand coralHand, double coralHandTargetRPM) {
        m_coralHand = coralHand;
        m_coralHandTargetRPM = coralHandTargetRPM;
        addRequirements(m_coralHand);
    }

    @Override
    public void execute() {
            m_coralHand.runCoralHand(m_coralHandTargetRPM);
    }
    
    @Override
    public void end(boolean interrupted) {
        m_coralHand.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}