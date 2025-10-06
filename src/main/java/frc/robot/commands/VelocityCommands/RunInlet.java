// commands/RunInlet.java

package frc.robot.commands.VelocityCommands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Inlet;

public class RunInlet extends Command {

    private final Inlet m_inlet;
    private final double m_inletTargetRPM;
  

    public RunInlet(Inlet inlet, double inletTargetRPM) {
        m_inlet = inlet;
        //m_coralSensor = coralSensor;
        m_inletTargetRPM = inletTargetRPM;
        addRequirements(m_inlet);
    }

    @Override
    public void execute() {
            m_inlet.runInlet(m_inletTargetRPM);
            m_inlet.stopMotor();
        }
    

    @Override
    public void end(boolean interrupted) {
        m_inlet.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}