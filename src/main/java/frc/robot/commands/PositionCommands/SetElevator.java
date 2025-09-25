// Elevator Position Control
package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;


public class SetElevator extends Command {

    private final Elevator m_elevator;
    private final double m_targetDistance;
 
    public SetElevator(Elevator elevator, double targetDistance) {
        m_elevator = elevator;
        m_targetDistance = targetDistance;
        addRequirements(m_elevator);
    }

    @Override
    public void initialize() {
        m_elevator.setElevatorPosition(m_targetDistance); 
    }

    @Override
    public void end(boolean interrupted) {
        m_elevator.setElevatorPosition(0.0); // Return to initial position
    }

    @Override
    public boolean isFinished() {
        // This command will end when both the shoulder and elevator reach their targets
        // You may need to add logic here to check if the mechanisms have reached their targets
        // For example, by checking the error or position of the motors
        return false;
    }
}


