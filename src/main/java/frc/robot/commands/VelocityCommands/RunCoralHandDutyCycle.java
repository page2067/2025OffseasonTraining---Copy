// commands/RunInlet.java

package frc.robot.commands.VelocityCommands;
// import java.security.interfaces.DSAPublicKey;

// import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.CoralHandDutyCycle;

public class RunCoralHandDutyCycle extends Command {

    private final CoralHandDutyCycle m_coralHandDutyCycle;
    private final double m_dutyCycle;


    // private final DigitalInput m_coralSensor;
   
    public RunCoralHandDutyCycle(CoralHandDutyCycle coralHandDutyCycle, double dutyCycle) {
          m_coralHandDutyCycle = coralHandDutyCycle;
          m_dutyCycle = dutyCycle;
            addRequirements(m_coralHandDutyCycle);
    }


   @Override
    public void execute() {
        m_coralHandDutyCycle.setSpeed(m_dutyCycle);
            
        } 
    

    @Override
    public void end(boolean interrupted) {
        m_coralHandDutyCycle.stopMotor();
       
    }

    @Override
    public boolean isFinished() {
        return false;
    }
   
}