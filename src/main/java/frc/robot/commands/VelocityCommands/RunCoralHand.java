// commands/RunInlet.java

package frc.robot.commands.VelocityCommands;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralHand;

public class RunCoralHand extends Command {

    private final CoralHand m_coralHand;
    private final DigitalInput m_coralSensor;
    private final double m_handTargetRPM;
  

    public RunCoralHand(CoralHand coralHand, DigitalInput coralSensor,double handTargetRPM) {
        m_coralHand = coralHand;
        m_coralSensor = coralSensor;
        m_handTargetRPM = handTargetRPM;
        addRequirements(m_coralHand);
    }

    @Override
    public void execute() {
        if (m_coralSensor.get()) { // If DIO 0 is false
            m_coralHand.runCoralHand(m_handTargetRPM);
        } else {
            m_coralHand.stopMotor();
      }
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