// commands/RunInlet.java

package frc.robot.commands.VelocityCommands;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Inlet;

public class RunInlet extends Command {

    private final Inlet m_inlet;
    private final DigitalInput m_coralSensor;
    private final double m_inletTargetRPM;
  

    public RunInlet(Inlet inlet, DigitalInput coralSensor, double inletTargetRPM) {
        m_inlet = inlet;
        m_coralSensor = coralSensor;
        m_inletTargetRPM = inletTargetRPM;
        addRequirements(m_inlet);
    }

    @Override
    public void execute() {
        if (m_coralSensor.get()) { // If DIO 0 is false
            m_inlet.runInlet(m_inletTargetRPM);
        } else {
            m_inlet.stopMotor();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_inlet.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
@Override
public InterruptionBehavior getInterruptionBehavior() {
    // TODO Auto-generated method stub
    return super.getInterruptionBehavior();
}}