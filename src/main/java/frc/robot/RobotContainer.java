// RobotContainer.java
package frc.robot;

// import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
// import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
// import frc.robot.Constants.InletConstants;
// import frc.robot.Constants.CoralHandConstants;
// import frc.robot.Constants.CoralShoulderConstants;
// import frc.robot.Constants.ElevatorConstants;
import frc.robot.commands.VelocityCommands.RunCoralHand;
import frc.robot.commands.VelocityCommands.RunCoralHandDutyCycle;
import frc.robot.commands.VelocityCommands.RunInlet;
import frc.robot.commands.IntakeCoralToCoralHand;
// import frc.robot.commands.RunL1;
import frc.robot.commands.PositionCommands.SetCoralShoulder;
import frc.robot.commands.PositionCommands.SetElevator;
import frc.robot.subsystems.Inlet;
import frc.robot.subsystems.CoralHand;
import frc.robot.subsystems.CoralHandDutyCycle;
import frc.robot.subsystems.CoralShoulder;
import frc.robot.subsystems.Elevator;

public class RobotContainer {

    // Subsystems
     private final Inlet m_inlet = new Inlet();
     private final CoralHand m_coralHand = new CoralHand();
     private final CoralShoulder m_coralShoulder = new CoralShoulder();
     private final Elevator m_elevator = new Elevator();
     private final CoralHandDutyCycle coralHandDutyCycle = new CoralHandDutyCycle();
 
     // Controllers
     private final CommandXboxController m_operatorController = new CommandXboxController(Constants.OIConstants.kOperatorControllerPort);

     // Sensors
     private final DigitalInput m_coralSensor = new DigitalInput(1);

    public RobotContainer() {
        configureBindings();
    }

     private void configureBindings() {
        
       
         new Trigger(m_coralSensor::get)
                 .onTrue(new InstantCommand(() -> {
                     m_inlet.stopMotor();
                     m_coralHand.stopMotor();
                 }));

       
        // If CoralSensor is false AND Button A is pressed, run Inlet and CoralHand motors
        m_operatorController.a()
        //.whileTrue(new RunInletCoralHand(m_inlet, m_coralHand, m_coralSensor));         
        .whileTrue(new RunCoralHand(m_coralHand, m_coralSensor, -60.0).alongWith(new RunInlet(m_inlet,m_coralSensor,-60.0))); 
        // This next command should perform the same as the prior (buttons A & B) 
        m_operatorController.b()
        .whileTrue(new IntakeCoralToCoralHand(m_inlet, m_coralHand, m_coralSensor));

         // If POV Up is pressed, run CoralShoulder 
         m_operatorController.y()
         .toggleOnTrue(new SetCoralShoulder(m_coralShoulder,-50.0));

         // If Y is pressed, run CoralShoulderand the elevator in parallel
         m_operatorController.povUp()
         .toggleOnTrue(new SetCoralShoulder(m_coralShoulder,-60.0).alongWith(new SetElevator(m_elevator,-10.0)));
        
         // If B is pressed, run L1 Score: Set coralshoulder, wait then run coralhand.
         m_operatorController.povDown()
         .toggleOnTrue(new SetCoralShoulder(m_coralShoulder,-15.0).alongWith(new WaitCommand(0.3).andThen(new RunCoralHand(m_coralHand, m_coralSensor, 100.0))));
       
         // If x is pressed, run DutyCycle
         m_operatorController.x()
         .whileTrue(new RunCoralHandDutyCycle(coralHandDutyCycle, 0.50));
    
        }
    
    public Command getAutonomousCommand() {
        return null;
    }
}
