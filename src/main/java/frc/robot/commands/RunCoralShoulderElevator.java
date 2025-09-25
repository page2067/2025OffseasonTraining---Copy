// commands/RunCoralShoulderElevator.java
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.Constants.CoralShoulderConstants;
// import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.CoralShoulder;
import frc.robot.subsystems.Elevator;

public class RunCoralShoulderElevator extends Command {

    private final CoralShoulder m_coralShoulder;
    private final Elevator m_elevator;

    public RunCoralShoulderElevator(CoralShoulder coralShoulder, Elevator elevator) {
        m_coralShoulder = coralShoulder;
        m_elevator = elevator;
        addRequirements(m_coralShoulder, m_elevator);
    }

    @Override
    public void initialize() {
        m_coralShoulder.setCoralShoulder(10.);
        m_elevator.setElevatorPosition(8.0);
    }

    @Override
    public void end(boolean interrupted) {
        m_coralShoulder.setCoralShoulder(0.0); // Return to initial position
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


