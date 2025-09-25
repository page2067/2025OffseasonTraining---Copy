// commands/RunInletCoralHand.java

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.Constants.InletConstants;
// import frc.robot.Constants.CoralHandConstants;
import frc.robot.subsystems.Inlet;
import frc.robot.subsystems.CoralHand;

public class IntakeCoralToCoralHand extends Command {

    private final Inlet m_inlet;
    private final CoralHand m_coralHand;
    private final DigitalInput m_coralSensor;
  

    public IntakeCoralToCoralHand(Inlet inlet, CoralHand coralHand, DigitalInput coralSensor) {
        m_inlet = inlet;
        m_coralHand = coralHand;
        m_coralSensor = coralSensor;
        addRequirements(m_inlet, m_coralHand);
    }

    @Override
    public void execute() {
        if (m_coralSensor.get()) { // If CoralSensor is false
            m_inlet.runInlet(-60.0);
            m_coralHand.runCoralHand(60.0);
        } else {
            m_inlet.stopMotor();
            m_coralHand.stopMotor();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_inlet.stopMotor();
        m_coralHand.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
   
}