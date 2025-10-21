// RobotContainer.java
package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
// import frc.robot.Constants.InletConstants;
// import frc.robot.Constants.CoralHandConstants;
// import frc.robot.Constants.CoralShoulderConstants;
// import frc.robot.Constants.ElevatorConstants;
import frc.robot.commands.VelocityCommands.RunCoralHand;
import frc.robot.commands.VelocityCommands.RunCoralHandDutyCycle;
// import frc.robot.commands.VelocityCommands.RunInlet;
import frc.robot.commands.IntakeCoralToCoralHand;
import frc.robot.commands.RunL1Score;
import frc.robot.commands.PositionCommands.SetCoralShoulder;
import frc.robot.commands.PositionCommands.SetElevator;
import frc.robot.subsystems.Inlet;
import frc.robot.subsystems.CoralHand;
import frc.robot.subsystems.CoralHandDutyCycle;
import frc.robot.subsystems.CoralShoulder;
import frc.robot.subsystems.Elevator;
//import frc.robot.subsystems.CoralSensor;

public class RobotContainer {

    // Subsystems
    private final Inlet m_inlet = new Inlet();
    private final CoralHand m_coralHand = new CoralHand();
    private final CoralShoulder m_coralShoulder = new CoralShoulder();
    private final Elevator m_elevator = new Elevator();
    private final CoralHandDutyCycle coralHandDutyCycle = new CoralHandDutyCycle();
    // private final CoralHandDutyCycle coralSensor = new DigitalInput();
 
    // Controllers
    private final CommandXboxController m_operatorController = new CommandXboxController(Constants.OIConstants.kOperatorControllerPort);

    // Sensors
    private final DigitalInput m_coralSensor = new DigitalInput(1);

    
    public RobotContainer() {
    
        configureBindings();
    }

     private void configureBindings() {
        
       
        // new Trigger(m_coralSensor::get)
        //          .onTrue(new InstantCommand(() -> {
        //              m_inlet.stopMotor();
        //              m_coralHand.stopMotor();
        //          }));

       
        // If CoralSensor is false AND Button A is pressed, run Inlet and CoralHand motors
        // m_operatorController.a()  // alternate below
        // .toggleOnTrue(new RunCoralHand(m_coralHand, 1200.0));

        // Create a Trigger that activates when Button A is pressed AND m_coralSensor is false
        // new Trigger(() -> m_operatorController.a().getAsBoolean() && !m_coralSensor.get())
        // .whileTrue(new RunCoralHand(m_coralHand, 60.0).alongWith(new RunInlet(m_inlet,-60.0))); 

        // sesnor and coralHand
        m_operatorController.a()
        .toggleOnTrue(new RunCoralHand(m_coralHand, 60.0)); // .alongWith(new RunInlet(m_inlet,-60.0)))

        // This next command should perform the same as the prior (buttons A & B) -Tested
        m_operatorController.b()
        .whileTrue(new IntakeCoralToCoralHand(m_inlet, m_coralHand, m_coralSensor));

         // If y is pressed, run CoralShoulder - Tested but distance not yet verified
         m_operatorController.y()
         .toggleOnTrue(new SetCoralShoulder(m_coralShoulder,-360.0));

         // If POVUP is pressed, run CoralShoulderand the elevator in parallel
         m_operatorController.povUp()
         .toggleOnTrue(new SetCoralShoulder(m_coralShoulder,-10.0).alongWith(new SetElevator(m_elevator,-1.0)));
        
         // If POVRight is pressed, run  the elevator 
         m_operatorController.povRight()
        .toggleOnTrue(new SetElevator(m_elevator,-4.0));

         // If povDown is pressed, run L1 Score: Set coralshoulder, wait then run coralhand.
         m_operatorController.povDown()
         .whileTrue(new SetCoralShoulder(m_coralShoulder,-25.0).alongWith(new WaitCommand(0.3).andThen(new RunCoralHand(m_coralHand, 1200.0))));
        //  .whileTrue(new RunL1Score(m_coralShoulder, m_coralHand)  ) ; 
         
         // If povleft is pressed, run alternate  L1 Score: Set coralshoulder, wait then run coralhand.
         m_operatorController.povLeft()
          .toggleOnTrue(new RunL1Score(m_coralShoulder,m_coralHand));

         // If x is pressed, run DutyCycle
         m_operatorController.x()
         .whileTrue(new RunCoralHandDutyCycle(coralHandDutyCycle, 0.50));

            // Create a Trigger for when the right Y-axis is non-zero
        //new Trigger(() -> Math.abs(m_operatorController.getRightY()) > 0.1)
        //.whileTrue(new RunCoralHandDutyCycle(coralHandDutyCycle, m_operatorController.getRightY()));
    
                double speed = m_operatorController.getRightY();
                                new Trigger(() -> {
                                    double yValue = m_operatorController.getRightY();
                                    System.out.println("Right Y Value " +yValue);
                                    return Math.abs(yValue) > 0.1;  // Trigger Condition
                             
                        }).whileTrue(new RunCoralHandDutyCycle(coralHandDutyCycle, speed)); 
    } 
       
                   
    public Command getAutonomousCommand() {
        return null;
    }
        
}
