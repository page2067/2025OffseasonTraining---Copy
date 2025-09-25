// commands/RunL1.java   

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
// import frc.robot.Constants.CoralHandConstants;
// import frc.robot.Constants.CoralShoulderConstants;

import frc.robot.subsystems.CoralHand;
import frc.robot.subsystems.CoralShoulder;

public class RunL1 extends Command {

    private final CoralShoulder m_coralShoulder;
    private final CoralHand m_coralHand;
    private final DigitalInput m_coralSensor;
  

    public RunL1(CoralShoulder coralShoulder, CoralHand coralHand, DigitalInput coralSensor) {
        m_coralShoulder = coralShoulder;
        m_coralHand = coralHand;
        m_coralSensor = coralSensor;
        addRequirements(m_coralShoulder, m_coralHand);
    }

    @Override
    public void initialize() {
        m_coralShoulder.setCoralShoulder(200.0);
    }
   

    @Override
    public void execute() {
        if (!m_coralSensor.get()) { // If CoralSensor is True (ie holding coral)
        m_coralHand.runCoralHand(100.0);
        } else {
        m_coralHand.stopMotor();
        m_coralShoulder.setCoralShoulder(0.0); // Return to initial position
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